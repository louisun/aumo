package com.louisun.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 添加自定义过滤器：这里是认证失败返回 JSON 信息，而非跳转到登录 url
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("authc", new CustomFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        // 添加自定义过滤器链
        Map<String, String> filterChainDefinitionMap  = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/*", "anon");
//        filterChainDefinitionMap.put("/login", "anon");
//        filterChainDefinitionMap.put("/register", "anon");
//        filterChainDefinitionMap.put("/logout", "anon");
//        filterChainDefinitionMap.put("/*", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;



    }

    @Bean(name="securityManager")
    public SecurityManager securityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // securityManager 中设置Realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 自定义 Realm
     **/
    @Bean(name="userRealm")
    UserRealm userRealm(@Qualifier("credentialsMatcher") HashedCredentialsMatcher matcher){
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(matcher);
        return userRealm;
    }

    /**
     * 自定义的哈希密码比较器
     **/
    @Bean(name="credentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        // 默认没有 salt、Hex 编码
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }


    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions)，需借助SpringAOP扫描使用Shiro注解的类，并在必要时进行安全逻辑验证
     * 配置以下两个bean（DefaultAdvisorAutoProxyCreator、AuthorizationAttributeSourceAdvisor）即可实现此功能
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
