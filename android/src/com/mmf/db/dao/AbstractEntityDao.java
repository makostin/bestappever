/**
 * 
 */
package com.mmf.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import com.mmf.db.DBVersions;
import com.mmf.db.dao.utils.Column;
import com.mmf.db.dao.utils.Table;
import com.mmf.db.model.Entity;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEntityDao<T extends Entity> extends BaseDAO<T> implements DBVersions {
	public static final String COLUMN_ID = "_id";

	abstract protected T _entityFrom(Cursor cursor);

	abstract protected void _entityTo(T entity, ContentValues values);

	private Table table;

	protected AbstractEntityDao(String tableName, int sinceDbVer) {
		super();
		init(tableName, sinceDbVer);
	}

	private void init(String tableName, int sinceDbVer) {
		table = new Table(tableName, sinceDbVer);

		addColumn(COLUMN_ID, "integer primary key autoincrement", sinceDbVer);
	}

	protected void addColumn(String name, String def, int ver) {
        table.addColumn(new Column(name, def, ver));
	}

	public Table getTable() {
		return table;
	}

	protected String getTableName() {
		return table.getName();
	}

	public boolean delete(long id) {
		String whereClause = COLUMN_ID + "= ?";
		String[] whereArgs = new String[] { String.valueOf(id) };
		int rows = super.deleteQuery(table.getName(), whereClause, whereArgs);

		return rows == 1;
	}

	public void deleteList(List<T> listForDelete) {
		if ((listForDelete != null) && (!listForDelete.isEmpty())) {
			for (T entity : listForDelete) {
				delete(entity.getId());
			}
		}
	}

	public T get(Long id) {
		Cursor cursor = super.findQueryWithWhere(table.getName(), table.getColumnsNames(), COLUMN_ID + "=" + id);
		T entity = null;
		if (cursor.moveToFirst()) {
			entity = loadEntity(cursor);
		}
		cursor.close();
		return entity;
	}

	public void saveData(List<T> entityList) {
		if ((entityList != null) && (!entityList.isEmpty())) {
			for (T entity : entityList) {
				saveData(entity);
			}
		}
	}

	public Long saveData(T entity) {
		ContentValues values = new ContentValues();
		entityTo(entity, values);

		long id = entity.getId();

		if (id == INVALID_ID) {
			id = insertEntity(values);
		} else {
			updateEntity(id, values);
		}
		return id;
	}

	public Long insertData(T entity) {
		ContentValues values = new ContentValues();
		entityTo(entity, values);
		return insertEntity(values);
	}

	public void overwriteAll(List<T> entityList) {
		super.delete( getTableName() );
		for( T entity : entityList ) {
			ContentValues values = new ContentValues();
			values.put(COLUMN_ID, entity.getId());
			entityTo(entity, values);
			insertEntity( values );
		}
	}
	
	public List<T> selectAll() {
		Cursor cursor = super.findQuery(table.getName(), table.getColumnsNames());
		List<T> list = loadEntityList(cursor);
		cursor.close();
		return list;
	}

	protected List<T> loadEntityList(Cursor cursor) {
		ArrayList<T> list = new ArrayList<T>();
		if (cursor != null && cursor.moveToFirst()) {
			do {
				T entity = loadEntity(cursor);
				list.add(entity);
			} while (cursor.moveToNext());
		}
		return list;
	}

	protected T loadEntity(Cursor cursor) {
		T entity = entityFrom(cursor);
		return entity;
	}
	
	private void entityTo(T ticket, ContentValues values) {
	    _entityTo(ticket, values);
	}

	private T entityFrom(Cursor cursor) {
		T entity = _entityFrom(cursor);
		entity.setId( getLong( cursor, COLUMN_ID ) );
		return entity;
	}

	private long insertEntity(ContentValues values) {
		long id = super.insertQuery(table.getName(), values);
		return id;
	}

	private boolean updateEntity(long id, ContentValues values) {
		int count = 0;
		StringBuilder selection = new StringBuilder(COLUMN_ID).append("=?");
		count = super.updateQuery(table.getName(), values, selection.toString(), new String[] { String.valueOf(id) });
		return count == 1;
	}

	protected int getCount(String whereClause, String[] whereArgs) {
		Cursor c = super.findQueryWithWhere( getTable().getName(), new String[] { "COUNT(*)" }, whereClause, whereArgs );
		int result = 0;
		if (c.moveToFirst()) {
			result = c.getInt(0);
		}
		c.close();
		return result;
	}
}
