package com.rise.authorize.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rise.authorize.dao.user.SysUserMapper;
import com.rise.authorize.model.user.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rise.authorize.service.user.SysUserService;
import com.rise.entity.BaseResult;
import com.rise.entity.QueryPage;
import com.rise.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * 用户登录信息service
 * @author 张牧之
 * @since 2022-12-05
 */


@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired(required = false)
    private SysUserMapper sysUserMapper;

    /**
     * 查看用户详情
     * @param id
     */
    @Override
    public BaseResult<SysUser> detail(Long id) {
        BaseResult<SysUser> result = new BaseResult<>();
        return result.success(getBaseMapper().selectById(id));
    }

    /**
     * 保存用户
     */
    @Override
    public BaseResult userSave(SysUser sysUser) {
        BaseResult result = new BaseResult();
        Boolean flag = saveOrUpdate(sysUser);
        if (flag) {
            return result.success();
        } else {
            return result.error();
        }
    }


    /**
     * 用户分页查询
     * @param userQueryPage
     * @return
     */
    @Override
    public BaseResult<Page<SysUser>> page(QueryPage<SysUser> userQueryPage) {
        BaseResult<Page<SysUser>> result = new BaseResult<>();
        Page<SysUser> page = new Page<>(userQueryPage.getCurrent(),userQueryPage.getSize());
        SysUser param = userQueryPage.getParam();
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        if (StringUtil.isNotEmpty(param.getUserName())) {
            wrapper.eq("userName",param.getUserName());
        }
        if (StringUtil.isNotEmpty(param.getUserNo())) {
            wrapper.eq("userNo",param.getUserNo());
        }
        if (StringUtil.isNotEmpty(param.getUserStatus())) {
            wrapper.eq("userStatus",param.getUserStatus());
        }
        if (StringUtil.isNotEmpty(param.getLoginStatus())) {
            wrapper.eq("loginStatus",param.getLoginStatus());
        }
        return result.success(page(page,wrapper));
    }


    /**
     * 删除用户
     * @param id
     */
    @Override
    public BaseResult delete(Long id) {
        BaseResult result = new BaseResult();
        int num = getBaseMapper().deleteById(id);
        if (num != 0) {
            return result.success();
        } else {
            return result.error();
        }
    }


    @Override
    public int queryCount(String userNo) {
        return sysUserMapper.queryCount(userNo);
    }
}
