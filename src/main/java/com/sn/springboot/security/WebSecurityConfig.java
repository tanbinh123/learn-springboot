package com.sn.springboot.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${system.user.password.secret}")
    private String secret;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserService userService;

    @Autowired
    private MyFilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource;

    @Autowired
    private MyAccessDecisionManager myAccessDecisionManager;

    private static final String userQuery = "select name, password, enabled from s_user where name = ?";
    private static final String roleQuery = "select u.name, r.role_code " +
            "from s_user u, s_role r, s_user_role ur " +
            "where u.id = ur.user_id and r.id = ur.role_id and u.name = ?";

    @Bean
    public JdbcTokenRepositoryImpl jdbcTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new Pbkdf2PasswordEncoder(secret);
//    }

    /**
     * 角色继承
     * ？？？测试发现使用动态权限时，角色继承失效
     */
    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ROLE_ADMIN > ROLE_SUPPORTER \n ROLE_SUPPORTER > ROLE_USER";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        // 使用动态权限配置时，如果自定义登录页面，要配置这个，防止登录页面被拦截
        web.ignoring().antMatchers("/login");
        // 不拦截静态资源
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
    }

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

//        auth.jdbcAuthentication()
        // 设置密码编码器
//                .passwordEncoder(passwordEncoder)
        // 设置数据源
//                .dataSource(dataSource);
        // 查询用户，自动判断密码是否一致
//                .usersByUsernameQuery(userQuery)
        // 赋予用户对应的权限
//                .authoritiesByUsernameQuery(roleQuery);

        // 这种用法需要使用passwordEncoder来配置加密
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);

//        auth.inMemoryAuthentication()
//                .passwordEncoder(passwordEncoder)
//
//                .withUser("zhangsan")
//                .password("fd4aa356ab2efcacf0fabfdd25a12775a9e0a257801559143ed61acf6714924b0ff4913356d00f4e")
//                .roles("ADMIN", "USER")
//
//                .and()
//                .withUser("lisi")
//                .password("fd4aa356ab2efcacf0fabfdd25a12775a9e0a257801559143ed61acf6714924b0ff4913356d00f4e")
//                .roles("USER")
//
//                .and()
//                .withUser("wangwu")
//                .password("fd4aa356ab2efcacf0fabfdd25a12775a9e0a257801559143ed61acf6714924b0ff4913356d00f4e")
//                .roles("SUPPORTER");


    }

//    @Override
//    protected UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager memoryUserDetailsManager = new InMemoryUserDetailsManager();
//        memoryUserDetailsManager.createUser(User.withUsername("zhangshan")
//                .password("fd4aa356ab2efcacf0fabfdd25a12775a9e0a257801559143ed61acf6714924b0ff4913356d00f4e").roles("ADMIN").build());
//        memoryUserDetailsManager.createUser(User.withUsername("lisi")
//                .password("fd4aa356ab2efcacf0fabfdd25a12775a9e0a257801559143ed61acf6714924b0ff4913356d00f4e").roles("USER").build());
//        return memoryUserDetailsManager;
//    }

//    @Override
//    protected UserDetailsService userDetailsService() {
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        jdbcUserDetailsManager.createUser(User.withUsername("zhangshan")
//                .password("fd4aa356ab2efcacf0fabfdd25a12775a9e0a257801559143ed61acf6714924b0ff4913356d00f4e").roles("ADMIN").build());
//        jdbcUserDetailsManager.createUser(User.withUsername("lisi")
//                .password("fd4aa356ab2efcacf0fabfdd25a12775a9e0a257801559143ed61acf6714924b0ff4913356d00f4e").roles("USER").build());
//        return jdbcUserDetailsManager;
//    }

    /**
     * 指定用户、角色与对应URL的访问权限
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http
                // 通过签名的请求
                .authorizeRequests()
//                // 将/sn/blog/**、/sn/user/**（Ant风格的路径配置）路径的访问权限赋予给角色USER、ADMIN
//                // hasAnyRole方法会默认在角色前添加ROLE_
//                .antMatchers("/user/**", "/user/**").hasAnyRole("USER", "ADMIN")
//                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
//                .antMatchers("/supporter/**").hasAuthority("ROLE_SUPPORTER")
//                // 是完整登录而不是通过remember me，才可以访问，目的是为了安全需要进行二次校验
//                .antMatchers("/main/hello").fullyAuthenticated()
//                .antMatchers("/verifyCode").permitAll()

                // 使用Spring EL配置那些角色可以访问指定路径
                // 有USER或ADMIN角色的用户才可以访问
//                .antMatchers("/user/hello").access("hasRole('USER') or hasRole('ADMIN')")
//                .antMatchers("/admin/hello").access("hasAuthority('ROLE_ADMIN')")
//                .antMatchers("/supporter/hello").access("hasAuthority('ROLE_SUPPORTER')")
                // 所有用户都可以访问登录页面、退出后的提示页面
//                .antMatchers("/login", "/logout_result").permitAll()

                // 动态配置角色的访问权限
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(myAccessDecisionManager);
                        o.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);
                        return o;
                    }
                })
                // 其它任意请求登录后就可以访问
                .anyRequest().authenticated()

//                .and()
                // 允许匿名访问没有配置过权限的路径
                // 注意，由于未配置匿名访问的路径，会和前边的访问限制冲突，基于先配置优先的原则，则会采用前边的配置
//                .anonymous()

                // 只配置formLogin则使用默认的登录页面，loginPage配置自定义登录页面的接口，
                // 前后端分离时，loginPage配置的接口可以返回JSON数据来提示客户端需要登录，
                // defaultSuccessUrl可以配置登录成功的跳转页面，但这里分两种情况：
                // 1：直接从登录地址登录成功后，会跳转到defaultSuccessUrl配置的页面
                // 2：访问其它地址被重定向到登录页面，然后登录成功后，不会跳转到defaultSuccessUrl配置的页面，而是登录页面的前一个页面
                // successForwardUrl可以保证登录成功后跳转到其指定的页面路径
                .and()
                .formLogin()
                // /login表示登陆页面接口，同时也表示默认登录的接口也是/login（可以用loginProcessingUrl配置）
                // 使用动态权限配置时，如果自定义登录页面，一定要配置loginProcessingUrl
                .loginPage("/login")
                .loginProcessingUrl("/doLogin")
                .defaultSuccessUrl("/main/index")
//                .usernameParameter("name")
//                .passwordParameter("pwd")
                // 登录页面不做访问控制
                .permitAll()

                // 前后端分离时，登录成功要返回JSON格式数据的回调
//                .successHandler(new AuthenticationSuccessHandler() {
//                    @Override
//                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth) throws IOException, ServletException {
//                        resp.setContentType("application/json;charset=utf-8");
//                        PrintWriter pw = resp.getWriter();
//                        HashMap<String, Object> result = new HashMap<>();
//                        result.put("status", 200);
//                        result.put("data", auth.getPrincipal());
//                        result.put("msg", "登录成功");
//                        pw.write(new ObjectMapper().writeValueAsString(result));
//                        pw.flush();
//                        pw.close();
//                    }
//                })

                // AbstractUserDetailsAuthenticationProvider，在这个类里进行登录异常处理
                // 前后端分离时， 登录失败要返回JSON格式数据的回调
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter pw = resp.getWriter();
                        HashMap<String, Object> result = new HashMap<>();
                        result.put("status", 401);
                        result.put("data", "");
                        String msg = "";
                        if (e instanceof LockedException) {
                            msg = "账号被锁";
                        } else if (e instanceof DisabledException) {
                            msg = "账号被禁用";
                        } else if (e instanceof BadCredentialsException) {
                            msg = "用户名或密码错误";
                        } else if (e instanceof CredentialsExpiredException) {
                            msg = "密码过期";
                        } else {
                            msg = "登录失败";
                        }
                        result.put("msg", msg);
                        pw.write(new ObjectMapper().writeValueAsString(result));
                        pw.flush();
                        pw.close();
                    }
                })

                .and()
                // 配置登出页面及其跳转页面
                // SecurityContextLogoutHandler
                .logout()
                // logoutUrl方法的参数默认就是/logout，默认get请求；可通过logoutRequestMatcher实现post方式
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
                .logoutUrl("/logout")
//                .logoutSuccessUrl("/logout_result")
                .permitAll()

                // 前后端分离时，退出登录成功后要返回JSON格式数据的回调
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter pw = resp.getWriter();
                        HashMap<String, Object> result = new HashMap<>();
                        result.put("status", 200);
                        result.put("data", "");
                        result.put("msg", "退出成功");
                        pw.write(new ObjectMapper().writeValueAsString(result));
                        pw.flush();
                        pw.close();
                    }
                })

                // 前后端分离时，未登录时的处理
//                .and()
//                .exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
//            @Override
//            public void commence(HttpServletRequest httpServletRequest, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
//                resp.setContentType("application/json;charset=utf-8");
//                PrintWriter pw = resp.getWriter();
//                HashMap<String, Object> result = new HashMap<>();
//                result.put("status", 401);
//                result.put("data", "");
//                result.put("msg", "请先登录");
//                pw.write(new ObjectMapper().writeValueAsString(result));
//                pw.flush();
//                pw.close();
//            }
//        })

                .and()
                // 使用记住我的功能，避免每次都输入密码
                // TokenBasedRememberMeServices类 会根据配置的信息在登录后生成对应cookie
                // 之后的请求RememberMeAuthenticationFilter 会解析cookie
                // 有效时间86400秒=1天，保存到cookie中的键是remember-me
                .rememberMe()
//                .rememberMeParameter("my-remember-me")
//                .userDetailsService(userService)
                .tokenValiditySeconds(2 * 60).key("hello")
                // 配置token持久化, PersistentTokenBasedRememberMeServices
                .tokenRepository(jdbcTokenRepository())

                .and()
                // 启用浏览器的HTTP基础验证
                .httpBasic()
                .and()
                .csrf().disable()

                // 踢掉已登录的用户
                .sessionManagement()
                .maximumSessions(1)
                // 一个浏览器登录成功后，另一个禁止登录，（需要使用踢掉已登录的用户的配置）
                .maxSessionsPreventsLogin(true)
        ;


//        http.addFilterAt(myUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    /**
     * ???配合maxSessionsPreventsLogin
     * @return
     */
    @Bean
    HttpSessionEventPublisher httpSessionEventPublisher(){
        return new HttpSessionEventPublisher();
    }

    /**
     * 使用MyUsernamePasswordAuthenticationFilter拦截登录请求后，登录成功失败的处理也需要在这个方法里
     */
//    @Bean
//    public MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter() throws Exception {
//        MyUsernamePasswordAuthenticationFilter filter = new MyUsernamePasswordAuthenticationFilter();
//        filter.setAuthenticationManager(authenticationManagerBean());
//        return filter;
//    }
}
