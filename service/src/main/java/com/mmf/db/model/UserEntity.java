package com.mmf.db.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author svetlana.voyteh
 * @date: 1/21/12
 */
@Entity
@Table(name = "usermmf")
public class UserEntity implements EntityClass<Long>{

    private static final long serialVersionUID = 1459679457005279388L;
    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String passwordSalt;
    private String passwordFormat;
    private GroupEntity group;
    private UserRoleEntity userRole;
    private DepartmentEntity department;
    private Date dateOfEntrance;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_Group")
    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
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

    @ManyToOne
    @JoinColumn(name = "ID_UserRole")
    public UserRoleEntity getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoleEntity userRole) {
        this.userRole = userRole;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Department")
    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfEntrance() {
        return dateOfEntrance;
    }

    public void setDateOfEntrance(Date dateOfEntrance) {
        this.dateOfEntrance = dateOfEntrance;
    }
}
