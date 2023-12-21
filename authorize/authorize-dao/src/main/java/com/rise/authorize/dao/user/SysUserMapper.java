package com.rise.authorize.dao.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rise.authorize.model.user.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 张牧之
 * @since 2022-12-05
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    int queryCount (String userNo);
}
