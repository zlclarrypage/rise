package com.rise.gateway.service.route.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rise.entity.BaseResult;
import com.rise.entity.QueryPage;
import com.rise.gateway.enums.StatusEnum;
import com.rise.gateway.model.route.SysFilters;
import com.rise.gateway.model.route.SysPredicates;
import com.rise.gateway.model.route.SysRoute;
import com.rise.gateway.service.route.SysFiltersService;
import com.rise.gateway.service.route.SysPredicatesService;
import com.rise.gateway.service.route.SysRouteService;
import com.rise.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author 张牧之
 * @date 2022-12-06 16:32:12
 * @Email zhanglichang99@gmail.com
 */


@Service
@Slf4j
public class DynamicRouteServiceImpl implements ApplicationEventPublisherAware {

    @Resource
    private RouteDefinitionWriter routeDefinitionWriter;

    @Resource
    private ApplicationEventPublisher publisher;

    @Autowired
    private SysRouteService sysRouteService;
    @Autowired
    private SysPredicatesService sysPredicatesService;
    @Autowired
    private SysFiltersService sysFiltersService;


    private final static String REWRITEPATH = "RewritePath";
    private final static Integer DEFUALT_ORDER = 0;
    private final static String REGEXP_STR = "/%s/(?<remaining>.*)";
    private final static String REPLACEMENT_STR = "/${remaining}";
    private final static String LB_URI = "lb://%s";


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }


    /**
     * 刷新路由
     * @param routeDefinition
     * @return
     */
    public BaseResult refreshRoute(RouteDefinition routeDefinition){
        BaseResult result = new BaseResult();
        routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
        return result.success();
    }


    /**
     * 从内存中删除路由
     * @param id
     * @return
     */
    public BaseResult deleteMemoryRoute(String id){
        BaseResult result = new BaseResult();
        routeDefinitionWriter.delete(Mono.just(id)).subscribe();
        return result.success();
    }



    /**
     * 保存路由
     * @param sysRouteDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult saveRoute (SysRoute sysRouteDto) {
        BaseResult result = new BaseResult();
        String uri = sysRouteDto.getUri();
        //保存路由信息
        if (sysRouteDto.getUriType() == 1) {
            sysRouteDto.setUri(String.format(LB_URI,uri));
        }
        Boolean routeFlag = sysRouteService.saveOrUpdate(sysRouteDto);
        if (null != sysRouteDto.getId()) {
            //删除断言
            Map<String,Object> param = new HashMap<>();
            param.put("route_id",sysRouteDto.getId());
            sysPredicatesService.removeByMap(param);
            //删除过滤器
            sysFiltersService.removeByMap(param);
        }
        //批量新增断言信息
        sysRouteDto.getSysPredicatesList().forEach( e -> {
            e.setRouteId(sysRouteDto.getId());
            e.setId(null);
        } );
        Boolean predicatesFlag;
        if (CollectionUtils.isEmpty(sysRouteDto.getSysPredicatesList())) {
            predicatesFlag = true;
        } else {
            predicatesFlag = sysPredicatesService.saveBatch(sysRouteDto.getSysPredicatesList());
        }

        if (sysRouteDto.getUriType() == 1) {
            if (CollectionUtils.isEmpty(sysRouteDto.getSysFiltersList())) {
                //配置的微服务名并且未加过滤器则默认一个filters
                List<SysFilters> filters = new ArrayList<>();
                SysFilters sysFilters = new SysFilters();
                sysFilters.setTenantId(1L);
                sysFilters.setSort(DEFUALT_ORDER);
                sysFilters.setFilterName(REWRITEPATH);
                JSONObject args = new JSONObject();
                args.put("regexp",String.format(REGEXP_STR,uri));
                args.put("replacement",REPLACEMENT_STR);
                sysFilters.setArgs(JSON.toJSONString(args));
                filters.add(sysFilters);
                sysRouteDto.setSysFiltersList(filters);
            }
        }
        //批量新增过滤器信息
        sysRouteDto.getSysFiltersList().forEach( e -> {
            e.setRouteId(sysRouteDto.getId());
            e.setId(null);
        } );
        Boolean filtersFlag;
        if (CollectionUtils.isEmpty(sysRouteDto.getSysFiltersList())) {
            filtersFlag = true;
        } else {
            filtersFlag = sysFiltersService.saveBatch(sysRouteDto.getSysFiltersList());
        }

        if (routeFlag && predicatesFlag && filtersFlag) {
            //刷新路由
            refresh(sysRouteDto);
            return result.success();
        } else {
            result.setCode(StatusEnum.SAVE_ROUTE_ERROR.getCode());
            result.setMessage(StatusEnum.SAVE_ROUTE_ERROR.getMessage());
            return result;
        }
    }


    /**
     * 删除路由
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult deleteRoute (Long id) {
        BaseResult result = new BaseResult();
        //删除路由
        sysRouteService.removeById(id);
        //删除断言
        Map<String,Object> preParam = new HashMap<>();
        preParam.put("route_id",id);
        sysPredicatesService.removeByMap(preParam);
        //删除过滤器
        Map<String,Object> filtersParam = new HashMap<>();
        filtersParam.put("route_id",id);
        sysFiltersService.removeByMap(filtersParam);

        //删除gateway中的路由缓存
        deleteMemoryRoute(id.toString());
        return result.success();
    }


    /**
     * 定时刷新路由
     */
    @PostConstruct
    public void refresh () {
        List<SysRoute> sysRouteList = sysRouteService.list();
        //查询断言与过滤器
        queryPredicatesFilters(sysRouteList);
        if (CollectionUtils.isEmpty(sysRouteList)) {
            return;
        }
        for (SysRoute sysRoute : sysRouteList) {
            refresh(sysRoute);
        }
    }



    /**
     * 分页查询路由信息
     * @param param
     * @return
     */
    public BaseResult<Page<SysRoute>> page (QueryPage<SysRoute> param) {
        BaseResult<Page<SysRoute>> result = new BaseResult<>();
        Page<SysRoute> page = new Page<>(param.getCurrent(),param.getSize());
        QueryWrapper<SysRoute> wrapper = new QueryWrapper<>();
        if (StringUtil.isNotEmpty(param.getParam().getUri())) {
            wrapper.like("uri",param.getParam().getUri());
        }
        if (StringUtil.isNotEmpty(param.getParam().getRouteName())) {
            wrapper.like("route_name",param.getParam().getRouteName());
        }
        wrapper.orderByAsc("sort");
        Page<SysRoute> entityResult = sysRouteService.page(page,wrapper);
        List<SysRoute> routeList = entityResult.getRecords();
        //查询断言与过滤器
        queryPredicatesFilters(routeList);
        entityResult.setRecords(routeList);
        return result.success(entityResult);
    }



    /**
     * 刷新路由到网关缓存
     * @param sysRouteDto
     * @return
     */
    private void refresh (SysRoute sysRouteDto) {
        RouteDefinition routeDefinition = new RouteDefinition();
        List<PredicateDefinition> predicates = new ArrayList<>();
        List<FilterDefinition> filters = new ArrayList<>();

        routeDefinition.setId(sysRouteDto.getId().toString());
        routeDefinition.setOrder(sysRouteDto.getSort());
        URI uri;
        if(sysRouteDto.getUriType() == 2){
            uri = UriComponentsBuilder.fromHttpUrl(sysRouteDto.getUri()).build().toUri();
        } else{
            uri = URI.create(sysRouteDto.getUri());
        }
        routeDefinition.setUri(uri);

        for (SysPredicates entity : sysRouteDto.getSysPredicatesList()) {
            PredicateDefinition predicateDefinition = new PredicateDefinition();
            predicateDefinition.setName(entity.getPredicateName());
            predicateDefinition.setArgs(JSON.parseObject(entity.getArgs(),HashMap.class));
            predicates.add(predicateDefinition);
        }

        for (SysFilters entity : sysRouteDto.getSysFiltersList()) {
            FilterDefinition filterDefinition = new FilterDefinition();
            filterDefinition.setName(entity.getFilterName());
            filterDefinition.setArgs(JSON.parseObject(entity.getArgs(),HashMap.class));
            filters.add(filterDefinition);
        }
        routeDefinition.setPredicates(predicates);
        routeDefinition.setFilters(filters);
        //刷新
        refreshRoute(routeDefinition);
    }



    /**
     * 查询断言与过滤器
     * @param sysRoutes
     * @return
     */
    public void queryPredicatesFilters (List<SysRoute> sysRoutes) {
        if (CollectionUtils.isEmpty(sysRoutes)) {
            return;
        }
        List<Long> ids = sysRoutes.stream().map(SysRoute :: getId).collect(Collectors.toList());
        List<SysPredicates> predicatesList = sysPredicatesService.queryByRouteIds(ids);
        if (!CollectionUtils.isEmpty(predicatesList)) {
            Map<Long,List<SysPredicates>> predicatesMap = predicatesList.stream().collect(Collectors.groupingBy(SysPredicates :: getRouteId));
            for (SysRoute entity : sysRoutes) {
                entity.setSysPredicatesList(predicatesMap.get(entity.getId()));
            }
        }
        List<SysFilters> filtersList = sysFiltersService.queryByRouteIds(ids);
        if (!CollectionUtils.isEmpty(filtersList)) {
            Map<Long,List<SysFilters>> filtersMap = filtersList.stream().collect(Collectors.groupingBy(SysFilters :: getRouteId));
            for (SysRoute entity : sysRoutes) {
                entity.setSysFiltersList(filtersMap.get(entity.getId()));
            }
        }
    }
}