package com.mmf.util;

import android.content.Context;
import android.widget.ArrayAdapter;
import com.mmf.R;
import com.mmf.activity.OptionActivity;
import com.mmf.db.dao.impl.DepartmentDao;
import com.mmf.db.dao.impl.LecturerDao;
import com.mmf.db.model.Department;
import com.mmf.db.model.Entity;
import com.mmf.db.model.Lecturer;
import com.mmf.prefs.OptionPrefs;
import com.mmf.adapter.*;

import java.util.Iterator;
import java.util.List;

/**
 * User: svetlana.voyteh
 * Date: 16.03.13
 */
public class SpinnerUtils {

    public static ArrayAdapter<Integer> getCourseAdapter(Context context) {
        int courseAmount = OptionPrefs.CourseAmount.get();
        ArrayAdapter<Integer> courseAdapter = new ArrayAdapter<Integer>(context, android.R.layout.simple_spinner_item);
        for (int i=1; i <= courseAmount; i++ ){
            courseAdapter.add(i);
        }
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return courseAdapter;
    }

    public static ArrayAdapter<Integer> getGroupAdapter(Context context) {
        int groupAmount = OptionPrefs.GroupAmount.get();
        ArrayAdapter<Integer> groupAdapter = new ArrayAdapter<Integer>(context, android.R.layout.simple_spinner_item);
        for (int i=1; i <= groupAmount; i++ ){
            groupAdapter.add(i);
        }
        groupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return groupAdapter;
    }

    public static ArrayAdapter<String> getSubGroupAdapter(Context context) {
        String subGroupAmount = OptionPrefs.SubgroupAmount.get();
        String[] subGroups = subGroupAmount.split(",");
        ArrayAdapter<String> subGroupAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item);
        for (String subGroup : subGroups){
            subGroupAdapter.add(subGroup);
        }
        subGroupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return subGroupAdapter;
    }

    public static ArrayAdapter<Department> getDepartmentAdapter(Context context) {
        DepartmentDao departmentDao = (DepartmentDao) EntityRegistry.get().getEntityDao(Department.class);
        List<Department> departments = departmentDao.selectAll();
        ArrayAdapter<Department> departmentAdapter = new ArrayAdapter<Department>(context, android.R.layout.simple_spinner_item);
        for (Iterator<Department> it = departments.iterator() ; it.hasNext() ;){
            departmentAdapter.add(it.next());
        }
        departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return departmentAdapter;
    }

    public static ArrayAdapter<Lecturer> getLecturerAdapter(Context context) {
        LecturerDao lecturerDao = (LecturerDao) EntityRegistry.get().getEntityDao(Lecturer.class);
        List<Lecturer> lecturers = lecturerDao.selectAll();
        ArrayAdapter<Lecturer> lecturerAdapter = new ArrayAdapter<Lecturer>(context, android.R.layout.simple_spinner_item);
        for (Iterator<Lecturer> it = lecturers.iterator() ; it.hasNext() ;){
            lecturerAdapter.add(it.next());
        }
        lecturerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return lecturerAdapter;
    }

    public static ArrayAdapter<Lecturer> getLecturerAdapter(Context context, Long idDepartment) {
        LecturerDao lecturerDao = (LecturerDao) EntityRegistry.get().getEntityDao(Lecturer.class);
        List<Lecturer> lecturers = lecturerDao.getLecturerByDepartment(idDepartment);
        ArrayAdapter<Lecturer> lecturerAdapter = new ArrayAdapter<Lecturer>(context, android.R.layout.simple_spinner_item);
        for (Iterator<Lecturer> it = lecturers.iterator() ; it.hasNext() ;){
            lecturerAdapter.add(it.next());
        }
        lecturerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return lecturerAdapter;
    }
}
