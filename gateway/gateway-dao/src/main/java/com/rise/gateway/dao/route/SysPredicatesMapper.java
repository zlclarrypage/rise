package com.rise.gateway.dao.route;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rise.gateway.model.route.SysPredicates;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 网关断言表 Mapper 接口
 * </p>
 *
 * @author 张牧之
 * @since 2022-12-06
 */

@Mapper
public interface SysPredicatesMapper extends BaseMapper<SysPredicates> {
    /**
     * 根据路由id查询
     * @param ids
     * @return
     */
    List<SysPredicates> queryByRouteIds (List<Long> ids);
}
