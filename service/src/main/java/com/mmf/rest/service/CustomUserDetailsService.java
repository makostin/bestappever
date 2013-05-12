package com.mmf.rest.service;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.LecturerService;
import com.mmf.business.StudentService;
import com.mmf.business.UserService;
import com.mmf.business.domain.Lecturer;
import com.mmf.business.domain.Student;
import com.mmf.business.domain.User;
import com.mmf.rest.RestServiceException;
import org.apache.commons.httpclient.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * svetlana.voyteh
 * 30.04.13
 */
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private LecturerService lecturerService;
    @Autowired
    private DaoAuthenticationProvider daoAuthenticationProvider;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user;
        try {
            user = userService.getUser(username);
            if (user == null) {
                throw new UsernameNotFoundException(null);
            }
            return buildUserFromUserEntity(user);
        } catch (BusinessServiceException e) {
            throw new RestServiceException(HttpStatus.SC_UNAUTHORIZED);
        }
    }


    private UserDetails buildUserFromUserEntity(User user) throws BusinessServiceException {

//        String username = user.getLogin();
//        String password = user.getPassword();
//
//        boolean enabled = true;
//        boolean accountNonExpired = true;
//        boolean credentialsNonExpired = true;
//        boolean accountNonLocked = true;

        // Add user role access rights
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        if (studentService.get(user.getId()) != null){
            ((ArrayList<SimpleGrantedAuthority>)user.getAuthorities()).add(new SimpleGrantedAuthority("ROLE_STUDENT"));
        }

        if (lecturerService.get(user.getId()) != null){
            ((ArrayList<SimpleGrantedAuthority>)user.getAuthorities()).add(new SimpleGrantedAuthority("ROLE_LECTURER"));
        }

        if (user.getAdmin()){
            ((ArrayList<SimpleGrantedAuthority>)user.getAuthorities()).add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        ((ArrayList<SimpleGrantedAuthority>)user.getAuthorities()).add(new SimpleGrantedAuthority("ROLE_USER"));

        // Return user data
        return user;
//        return new org.springframework.security.core.userdetails.User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
