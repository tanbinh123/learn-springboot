package com.sn.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${system.user.password.secret}")
    private String secret;

    @Autowired
    private DataSource dataSource;

    private static final String userQuery = "select name, password, available from s_user where name = ?";
    private static final String roleQuery = "select u.name, r.role_name " +
            "from s_user u, s_role r, s_user_role ur " +
            "where u.id = ur.user_id and r.id = ur.role_id and u.name = ?";

    /**
     * 定义用户名、密码、角色等
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        // 使用数据库定义用户认证服务

        // 密码编码器
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder(secret);

        auth.jdbcAuthentication()
                // 设置密码编码器
                .passwordEncoder(passwordEncoder)
                // 设置数据源
                .dataSource(dataSource)
                // 查询用户，自动判断密码是否一致
                .usersByUsernameQuery(userQuery)
                // 赋予用户对应的权限
                .authoritiesByUsernameQuery(roleQuery);
    }

    /**
     * 指定用户、角色与对应URL的访问权限
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http.authorizeRequests()
                // 将/sn/blog/**、/sn/user/**（Ant风格的路径配置）路径的访问权限赋予给角色USER、ADMIN
                // hasAnyRole方法会默认在角色前添加ROLE_
//                .antMatchers("/sn/blog/**", "/sn/user/**").hasAnyRole("USER", "ADMIN")
//                .antMatchers("/sn/admin/**").hasAuthority("ROLE_ADMIN")
                // 使用Spring EL配置那些角色可以访问指定路径
                // 有USER或ADMIN角色的用户才可以访问
                .antMatchers("/upload.html").access("hasRole('USER') or hasRole('ADMIN')")
                // 用户有ROLE_ADMIN角色，并且是完整登录而不是通过remember me，才可以访问
                .antMatchers("/upload3.html").access("hasAuthority('ROLE_ADMIN')")
                // 所有用户都可以访问登录页面
                .antMatchers("/admin/login","/admin/logout_result").permitAll()
                .anyRequest().authenticated()

//                .and()
                // 允许匿名访问没有配置过权限的路径
                // 注意，由于未配置匿名访问的路径，会和前边的访问限制冲突，基于先配置优先的原则，则会采用前边的配置
//                .anonymous()

                .and()
//                // 使用记住我的功能，避免每次都输入密码
//                // 有效时间86400秒=1天，保存到cookie中的键是remember-me-key
                .rememberMe().tokenValiditySeconds(120).key("remember-me-key")
//
                .and()
                // 只配置formLogin则使用默认的登录页面，loginPage配置自定义登录页面，defaultSuccessUrl为登录成功的跳转页面
                .formLogin()
                .loginPage("/admin/login").defaultSuccessUrl("/main/index")

                .and()
//                // 配置登出页面及其跳转页面，登出的请求需要是POST
                .logout().logoutUrl("/admin/logout").logoutSuccessUrl("/admin/logout_result")

                .and()
                // 启用浏览器的HTTP基础验证
                .httpBasic()
                .and()
                .csrf().disable();

    }
}
