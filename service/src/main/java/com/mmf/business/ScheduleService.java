package com.mmf.business;

import com.mmf.business.domain.Schedule;
import com.mmf.rest.response.ScheduleResponse;

import java.util.List;

/**
 * User: svetlana.voyteh
 * Date: 19.03.13
 */
public interface ScheduleService extends CrudService<Long, Schedule>{
    List<ScheduleResponse> getSchedule(int semester, int yearOfEntrance, String groupName, String subGroupName) throws BusinessServiceException;

    List<ScheduleResponse> getSchedule(long lecturerId);
}
