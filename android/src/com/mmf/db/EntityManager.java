package com.mmf.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.mmf.RezolventaApplication;
import com.mmf.util.Logger;
import com.mmf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author svetlana.voyteh
 * @date: 3/13/12
 */
public class EntityManager {

    private final static boolean IS_UPDATE = true;

    private static EntityManager entityManagerInstance;
    private final DatabaseConnector databaseConnector;
    private SQLiteDatabase currentDatabase;

    public static EntityManager getInstance() {
        if (entityManagerInstance == null) {
            synchronized (EntityManager.class) {
                if (entityManagerInstance == null) {
                    entityManagerInstance = new EntityManager();
                }
            }
        }
        return entityManagerInstance;
    }

    private EntityManager() {
        databaseConnector = RezolventaApplication.getDatabaseConnector();
        currentDatabase = databaseConnector.getWritableDatabase();
        currentDatabase.setLockingEnabled(false);
    }

    public void close() {
        Logger.getInstance().debug("Closing entity manager");
        currentDatabase.close();
        databaseConnector.close();
        entityManagerInstance = null;
        Logger.getInstance().debug("Entity manager closed");
    }

    /**
     * finds the first entity in the database
     *
     * @param t          entity class
     * @param sortColumn sort column name
     * @return entity or null if not found any
     * @throws IllegalAccessException throws IllegalAccessException if an error occurred
     * @throws InstantiationException throws InstantiationException if an error occurred
     */
    public <T extends Entity> T first(Class<T> t, String sortColumn) throws IllegalAccessException, InstantiationException {
        T objT = null;
        Cursor cursor = null;
        try {
            T classInstance = t.newInstance();
            String tableName = classInstance.getTableName();
            String[] columns = classInstance.getColumns();
            String orderBy = sortColumn + " ASC";
            String limit = " 1";

            cursor = currentDatabase.query(tableName, columns, null, null, null, null, orderBy, limit);
            if (cursor.moveToNext()) {
                objT = t.newInstance();
                objT.extractValues(cursor);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return objT;
    }

    /**
     * returns entity by id
     *
     * @param t  entity class
     * @param id id
     * @return entity or null if not found any
     * @throws IllegalAccessException throws IllegalAccessException if an error occurred
     * @throws InstantiationException throws InstantiationException if an error occurred
     */
    public <T extends Entity> T getById(Class<T> t, int id) throws IllegalAccessException, InstantiationException {
        T objT = null;
        Cursor cursor = null;
        try {
            T classInstance = t.newInstance();
            String tableName = classInstance.getTableName();
            String[] columns = classInstance.getColumns();
            String selection = classInstance.getPrimaryKeyColumnName() + " = ?";
            String[] selectionArgs = new String[]{String.valueOf(id)};
            String limit = " 0,1";

            cursor = currentDatabase.query(tableName, columns, selection, selectionArgs, null, null, null, limit);
            if (cursor.moveToNext()) {
                objT = t.newInstance();
                objT.extractValues(cursor);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return objT;
    }

    /**
     *
     * @param t  entity class
     * @param selection where clauses
     * @param selectionArgs arguments of selection
     * @return first entity
     * @throws IllegalAccessException throws IllegalAccessException if an error occurred
     * @throws InstantiationException throws InstantiationException if an error occurred
     */
    public <T extends Entity> T get(Class<T> t, String selection, String[] selectionArgs) throws IllegalAccessException, InstantiationException {
        T objT = null;
        Cursor cursor = null;
        try {
            T classInstance = t.newInstance();
            String tableName = classInstance.getTableName();
            String[] columns = classInstance.getColumns();
            String limit = " 0,1";

            cursor = currentDatabase.query(tableName, columns, selection, selectionArgs, null, null, null, limit);
            if (cursor.moveToNext()) {
                objT = t.newInstance();
                objT.extractValues(cursor);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return objT;
    }


    /**
     * returns list of entities
     *
     * @param t             entity class
     * @param selection     where clauses
     * @param selectionArgs arguments of selection
     * @param orderBy       sort column name
     * @param limit         amount of records
     * @return list of entities or empty list if not found any
     * @throws IllegalAccessException throws IllegalAccessException if an error occurred
     * @throws InstantiationException throws InstantiationException if an error occurred
     */
    public <T extends Entity> List<T> find(Class<T> t, String selection, String[] selectionArgs, String orderBy, String limit) throws IllegalAccessException, InstantiationException {
        T classInstance = t.newInstance();
        String[] columns = classInstance.getColumns();
        return find(t, columns, selection, selectionArgs, orderBy, limit);
    }


    /**
     * returns list of entities
     *
     * @param t             entity class
     * @param selection     where clauses
     * @param selectionArgs arguments of selection
     * @param orderBy       sort column name
     * @param limit         amount of records
     * @return list of entities or empty list if not found any
     * @throws IllegalAccessException throws IllegalAccessException if an error occurred
     * @throws InstantiationException throws InstantiationException if an error occurred
     */
    public <T extends Entity> List<T> find(Class<T> t, String[] columns, String selection, String[] selectionArgs, String orderBy, String limit) throws IllegalAccessException, InstantiationException {
        Cursor cursor = null;
        List<T> resultSet = new ArrayList<T>();
        try {
            T classInstance = t.newInstance();
            String tableName = classInstance.getTableName();

            cursor = currentDatabase.query(tableName, columns, selection, selectionArgs, null, null, orderBy, limit);
            while (cursor.moveToNext()) {
                T obj = t.newInstance();
                obj.extractValues(cursor);
                resultSet.add(obj);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return resultSet;
    }

    public <T extends Entity> List<String> find(Class<T> t, String column, String selection, String[] selectionArgs, String orderBy, String limit) throws IllegalAccessException, InstantiationException {
        Cursor cursor = null;
        List<String> resultSet = new ArrayList<String>();
        try {
            T classInstance = t.newInstance();
            String tableName = classInstance.getTableName();

            cursor = currentDatabase.query(tableName, new String[]{column}, selection, selectionArgs, null, null, orderBy, limit);
            while (cursor.moveToNext()) {
                T obj = t.newInstance();
                String str = obj.extractString(cursor, column);
                if (!resultSet.contains(str)){
                    resultSet.add(str);
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return resultSet;
    }
    
    

    /**
     * adds new record
     *
     * @param t entity class
     * @throws TransactionException throws TransactionException if an error occurred
     */
    public <T extends Entity> void insert(T t) throws TransactionException{
        ContentValues contentValues = t.getContentValues(!IS_UPDATE);
        currentDatabase.insert(t.getTableName(), null, contentValues);
    }

    /**
     * updates record
     *
     * @param t           entity class
     * @throws TransactionException throws TransactionException if an error occurred
     */
    public <T extends Entity> void update(T t) throws TransactionException{
        String whereClause = StringUtils.getSelectionString(new String[]{t.getPrimaryKeyColumnName()});
        String[] whereArgs = new String[]{String.valueOf(t.getId())};
        currentDatabase.update(t.getTableName(), t.getContentValues(IS_UPDATE), whereClause, whereArgs);
    }


    /**
     * deletes record
     *
     * @param t           entity class
     * @param whereClause where clauses
     * @param whereArgs   arguments of where clauses
     * @throws TransactionException throws TransactionException if an error occurred
     * @throws IllegalAccessException throws IllegalAccessException if an error occurred
     * @throws InstantiationException throws InstantiationException if an error occurred
     */
    public <T extends Entity> void delete(Class<T> t, String whereClause, String[] whereArgs) throws TransactionException, IllegalAccessException, InstantiationException {
        T classInstance = t.newInstance();
        String tableName = classInstance.getTableName();
        currentDatabase.delete(tableName, whereClause, whereArgs);
    }


    public synchronized void beginTransaction() {
        currentDatabase.beginTransaction();
    }

    public synchronized void rollbackTransaction() {
        if (currentDatabase.inTransaction()) {
            currentDatabase.endTransaction();
        }
    }

    public synchronized void commitTransaction() {
        if (currentDatabase.inTransaction()) {
            currentDatabase.setTransactionSuccessful();
            currentDatabase.endTransaction();
        }
    }
}
