package com.sn.springboot.security;

import com.sn.springboot.pojo.Menu;
import com.sn.springboot.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

@Component
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    // Ant风格的路径匹配
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private MenuService menuService;

    /**
     * 根据请求的地址判断需要哪些角色
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 请求的地址
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        // 这一步可以使用Redis缓存
        List<Menu> menus = menuService.getAllMenus();
        for (Menu menu : menus) {
            // 请求地址和menu中的路径匹配
            if (antPathMatcher.match(menu.getPattern(), requestUrl)) {
                List<Role> roles = menu.getRoles();
                // 将角色集合转为角色编码数组
//                String[] roleCodes = roles.stream().map(Role::getRoleCode).toArray(String[]::new);
                String[] roleCodes = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    roleCodes[i] = roles.get(i).getRoleName();
                }
                return SecurityConfig.createList(roleCodes);
            }
        }
        // 如果前边未匹配到路径，则做如下返回，相当于一个标记，后续会根据这个标记让用户登录后才可以访问资源
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
