package com.mmf.business.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * svetlana.voyteh
 * 12.03.13
 */
public class SpecialtyInfo {

    private Long id;
    private String name;
    private List<String> groupNumbers = new ArrayList<String>();

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

    public List<String> getGroupNumbers() {
        return groupNumbers;
    }

    public void setGroupNumbers(List<String> groupNumbers) {
        this.groupNumbers = groupNumbers;
    }
}
