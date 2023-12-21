package com.rise.gateway.service.route;


import com.baomidou.mybatisplus.extension.service.IService;
import com.rise.gateway.model.route.SysFilters;

import java.util.List;

/**
 * <p>
 * 网关过滤器表 服务类
 * </p>
 *
 * @author 张牧之
 * @since 2022-12-06
 */
public interface SysFiltersService extends IService<SysFilters> {

    /**
     * 根据路由id查询
     * @param ids
     * @return
     */
    List<SysFilters> queryByRouteIds (List<Long> ids);
}
