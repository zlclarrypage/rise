package com.rise.gateway.dao.route;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rise.gateway.model.route.SysRoute;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 网关路由表 Mapper 接口
 * </p>
 *
 * @author 张牧之
 * @since 2022-12-06
 */

@Mapper
public interface SysRouteMapper extends BaseMapper<SysRoute> {

}
