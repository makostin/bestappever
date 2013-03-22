package com.mmf.util;

import android.content.Context;
import com.mmf.db.dao.impl.*;
import com.mmf.db.model.*;
import com.mmf.db.dao.AbstractEntityDao;

import java.util.ArrayList;
import java.util.List;

public class EntityRegistry {
	ArrayList< EntityInfo > list = new ArrayList<EntityInfo>();
	
	private static EntityRegistry instance;
	
	private EntityRegistry(Context context) {
		register( Department.class, new DepartmentDao());
		register( Lecturer.class, new LecturerDao());
        register( Schedule.class, new ScheduleDao());
        register( Specialty.class, new SpecialtyDao());
        register( Note.class, new NoteDao());
        register( Filter.class, new FilterDao());
	}
	
	static public void init( Context context ) {
		if( instance == null ) {
			instance = new EntityRegistry( context );
		}
	}
	
	static public EntityRegistry get() {
		return instance;
	}

	public EntityInfo register( Class<? extends Entity> entityClass, AbstractEntityDao<?> entityDao ) {
		if( entityDao == null ) {
			throw new IllegalArgumentException();
		}
		EntityInfo info = new EntityInfo( entityClass, entityDao );
        list.add(info);
		return info;
	}
	
	public List<AbstractEntityDao<?>> getEntityDaoList() {
		List<AbstractEntityDao<?>> list = new ArrayList<AbstractEntityDao<?>>();
		for( int i=0; i < this.list.size(); i++ ) {
			list.add( this.list.get(i).getEntityDao() );
		}
		return list;
	}
	
	public AbstractEntityDao<?> getEntityDao( Class<? extends Entity> entityClass ) {
		for( EntityInfo info : list) {
			if( info.getEntityClass().equals( entityClass ) ) {
				return info.getEntityDao();
			}
		}
		throw new IllegalArgumentException( "Ticket " + entityClass.getSimpleName() + " is not registered" );
	}
	
	public EntityInfo getEntityInfo( Class<? extends Entity> entityClass ) {
		if( entityClass == null ) {
			throw new IllegalArgumentException();
		}
		
		EntityInfo ti = new EntityInfo( entityClass );
		final int index = list.indexOf( ti );
		
		if( index == -1 ) {
			throw new IllegalArgumentException();
		}
		
		return list.get( index );
	}
	
	
	

	public class EntityInfo {
		Class<? extends Entity> entityClass;
        AbstractEntityDao<?> entityDao;
		
		public EntityInfo(Class<? extends Entity> entityClass) {
			this.entityClass = entityClass;
		}
		
		public EntityInfo( Class<? extends Entity> entityClass, AbstractEntityDao<?> entityDao ) {
			this.entityClass = entityClass;
			this.entityDao = entityDao;
		}

		public AbstractEntityDao<?> getEntityDao() {
			return entityDao;
		}
		
		public Class<? extends Entity> getEntityClass() {
			return entityClass;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((entityClass == null) ? 0 : entityClass.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			EntityInfo other = (EntityInfo) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (entityClass == null) {
				if (other.entityClass != null)
					return false;
			} else if (!entityClass.equals(other.entityClass))
				return false;
			return true;
		}

		private EntityRegistry getOuterType() {
			return EntityRegistry.this;
		}
	}
}
