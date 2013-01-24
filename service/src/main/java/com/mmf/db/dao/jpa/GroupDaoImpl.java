package com.mmf.db.dao.jpa;

import com.mmf.db.dao.GroupDao;
import com.mmf.db.model.GroupEntity;

import javax.inject.Named;

/**
 * @author svetlana.voyteh
 * @date: 3/12/12
 */
@Named
public class GroupDaoImpl extends GenericJpaDao<Long, GroupEntity> implements GroupDao {

    @Override
    protected Class<GroupEntity> getEntityClass() {
        return GroupEntity.class;
    }
}
