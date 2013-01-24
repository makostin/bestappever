package com.mmf.db.model;

import javax.persistence.*;

/**
 * @author svetlana.voyteh
 * @date: 3/30/12
 */
@Entity
@Table(name = "userrole")
public class UserRoleEntity implements EntityClass<Long>{

    private static final long serialVersionUID = 1152987584554035975L;
    private Long id;
    private String role;


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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
