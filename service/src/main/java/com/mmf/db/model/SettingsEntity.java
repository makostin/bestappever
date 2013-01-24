package com.mmf.db.model;

import javax.persistence.*;

@Entity
@Table(name = "settings")
public class SettingsEntity implements EntityClass<Long>{

    private static final long serialVersionUID = 7930894136561594347L;
    private Long id;
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

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
}
