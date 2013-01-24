package com.mmf.db.model;

import javax.persistence.*;
import java.util.Set;

/**
 * @author svetlana.voyteh
 * @date: 3/30/12
 */
@Entity
@Table(name = "department")
public class DepartmentEntity implements EntityClass<Long>{

    private static final long serialVersionUID = -6012109122619024379L;
    private Long id;
    private String name;
    private Set<UserEntity> users;

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

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }
}
