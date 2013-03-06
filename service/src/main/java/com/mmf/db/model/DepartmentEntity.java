package com.mmf.db.model;

import javax.persistence.*;
import java.util.HashSet;
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
    private String description;
    private Set<LecturerEntity> lecturers = new HashSet<LecturerEntity>();

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
    public Set<LecturerEntity> getLecturers() {
        return lecturers;
    }

    public void setLecturers(Set<LecturerEntity> lecturers) {
        this.lecturers = lecturers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
