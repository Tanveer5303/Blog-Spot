package com.tanveer.blog.springbootblogrestapi.Security;

import com.tanveer.blog.springbootblogrestapi.entity.Role;
import com.tanveer.blog.springbootblogrestapi.entity.User;
import com.tanveer.blog.springbootblogrestapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameorEmail) throws UsernameNotFoundException {
        //write the logic to load the user from database
        User user =  userRepository.findByUsernameOrEmail(usernameorEmail,usernameorEmail).orElseThrow(
                ()-> new UsernameNotFoundException("User Not Found With Username or Email :"+ usernameorEmail)
        );

        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),mapRolesToAuthorities(user.getRoles()));
    }

    //private method to map set of roles to granted authority
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
       return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
