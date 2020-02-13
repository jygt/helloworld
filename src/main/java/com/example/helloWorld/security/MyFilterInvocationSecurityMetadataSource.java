package com.example.helloWorld.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component


public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    /**
     * 每一个资源所需要的角色 Collection<ConfigAttribute>决策器会用到
     */
    private static HashMap<String, Collection<ConfigAttribute>> map =null;


    private Logger logger = LoggerFactory.getLogger(this.getClass());



    @Autowired
    private PermissionService objPermissionService;

    @Autowired
    private  RolePermissionService objRolePermissionService;

    /**
     * 初始化 所有资源 对应的角色
     */
    public void loadResourceDefine() {

        StackTraceElement[] stackTrace = new Throwable().getStackTrace();

        logger.info("MyFilterInvocationSecurityMetadataSource::loadResourceDefine"+stackTrace[1].getFileName());

        map = new HashMap<>(16);
        //权限资源 和 角色对应的表  也就是 角色权限 中间表
        List<Permission> lstPermission = objPermissionService.findAll();

        //某个资源 可以被哪些角色访问
        for (Permission itm : lstPermission) {



            String url = itm.getUrl();
            String name = itm.getName();
            String id = itm.getId();

            List<RolePermission> lstRolePermisson = objRolePermissionService.findByPermissionId(id);

            for(RolePermission itm_itm:lstRolePermisson) {
                logger.info("MyFilterInvocationSecurityMetadataSource::loadResourceDefine " + url + " : " + itm_itm.getRoleId());

                ConfigAttribute role = new SecurityConfig(itm_itm.getRoleId());

                if (map.containsKey(url)) {
                    map.get(url).add(role);
                } else {
                    List<ConfigAttribute> list = new ArrayList<>();
                    list.add(role);
                    map.put(url, list);
                }
            }
        }
    }


    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {

        logger.info("MyFilterInvocationSecurityMetadataSource::getAttributes");

        if (null == map) {
            loadResourceDefine();
        }
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();

        for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
            String url = it.next();

            logger.info("MyFilterInvocationSecurityMetadataSource::getAttributes "+url + " vs " + request.getRequestURI());

            if (new AntPathRequestMatcher( url ).matches( request )) {

                logger.info("MyFilterInvocationSecurityMetadataSource::getAttributes "+url + map.get(url).toString()) ;
                return map.get( url );
            }
        }
        logger.info("MyFilterInvocationSecurityMetadataSource::getAttributes " + "null");
        // 对于没有纳入配置的URL，默认不做处理。允许访问的
        //throw new AccessDeniedException("资源没有配置：当前访问没有权限");
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }
}
