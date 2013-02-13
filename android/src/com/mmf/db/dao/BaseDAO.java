package com.mmf.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.mmf.db.DBAdapter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

/**
 *
 * 
 */
public abstract class BaseDAO<T extends Serializable> {
	public static final long INVALID_ID = -1;
	
    protected DBAdapter dbAdapter;

    protected BaseDAO() {
    	dbAdapter = DBAdapter.getInstance();
    }
    

    protected void delete( String tableName ) {
    	SQLiteDatabase database = dbAdapter.getCurrentDatabase();
		database.delete( tableName, null, null );
    }

    public int deleteQuery(String tableName, String whereClause, String[] whereArgs) {
    	SQLiteDatabase database = dbAdapter.getCurrentDatabase();
        return database.delete(tableName, whereClause, whereArgs);
    }

    static public void put( ContentValues values, String key, Double value ) {
    	if( value == null ) {
    		values.putNull( key );
    	} else {
    		values.put( key, value );
    	}
    }

    static public void put( ContentValues values, String key, BigDecimal value ) {
    	if( value == null ) {
    		values.putNull( key );
    	} else {
    		values.put( key, value.doubleValue() );
    	}
    }

    static public void put( ContentValues values, String key, String value ) {
    	if( value == null ) {
    		values.putNull( key );
    	} else {
    		values.put( key, value );
    	}
    }

    static public void put( ContentValues values, String key, Integer value ) {
    	if( value == null ) {
    		values.putNull( key );
    	} else {
    		values.put( key, value );
    	}
    }

    static public void put( ContentValues values, String key, Long value ) {
        if( value == null ) {
            values.putNull( key );
        } else {
            values.put( key, value );
        }
    }

    static public Double getDouble( Cursor cursor, String colName ) {
    	int colIndex = cursor.getColumnIndexOrThrow( colName );
    	if( cursor.isNull( colIndex ) ) {
    		return null;
    	}
    	return new Double( cursor.getDouble( colIndex ) );
    }
    
    static public Integer getInt( Cursor cursor, String colName ) {
    	int colIndex = cursor.getColumnIndexOrThrow( colName );
    	if( cursor.isNull( colIndex) ) {
    		return null;
    	}
    	return new Integer( cursor.getInt( colIndex ) );
    }
    
    static public Long getLong( Cursor cursor, String colName ) {
    	int colIndex = cursor.getColumnIndexOrThrow( colName );
    	if( cursor.isNull( colIndex) ) {
    		return null;
    	}
    	return new Long( cursor.getLong( colIndex ) );
    }

    static public BigDecimal getBigDecimal( Cursor cursor, String colName ) {
    	int colIndex = cursor.getColumnIndexOrThrow( colName );
    	if( cursor.isNull( colIndex) ) {
    		return null;
    	}
    	return new BigDecimal( cursor.getDouble(colIndex) );
    }

    static public String getString( Cursor cursor, String colName ) {
    	int colIndex = cursor.getColumnIndexOrThrow( colName );
    	if( cursor.isNull( colIndex) ) {
    		return null;
    	}
    	return cursor.getString( colIndex );
    }
    
    
    protected Cursor findQueryWithOrderByLimit(String tableName, String[] columns, String orderBy, String limit) {
        return dbAdapter.getCurrentDatabase().query(tableName, columns, null, null, null, null, orderBy, limit);
    }

    protected Cursor findQuery(String tableName, String[] columns) {
    	return findQueryWithWhere(tableName, columns, null);
    }

    protected Cursor findQueryWithWhere(String tableName, String[] columns, String whereClause) {
        return dbAdapter.getCurrentDatabase().query(tableName, columns, whereClause, null, null, null, null);
    }

    protected Cursor findQueryWithWhere(String tableName, String[] columns, String whereClause, String orderBy ) {
        return dbAdapter.getCurrentDatabase().query(tableName, columns, whereClause, null, null, null, orderBy );
    }

    protected Cursor findQueryWithWhere(String tableName, String[] columns, String selection, String[] selectionArgs ) {
        return dbAdapter.getCurrentDatabase().query(tableName, columns, selection, selectionArgs, null, null, null);
    }

    protected Cursor findQueryWithWhere(String tableName, String[] columns, String selection, String[] selectionArgs, String orderBy ) {
        return dbAdapter.getCurrentDatabase().query(tableName, columns, selection, selectionArgs, null, null, orderBy );
    }
    
    protected void insertBulkQuery(String tableName, List<ContentValues> values) {
    	SQLiteDatabase database = dbAdapter.getCurrentDatabase();
        for (Iterator<ContentValues> it = values.iterator(); it.hasNext();) {
            ContentValues value = it.next();
            database.insert(tableName, null, value);
        }
    }

    protected long insertQuery(String tableName, ContentValues contentValues) {
    	SQLiteDatabase database = dbAdapter.getCurrentDatabase();
		return database.insert(tableName, null, contentValues);
    }


    protected int updateQuery(String tableName, ContentValues contentValues, String whereClause) {
    	SQLiteDatabase database = dbAdapter.getCurrentDatabase();
		return database.update(tableName, contentValues, whereClause, null);
    }
    
    public int updateQuery(String tableName, ContentValues contentValues, String whereClause, String[] whereArgs) {
    	SQLiteDatabase database = dbAdapter.getCurrentDatabase();
		return database.update(tableName, contentValues, whereClause, whereArgs);
    }
}
