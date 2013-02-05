package com.mmf.db.dao.jpa;

import com.mmf.db.dao.SettingsDao;

import javax.inject.Named;

/**
 * User: admin
 * Date: 20.10.12
 */
@Named
public class SettingsDaoImpl extends GenericJpaDao<Long, SettingsEntity> implements SettingsDao{
    @Override
    protected Class<SettingsEntity> getEntityClass() {
        return SettingsEntity.class;
    }
}
