package com.example.helloWorld.security;

import com.example.helloWorld.error.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

@Component
public class MyAccessDecisionManager implements AccessDecisionManager {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {

        logger.info("MyAccessDecisionManager::decide.");

        if (null == collection || 0 >= collection.size()) {
            logger.info("MyAccessDecisionManager::decide. null or empty of collection");

            return;
        }
        else {
            String needRole;
            logger.info("MyAccessDecisionManager::decide." + collection.size());

            for(Iterator<ConfigAttribute> iter = collection.iterator(); iter.hasNext(); ) {
                needRole = iter.next().getAttribute();

                logger.info("MyAccessDecisionManager::decide.  " + needRole + " : " + authentication.getAuthorities().size() );


                for(GrantedAuthority ga : authentication.getAuthorities()) {

                    logger.info("MyAccessDecisionManager::decide.  " + needRole + " vs " + ga.getAuthority());

                    if(needRole.trim().equals(ga.getAuthority().trim())) {
                        return;
                    }
                }
            }
            throw new AccessDeniedException("当前访问没有权限");
        }
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
