package com.example.universe.security;

import com.example.universe.entity.User;
import com.example.universe.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthProviderImpl implements AuthenticationProvider {
    @Autowired
    private UserRepo userRepo;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("tyt "+authentication.getName()+" "+authentication.getCredentials().toString());
        String login = authentication.getName();
        User user = userRepo.findByUsername(login).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + login));;
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        String password = authentication.getCredentials().toString();
        if(!password.equals(user.getPassword())){
            throw new BadCredentialsException("Bad credential");
        }
        List<GrantedAuthority> authority = new ArrayList<>();
        return new UsernamePasswordAuthenticationToken(user,null,authority);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
