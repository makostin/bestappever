package com.mmf.db.dao;

import com.mmf.db.model.StudentEntity;

import java.util.List;

/**
 * svetlana.voyteh
 * 05.03.13
 */
public interface StudentDao extends GenericDao<Long, StudentEntity>{
    List<StudentEntity> getGroupStudents(Long groupId);

    StudentEntity getUser(String login);
}
