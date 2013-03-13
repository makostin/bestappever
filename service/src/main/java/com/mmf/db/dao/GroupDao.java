package com.mmf.db.dao;

import com.mmf.db.model.GroupEntity;

import java.util.List;

/**
 * svetlana.voyteh
 * 05.03.13
 */
public interface GroupDao extends GenericDao<Long, GroupEntity>{

    List<GroupEntity> getMainGroups();
}
