package com.mmf.rest.response.lecturer;

import com.mmf.business.domain.Department;
import com.mmf.business.domain.Group;
import com.mmf.business.domain.Lecturer;
import com.mmf.business.domain.Student;
import org.springframework.security.core.GrantedAuthority;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.LinkedList;

/**
 * User: svetlana.voyteh
 * Date: 19.05.13
 */
@XmlRootElement
public class LecturerResponse {

    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private String fullName;
    private String login;
    private String password;
    private Boolean isAdmin;
    private Department department;
    private Collection<GrantedAuthority> authorities = new LinkedList<GrantedAuthority>();

    public LecturerResponse() {
    }

    public LecturerResponse(Lecturer lecturer) {
        this.id = lecturer.getId();
        this.name = lecturer.getName();
        this.surname = lecturer.getSurname();
        this.patronymic = lecturer.getPatronymic();
        this.fullName = lecturer.getFullName();
        this.login = lecturer.getLogin();
        this.password = lecturer.getPassword();
        this.isAdmin = lecturer.getAdmin();
        this.department = lecturer.getDepartment();
        this.authorities.addAll(lecturer.getAuthorities());
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
