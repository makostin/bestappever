package com.mmf.business;

import com.mmf.business.domain.Group;
import com.mmf.business.domain.SpecialtyInfo;

import java.util.List;

/**
 * svetlana.voyteh
 * 12.03.13
 */
public interface GroupService extends CrudService<Long, Group>{

    List<SpecialtyInfo> getSpecialtyInfos();
}
