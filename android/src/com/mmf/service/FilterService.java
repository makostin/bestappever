package com.mmf.service;

import com.mmf.db.DaoLayerException;
import com.mmf.db.dao.impl.FilterDao;
import com.mmf.db.dao.impl.ScheduleDao;
import com.mmf.db.model.Filter;
import com.mmf.db.model.Schedule;
import com.mmf.prefs.OptionPrefs;
import com.mmf.util.EntityRegistry;
import com.mmf.util.Logger;

/**
 * svetlana.voyteh
 * 25.03.13
 */
public class FilterService {

    private final FilterDao filterDao = (FilterDao) EntityRegistry.get().getEntityDao(Filter.class);

//    private int course;
//    private int group;
//    private String subGroup;
//    private Long lecturer;

    public FilterService(){
//        this.course = OptionPrefs.Course.get();
//        this.group = OptionPrefs.Group.get();
//        this.subGroup = OptionPrefs.Subgroup.get();
//        this.lecturer = OptionPrefs.Lecturer.get();
    }

    public long updateFilter(int course, int group, String subGroup) throws BusinessLayerException {
        try {
            Filter filter = filterDao.getFilter(course, group, subGroup);
            if (filter != null){
                filterDao.delete(filter.getId());
                ScheduleDao scheduleDao = (ScheduleDao) EntityRegistry.get().getEntityDao(Schedule.class);
                scheduleDao.deleteScheduleByFilter(filter.getId());
            }
            filter = new Filter();
            filter.setCourse(course);
            filter.setGroupNumber(group);
            filter.setSubGroup(subGroup);


            return filterDao.saveData(filter);
        } catch (DaoLayerException e) {
            Logger.getInstance().error(e);
            throw new BusinessLayerException(e);
        }
    }

    public Filter getFilter(int course, int group, String subGroup) {
        return filterDao.getFilter(course, group, subGroup);
    }
}
