package com.mmf.rest.response.student;

import com.mmf.business.domain.Group;
import com.mmf.business.domain.Student;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * User: svetlana.voyteh
 * Date: 19.05.13
 */
@XmlRootElement
public class StudentResponse {

    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private String fullName;
    private String login;
    private String password;
    private Boolean isAdmin;
    private Group group;
    private Integer yearOfEntrance;
    private Boolean isPraepostor;

    public StudentResponse() {
    }

    public StudentResponse(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.surname = student.getSurname();
        this.patronymic = student.getPatronymic();
        this.fullName = student.getFullName();
        this.login = student.getLogin();
        this.password = student.getPassword();
        this.isAdmin = student.getAdmin();
        this.group = student.getGroup();
        this.yearOfEntrance = student.getYearOfEntrance();
        this.isPraepostor = student.getPraepostor();
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Integer getYearOfEntrance() {
        return yearOfEntrance;
    }

    public void setYearOfEntrance(Integer yearOfEntrance) {
        this.yearOfEntrance = yearOfEntrance;
    }

    public Boolean getPraepostor() {
        return isPraepostor;
    }

    public void setPraepostor(Boolean praepostor) {
        isPraepostor = praepostor;
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
}
