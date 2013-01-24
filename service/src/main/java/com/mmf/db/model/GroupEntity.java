package com.mmf.db.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author svetlana.voyteh
 * @date: 1/21/12
 */
@Entity
@Table(name = "studentgroup")
public class GroupEntity implements EntityClass<Long>{

    private static final long serialVersionUID = 6498292117645011293L;
    private Long id;
    private String subGroup;
    private Integer number;
    private SpecialtyEntity specialty;
    private Set<UserEntity> users = new HashSet<UserEntity>();
    private Set<TimetableCellEntity> timetableCells = new HashSet<TimetableCellEntity>();

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

    public String getSubGroup() {
        return subGroup;
    }

    public void setSubGroup(String subGroup) {
        this.subGroup = subGroup;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    //    @ManyToMany
//    @JoinTable(name="grouppair",
//            joinColumns={@JoinColumn(name="ID_SubGroup")},
//            inverseJoinColumns={@JoinColumn(name="ID_Lesson")})
//    public Set<LessonEntity> getLessons() {
//        return lessons;
//    }
//
//    public void setLessons(Set<LessonEntity> lessons) {
//        this.lessons = lessons;
//    }

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    public Set<TimetableCellEntity> getTimetableCells() {
        return timetableCells;
    }

    public void setTimetableCells(Set<TimetableCellEntity> timetableCells) {
        this.timetableCells = timetableCells;
    }

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Specialty")
    public SpecialtyEntity getSpecialty() {
        return specialty;
    }

    public void setSpecialty(SpecialtyEntity specialty) {
        this.specialty = specialty;
    }
}
