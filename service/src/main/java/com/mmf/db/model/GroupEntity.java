package com.mmf.db.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author svetlana.voyteh
 * @date: 1/21/12
 */
@Entity
@Table(name = "studygroup")
public class GroupEntity implements EntityClass<Long>{

    private static final long serialVersionUID = 6498292117645011293L;
    private Long id;
    private String name;
    private Integer year;
    private SpecialtyEntity specialty;
    private GroupEntity mainGroup;
    private Set<StudentEntity> students = new HashSet<StudentEntity>();

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

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    public Set<StudentEntity> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentEntity> students) {
        this.students = students;
    }

    @ManyToOne
    @JoinColumn(name = "IdSpecialty")
    public SpecialtyEntity getSpecialty() {
        return specialty;
    }

    public void setSpecialty(SpecialtyEntity specialty) {
        this.specialty = specialty;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @ManyToOne
    @JoinColumn(name = "IdMainGroup")
    public GroupEntity getMainGroup() {
        return mainGroup;
    }

    public void setMainGroup(GroupEntity mainGroup) {
        this.mainGroup = mainGroup;
    }
}
