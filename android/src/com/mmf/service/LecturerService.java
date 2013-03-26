package com.mmf.service;

import com.mmf.db.dao.impl.LecturerDao;
import com.mmf.db.model.Lecturer;
import com.mmf.util.EntityRegistry;

import java.util.List;

/**
 * svetlana.voyteh
 * 22.03.13
 */
public class LecturerService {
    
    private final LecturerDao lecturerDao = (LecturerDao) EntityRegistry.get().getEntityDao(Lecturer.class);
    
    public List<Lecturer> list(){
        return lecturerDao.selectAll();
    } 
    
    public List<Lecturer> getLecturerByDepartment(Long departmentId){
        return lecturerDao.getLecturerByDepartment(departmentId);
    }

    public Lecturer getLecturer(long id) {
        return lecturerDao.get(id);
    }
}
