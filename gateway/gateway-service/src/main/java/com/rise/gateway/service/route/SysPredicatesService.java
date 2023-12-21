package com.rise.gateway.service.route;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rise.gateway.model.route.SysPredicates;

import java.util.List;

/**
 * <p>
 * 网关断言表 服务类
 * </p>
 *
 * @author 张牧之
 * @since 2022-12-06
 */
public interface SysPredicatesService extends IService<SysPredicates> {
    /**
     * 根据路由id查询
     * @param ids
     * @return
     */
    List<SysPredicates> queryByRouteIds (List<Long> ids);
}
