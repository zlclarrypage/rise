package com.rise.authorize.service.user.impl;

import com.rise.authorize.model.user.entity.SysUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author 张牧之
 * @date 2022-12-08 23:50:19
 * @Email zhanglichang99@gmail.com
 */


@Service
public class RiseUserDetailsService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    // 后续登录使用此方法加载用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserInfo result = SysUserInfo.getUser(username);
        // 用户不存在，抛出异常
        if (result == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        // 模拟的用户查询数据为明文密码，实际使用时都是加密存储，此处手动加密模拟处理
        String encode = passwordEncoder.encode(result.getPassword());
        SysUserInfo res = new SysUserInfo(result.getUsername(), encode);
        return res;
    }
}
