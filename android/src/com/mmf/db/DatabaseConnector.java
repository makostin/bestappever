package com.mmf.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.mmf.util.EntityRegistry;
import com.mmf.db.dao.AbstractEntityDao;
import com.mmf.db.dao.utils.Column;
import com.mmf.db.dao.utils.Table;

import java.util.List;

/**
 * @author svetlana.voyteh
 * @date: 2/10/12
 */
public class DatabaseConnector extends SQLiteOpenHelper implements DBVersions {

    public static final String TAG = "DatabaseConnector";
    public static final String DATABASE_NAME = "scheduleDB";



    public DatabaseConnector(Context context) {
        super(context, DATABASE_NAME, null, CURRENT_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        initDb(db, CURRENT_VERSION, CURRENT_VERSION);
    }
    

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == DATABASE_VERSION_1) {
            List<AbstractEntityDao<?>> entityDaoList = EntityRegistry.get().getEntityDaoList();
            for (AbstractEntityDao<?> dao : entityDaoList) {
                db.execSQL("DROP TABLE IF EXISTS " + dao.getTable());
            }
            onCreate(db);
        } else {
            initDb(db, oldVersion, newVersion);
        }
    }


    void initDb(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == CURRENT_VERSION && newVersion == CURRENT_VERSION) {
            Log.d(TAG, String.format("Create new database v.%d", newVersion));

            List<AbstractEntityDao<?>> entityDaoList = EntityRegistry.get().getEntityDaoList();
            for (AbstractEntityDao<?> dao : entityDaoList) {
                createTable(db, dao.getTable());
            }

        } else if (oldVersion < CURRENT_VERSION) {
            Log.d(TAG, String.format("Create update database from v.%d to v.%d", oldVersion, newVersion));

            List<AbstractEntityDao<?>> entityDaoList = EntityRegistry.get().getEntityDaoList();
            for (AbstractEntityDao<?> dao : entityDaoList) {
                Table table = dao.getTable();
                if (table.getInDbSinceVersion() <= oldVersion) {
                    updateTable(db, dao.getTable(), oldVersion, newVersion);
                } else {
                    createTable(db, table);
                }
            }
        }
    }

    public void createTable(SQLiteDatabase db, Table table) {
        String script = table.getCreationScript();
        Log.d(TAG, script);
        db.execSQL(script);
    }

    public void updateTable(SQLiteDatabase db, Table table, int fromVersion, int toVersion) {
        StringBuilder script = new StringBuilder();
        String tableName = table.getName();
        List<Column> columns = table.getColumns(fromVersion + 1, toVersion);

        for (Column column : columns) {
            script.setLength(0);
            script.append("ALTER TABLE ").append(tableName).append(
                    " ADD COLUMN ").append(column.name()).append(" ")
                    .append(column.definition()).append(';');
            Log.d(TAG, script.toString());
            db.execSQL(script.toString());
        }
    }

}
