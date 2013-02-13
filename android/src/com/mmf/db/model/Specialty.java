package com.mmf.db.model;

/**
 * svetlana.voyteh
 * 13.02.13
 */
public class Specialty implements Entity {
    private static final long serialVersionUID = 252760900540193241L;

    private Long id;
    private String name;
    private int number;

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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
