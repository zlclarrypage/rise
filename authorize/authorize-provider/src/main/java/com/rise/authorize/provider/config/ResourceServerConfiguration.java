package com.rise.authorize.provider.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

/**
 * @author 张牧之
 * @date 2022-12-09 20:03:51
 * @Email zhanglichang99@gmail.com
 */



@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
        // 针对所有的请求
        .authorizeRequests()
        //.antMatchers("/sys-user/queryCount").permitAll()
        // 匹配规则：任何请求 需要登录认证
        .anyRequest().authenticated();
        //允许表单登录
        http.formLogin();
        // 开启httpBasic认证
        http.httpBasic();
        // 关闭csrf防护
        http.csrf().disable();
    }


    @Primary
    @Bean
    public RemoteTokenServices remoteTokenServices() {
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        // 设置授权服务器check_token端点完整地址
        remoteTokenServices.setCheckTokenEndpointUrl("http://localhost:9100/oauth/check_token");
        // 设置客户端id与secret，注意：client_secret值不能使用passwordEncoder加密！
        remoteTokenServices.setClientId("whqClient");
        remoteTokenServices.setClientSecret(passwordEncoder.encode("whqSecret"));
        return remoteTokenServices;
    }



    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        // 设置授权服务器的资源列表
        resources.resourceId("resource");
    }
}
