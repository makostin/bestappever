package com.mmf.rest.util;

import com.mmf.business.domain.Lecturer;
import com.mmf.business.domain.Student;
import com.mmf.business.domain.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * svetlana.voyteh
 * 21/05/13
 */
public class UserRoleUtil {

    public static void setRoles(User user){
        ((LinkedList<SimpleGrantedAuthority>)user.getAuthorities()).add(new SimpleGrantedAuthority("ROLE_USER"));
        if (user.getAdmin() != null && user.getAdmin()){
            ((LinkedList<SimpleGrantedAuthority>)user.getAuthorities()).add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
    }

    public static void setRoles(Student student){
        ((LinkedList<SimpleGrantedAuthority>)student.getAuthorities()).add(new SimpleGrantedAuthority("ROLE_STUDENT"));
        ((LinkedList<SimpleGrantedAuthority>)student.getAuthorities()).add(new SimpleGrantedAuthority("ROLE_USER"));
        if (student.getAdmin() != null && student.getAdmin()){
            ((LinkedList<SimpleGrantedAuthority>)student.getAuthorities()).add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        if (student.getPraepostor() != null && student.getPraepostor()){
            ((LinkedList<SimpleGrantedAuthority>)student.getAuthorities()).add(new SimpleGrantedAuthority("ROLE_PRAEPOSTOR"));
        }
    }

    public static void setRoles(Lecturer lecturer){
        ((LinkedList<SimpleGrantedAuthority>)lecturer.getAuthorities()).add(new SimpleGrantedAuthority("ROLE_LECTURER"));
        ((LinkedList<SimpleGrantedAuthority>)lecturer.getAuthorities()).add(new SimpleGrantedAuthority("ROLE_USER"));
        if (lecturer.getAdmin() != null && lecturer.getAdmin()){
            ((LinkedList<SimpleGrantedAuthority>)lecturer.getAuthorities()).add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
    }
}
