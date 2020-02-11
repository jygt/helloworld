package com.example.helloWorld.security;

import com.example.helloWorld.error.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service

public class CustomUserService implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserService objUserService;

    @Resource
    private UserRoleService objUserRoleService;

    @Resource
    private RoleService objRoleService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        logger.info("loadUserByUsername:"+name);
        User objUser = objUserService.findByName(name);

        if(null == objUser){
            throw new BusinessException("User does not exist.");
        }

        List<UserRole> lstUserRole = objUserRoleService.findByUserId(objUser.getId());

        List<GrantedAuthority> lstGrantedAuthority = new ArrayList<GrantedAuthority>();

        if((null != lstUserRole) && lstUserRole.size()>0){
            for(UserRole itm : lstUserRole){

                logger.info("loadUserByUsername:"+name + " "+objUser.getId() + " vs " + itm.getUserId()+" - " + itm.getRoleId() + " in "+ lstUserRole.size());
                String roleName = itm.getRoleId();
                logger.info(itm.getRoleId() + " : "+itm.getUserId() );
                lstGrantedAuthority.add(new SimpleGrantedAuthority(roleName));
            }
        }

        return new org.springframework.security.core.userdetails.User(objUser.getName(),
                objUser.getPassword(),lstGrantedAuthority);

    }


}
