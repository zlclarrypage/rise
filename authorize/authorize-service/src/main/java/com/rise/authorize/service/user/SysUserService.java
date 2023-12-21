package com.rise.authorize.service.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rise.authorize.model.user.entity.SysUser;
import com.rise.common.entity.BaseResult;
import com.rise.common.entity.QueryPage;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张牧之
 * @since 2022-12-05
 */


public interface SysUserService extends IService<SysUser> {
    /**
     * 查看单个用户
     */
    BaseResult<SysUser> detail (Long id);

    /**
     * 保存用户
     */
    BaseResult userSave (SysUser sysUser);


    /**
     * 分页查询用户信息
     * @return
     */
    BaseResult<Page<SysUser>> page (QueryPage<SysUser> userQueryPage);


    /**
     * 删除用户
     * @param id
     */
    BaseResult delete (Long id);

    int queryCount (String userNo);
}
