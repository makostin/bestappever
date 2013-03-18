package com.mmf.db.model;

/**
 * svetlana.voyteh
 * 13.02.13
 */
public class Department implements Entity{
    private static final long serialVersionUID = -611955538916709854L;

    private Long id;
    private String name;

    public Department(){
    }

    public Department(Long id){
        this.id = id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
