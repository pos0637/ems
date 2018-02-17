package com.furongsoft.ems.configurations;

import com.furongsoft.base.rbac.security.JwtFilter;
import com.furongsoft.base.rbac.security.MyRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro配置文件
 *
 * @author Alex
 */
@Configuration
public class ShiroConfiguration {
    @Bean
    public SecurityManager securityManager(MyRealm myRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm);

        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 设置过滤器
        Map<String, Filter> filters = new HashMap<>(1);
        filters.put("jwt", new JwtFilter());
        shiroFilterFactoryBean.setFilters(filters);

        // 过滤器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/ui/**", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/home/**", "anon");
        filterChainDefinitionMap.put("/api/**", "noSessionCreation, jwt");
        filterChainDefinitionMap.put("/**", "authc");
        filterChainDefinitionMap.put("/security/logout", "logout");

        shiroFilterFactoryBean.setLoginUrl("/ui/build/index.html");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403.html");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }
}
