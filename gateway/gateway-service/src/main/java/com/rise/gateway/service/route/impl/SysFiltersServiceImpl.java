package com.rise.gateway.service.route.impl;

import com.rise.gateway.dao.route.SysFiltersMapper;
import com.rise.gateway.model.route.SysFilters;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rise.gateway.service.route.SysFiltersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 网关过滤器表 服务实现类
 * </p>
 *
 * @author 张牧之
 * @since 2022-12-06
 */
@Service
public class SysFiltersServiceImpl extends ServiceImpl<SysFiltersMapper, SysFilters> implements SysFiltersService {

    @Autowired(required = false)
    private SysFiltersMapper sysFiltersMapper;

    /**
     * 根据路由id查询
     * @param ids
     * @return
     */
    @Override
    public List<SysFilters> queryByRouteIds(List<Long> ids) {
        return sysFiltersMapper.queryByRouteIds(ids);
    }
}
