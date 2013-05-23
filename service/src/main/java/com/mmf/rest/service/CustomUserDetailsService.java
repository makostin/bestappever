package com.mmf.rest.service;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.LecturerService;
import com.mmf.business.StudentService;
import com.mmf.business.UserService;
import com.mmf.business.domain.User;
import com.mmf.rest.RestServiceException;
import org.apache.commons.httpclient.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

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
        // Add user role access rights
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        if (studentService.get(user.getId()) != null) {
            ((LinkedList<SimpleGrantedAuthority>) user.getAuthorities()).add(new SimpleGrantedAuthority("ROLE_STUDENT"));
        }

        if (lecturerService.get(user.getId()) != null) {
            ((LinkedList<SimpleGrantedAuthority>) user.getAuthorities()).add(new SimpleGrantedAuthority("ROLE_LECTURER"));
        }

        if (user.getAdmin()) {
            ((LinkedList<SimpleGrantedAuthority>) user.getAuthorities()).add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        ((LinkedList<SimpleGrantedAuthority>) user.getAuthorities()).add(new SimpleGrantedAuthority("ROLE_USER"));

        return user;
    }
}
