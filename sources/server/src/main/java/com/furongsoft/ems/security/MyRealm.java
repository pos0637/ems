package com.furongsoft.ems.security;

import com.furongsoft.ems.entities.User;
import com.furongsoft.ems.repositories.UserRepository;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 领域
 *
 * @author Alex
 */
@Component
public class MyRealm extends AuthorizingRealm {
    private final UserRepository mUserRepository;

    @Autowired
    public MyRealm(UserRepository userRepository) {
        mUserRepository = userRepository;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = mUserRepository.findByUserName(token.getUsername());
        if (user == null) {
            throw new UnknownAccountException();
        }

        return new SimpleAuthenticationInfo(user.getName(), user.getPassword(), getName());
    }
}