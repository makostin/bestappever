package com.mmf.business.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author svetlana.voyteh
 * @date: 1/21/12
 */
public class User implements UserDetails, DomainClass<Long>{

    private static final long serialVersionUID = -61698930484704677L;

    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private String fullName;
    private String login;
    private String password;
    private String passwordSalt;
    private String passwordFormat;
    private Boolean isAdmin;
    private Collection<? extends GrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();

    public User(){}

    public User(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @XmlTransient
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @XmlTransient
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @XmlTransient
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @XmlTransient
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @XmlTransient
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @XmlTransient
    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    @XmlTransient
    public String getPasswordFormat() {
        return passwordFormat;
    }

    public void setPasswordFormat(String passwordFormat) {
        this.passwordFormat = passwordFormat;
    }


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
