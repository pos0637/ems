package com.furongsoft.ems.configurations;

import com.furongsoft.base.rbac.filters.CorsFilter;
import com.furongsoft.base.rbac.filters.JwtFilter;
import com.furongsoft.base.rbac.security.MyRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
    @Value("${resources.url}")
    private String resourcesUrl;

    @Value("${upload.url}")
    private String uploadUrl;

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
        Map<String, Filter> filters = new HashMap<>(2);
        filters.put("cors", new CorsFilter());
        filters.put("jwt", new JwtFilter());
        shiroFilterFactoryBean.setFilters(filters);

        // 过滤器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        // 静态资源
        filterChainDefinitionMap.put(resourcesUrl + "/**", "anon");
        // 附件资源
        filterChainDefinitionMap.put(uploadUrl + "/**", "anon");
        // 数据库连接池监控
        filterChainDefinitionMap.put("/druid/**", "anon");
        // 首页
        filterChainDefinitionMap.put("/home/**", "anon");
        // CMS
        filterChainDefinitionMap.put("/cms/**", "anon");
        // 后台管理系统
        filterChainDefinitionMap.put("/admin/**", "anon");

        // 文件上传
        filterChainDefinitionMap.put("/file/**", "cors, authc");
        // RESTful API
        // filterChainDefinitionMap.put("/api/**", "noSessionCreation, jwt");
        filterChainDefinitionMap.put("/api/**", "cors");

        // 首页
        filterChainDefinitionMap.put("/", "anon");
        // 登录
        filterChainDefinitionMap.put("/api/v1/system/login", "cors, anon");
        // 注销
        filterChainDefinitionMap.put("/security/logout", "logout");
        // 默认
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setLoginUrl("/ui/build/index.html");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403.html");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }
}
