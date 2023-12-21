package com.rise.gateway.provider.route;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rise.entity.BaseResult;
import com.rise.entity.QueryPage;
import com.rise.gateway.model.route.SysRoute;
import com.rise.gateway.service.route.impl.DynamicRouteServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author 张牧之
 * @date 2022-12-06 16:54:13
 * @Email zhanglichang99@gmail.com
 */

@RestController
@RequestMapping("/route")
public class RouteSettingController {
    @Autowired
    private DynamicRouteServiceImpl dynamicRouteService;


    /**
     * 保存路由
     * @param sysRouteDto
     * @return
     */
    @ApiOperation("保存路由")
    @PostMapping
    public BaseResult saveRoute (@RequestBody @Validated SysRoute sysRouteDto) {
        return dynamicRouteService.saveRoute(sysRouteDto);
    }


    /**
     * 删除路由
     * @param id
     * @return
     */
    @ApiOperation("删除路由")
    @DeleteMapping
    public BaseResult deleteRoute (Long id) {
        return dynamicRouteService.deleteRoute(id);
    }


    /**
     * 分页查询路由
     * @param param
     * @return
     */
    @ApiOperation("分页查询路由")
    @GetMapping("page")
    public BaseResult<Page<SysRoute>> page (@RequestBody QueryPage<SysRoute> param) {
        return dynamicRouteService.page(param);
    }


    /**
     * 刷新路由
     */
    @ApiOperation("刷新路由")
    @PutMapping("refresh")
    public void refresh () {
        dynamicRouteService.refresh();
    }
}
