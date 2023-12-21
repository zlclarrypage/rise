package com.rise.authorize.model.user.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * @author 张牧之
 * @date 2022-12-08 23:53:41
 * @Email zhanglichang99@gmail.com
 */

@Data
public class SysUserInfo extends User {
    /**
     * 用户id
     */
    private final Long userId;
    /**
     * 租户ID
     */
    private final String tenantId;


    // 模拟数据库存储的用户信息
    public static Map<String, SysUserInfo> SysUserInfos;



    static {
        SysUserInfos = new HashMap<>();
        SysUserInfos.put("whq1", new SysUserInfo("whq1", "123"));
        SysUserInfos.put("whq2", new SysUserInfo("whq2", "123"));
        SysUserInfos.put("18788888888", new SysUserInfo("whq1", "123"));
        SysUserInfos.put("18666666666", new SysUserInfo("whq2", "123"));
    }



    // 用户构造函数
    public SysUserInfo(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Long userId, String tenantId) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
        this.tenantId = tenantId;
    }







    /**
     * 方便测试用的构造函数
     * 除用户名及密码外其他信息一致
     */
    public SysUserInfo(String username, String password) {
        // authorities 参数为用户角色相关，如果没有使用spring security进行角色权限控制，则不需要配置此参数
        this(username, password, true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("USER,AMDIN"), 1L, "000000");
    }


    // 模拟数据库通过用户名查询用户信息
    public static SysUserInfo getUser(String userName) {
        return SysUserInfos.get(userName);
    }

    // 模拟数据库通过手机号查询用户信息
    public static SysUserInfo getUserByPhone(String userName) {
        return SysUserInfos.get(userName);
    }
}
