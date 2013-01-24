package com.mmf.db;

import android.content.ContentValues;
import android.database.Cursor;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;

/**
 * @author svetlana.voyteh
 * @date: 3/13/12
 */
public abstract class Entity {
    
    private final static String COLUMN_ID = "_id";
    protected int id;


    public int getId(){
        return id;
    }

    void setId(int id){
        this.id = id;
    }

    public String getPrimaryKeyColumnName(){
        return COLUMN_ID;
    }

    abstract public String getTableName();

    abstract public String[] getColumns();

    abstract public ContentValues getContentValues(boolean isUpdate);

    abstract public void extractValues(Cursor cursor);
    
    public String extractString(Cursor cursor, String column){
        return cursor.getString(cursor.getColumnIndex(column));
    }
}
