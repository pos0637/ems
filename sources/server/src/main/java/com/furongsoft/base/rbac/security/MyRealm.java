package com.furongsoft.base.rbac.security;

import com.furongsoft.base.rbac.entities.User;
import com.furongsoft.base.rbac.repositories.UserRepository;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
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
        super(new RetryLimitHashedCredentialsMatcher(null));
        mUserRepository = userRepository;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return true;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username;
        if (authenticationToken instanceof JwtToken) {
            JwtToken token = (JwtToken) authenticationToken;
            username = JwtUtils.getUsername(token.getToken());
            if (username == null) {
                throw new AuthenticationException();
            }
        } else if (authenticationToken instanceof UsernamePasswordToken) {
            UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
            username = token.getUsername();
        } else {
            throw new AuthenticationException();
        }

        User user = mUserRepository.findByUserName(username);
        if (user == null) {
            throw new UnknownAccountException();
        }

        return new SimpleAuthenticationInfo(user.getName(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
    }
}