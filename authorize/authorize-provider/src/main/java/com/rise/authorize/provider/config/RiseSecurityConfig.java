package com.rise.authorize.provider.config;

import com.rise.authorize.service.user.impl.RiseUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * rise security 安全配置
 * @author 张牧之
 * @date 2022-12-08 23:28:11
 * @Email zhanglichang99@gmail.com
 */



@Configuration
@EnableWebSecurity
public class RiseSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private RiseUserDetailsService riseUserDetailsService;


    /**
     * 加密方式
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * 初始化security的认证管理器
     * @return
     * @throws Exception
     */
    @Bean("authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    /**
     * 账号密码登录设置获取用户UserDetailsService
     * 如果所有登录方式获取用户的方式一致，则可以在AuthorizationServerConfigurerAdapter的AuthorizationServerEndpointsConfigurer中设置UserDetailsService
     * @return
     */
    @Bean
    public DaoAuthenticationProvider getDaoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(riseUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }



    /**
     * 配置权限
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //1.配置基本认证方式
        http.authorizeRequests()
                // 角色为“ADMIN”的用户才可以访问/test/admin/相关的接口
                //.antMatchers("/test/admin/**").hasRole("ADMIN")
                // 角色为"USER"、“ADMIN”的用户才可以访问/test/user/相关的接口
                //.antMatchers("/test/user/**").hasAnyRole("USER", "ADMIN")
                // 所有用户都可以访问的接口
                .antMatchers("/swagger/**", "/oauth/token/**").permitAll()
                // 对任意请求都进行认证（其他路径的请求登录后才可以访问）
                .anyRequest()
                .authenticated()
                //开启basic认证
                .and().httpBasic()
                .and().formLogin();
    }


    /**
     * 导入用户配置
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(getDaoAuthenticationProvider());
    }
}
