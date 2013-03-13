package com.mmf.db.dao.jpa;

import com.mmf.db.dao.GroupDao;
import com.mmf.db.model.GroupEntity;

import javax.inject.Named;
import java.util.List;

/**
 * svetlana.voyteh
 * 12.03.13
 */
@Named
public class GroupDaoImpl extends GenericJpaDao<Long, GroupEntity> implements GroupDao{


    @Override
    protected Class<GroupEntity> getEntityClass() {
        return GroupEntity.class;
    }

    @Override
    public List<GroupEntity> getMainGroups() {

        return null;
    }
}
