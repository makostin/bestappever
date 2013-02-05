package com.mmf.db.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author svetlana.voyteh
 * @date: 1/21/12
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usermmf")
public class UserEntity implements EntityClass<Long>{

    private static final long serialVersionUID = 1459679457005279388L;
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private String login;
    private String password;
    private String passwordSalt;
    private String passwordFormat;
    private Boolean isAdmin;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

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
}
