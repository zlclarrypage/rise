package com.rise.gateway.service.route.impl;

import com.rise.gateway.dao.route.SysPredicatesMapper;
import com.rise.gateway.model.route.SysPredicates;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rise.gateway.service.route.SysPredicatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 网关断言表 服务实现类
 * </p>
 *
 * @author 张牧之
 * @since 2022-12-06
 */
@Service
public class SysPredicatesServiceImpl extends ServiceImpl<SysPredicatesMapper, SysPredicates> implements SysPredicatesService {

    @Autowired(required = false)
    private SysPredicatesMapper sysPredicatesMapper;

    /**
     * 根据路由id查询
     * @param ids
     * @return
     */
    @Override
    public List<SysPredicates> queryByRouteIds(List<Long> ids) {
        return sysPredicatesMapper.queryByRouteIds(ids);
    }
}
