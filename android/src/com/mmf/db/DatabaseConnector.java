package com.mmf.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.mmf.R;
import com.mmf.db.model.LecturerOption;
import com.mmf.db.model.StudentOption;
import com.mmf.service.BusinessLayerException;
import com.mmf.service.LecturerOptionService;
import com.mmf.util.Logger;
import com.mmf.util.StringUtils;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author svetlana.voyteh
 * @date: 2/10/12
 */
public class DatabaseConnector extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "rezDB";
    private static final int DATABASE_VERSION = 1;

    private String[] course;
    private String[] group;
    private String[] subgroup;

    private Context context;

    public DatabaseConnector(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        course = StringUtils.getStringArrayByResource(R.array.course_array);
        group = StringUtils.getStringArrayByResource(R.array.group_array);
        subgroup = StringUtils.getStringArrayByResource(R.array.subgroup_array);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        executeSqlScript(db, R.raw.create_db_schema);
        setStudentOption(db);
//        setLecturerOption(db);
        
    }
    

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        executeSqlScript(db, R.raw.create_db_schema);
    }


    private void executeSqlScript(SQLiteDatabase db, int rawResourceId) {
        InputStream sqlInputStream = null;
        try {
            sqlInputStream = context.getResources().openRawResource(rawResourceId);
            byte[] buffer = new byte[sqlInputStream.available()];
            sqlInputStream.read(buffer, 0, sqlInputStream.available());
            String[] sqlStrings = new String(buffer).split(";");
            for (String string : sqlStrings) {
                String sqlString = string.replaceAll("\\n", "").replaceAll("\\r", "").replaceAll("\\t", "");
                if (!StringUtils.isEmpty(sqlString)) {
                    db.execSQL(sqlString + ";");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while reading resource");
        } catch (Resources.NotFoundException notFoundException) {
            throw new RuntimeException(notFoundException);
        } finally {
            try {
                sqlInputStream.close();
            } catch (IOException e2) {
                Logger.getInstance().warn(e2);
            }
        }
    }
    
    private void setStudentOption(SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        for (String aCourse : course) {
            for (String aGroup : group) {
                contentValues.clear();
                contentValues.put(StudentOption.COLUMN_COURSE, aCourse);
                contentValues.put(StudentOption.COLUMN_WEEK, 0);
                contentValues.put(StudentOption.COLUMN_IS_DOWNLOAD, 0);
                contentValues.put(StudentOption.COLUMN_IS_LOGIN, 0);
                contentValues.put(StudentOption.COLUMN_IS_CURRENT, 0);
                contentValues.put(StudentOption.COLUMN_NAME, "");
                contentValues.put(StudentOption.COLUMN_SUBGROUP, aGroup + subgroup[0]);
                db.insert(StudentOption.TABLE_NAME, null, contentValues);

                contentValues.put(StudentOption.COLUMN_SUBGROUP, aGroup + subgroup[1]);
                db.insert(StudentOption.TABLE_NAME, null, contentValues);
            }
        }    
    }

//    private void setLecturerOption(SQLiteDatabase db) {
//        try {
//            List<LecturerOption> lecturers = getAllLecturers();
//            ContentValues contentValues = new ContentValues();
//            for (LecturerOption lecturer : lecturers){
//                contentValues.clear();
//                contentValues.put(LecturerOption.COLUMN_DEPARTMENT, lecturer.getDepartment());
//                contentValues.put(LecturerOption.COLUMN_WEEK, lecturer.getWeek());
//                contentValues.put(LecturerOption.COLUMN_IS_DOWNLOAD, lecturer.isDownload());
//                contentValues.put(LecturerOption.COLUMN_IS_LOGIN, lecturer.isLogin());
//                contentValues.put(LecturerOption.COLUMN_IS_CURRENT, lecturer.isCurrent());
//                contentValues.put(LecturerOption.COLUMN_NAME, lecturer.getName());
//                contentValues.put(LecturerOption.COLUMN_LECTURER_ID, lecturer.getLecturerId());
//                db.insert(LecturerOption.TABLE_NAME, null, contentValues);
//            }
//        } catch (BusinessLayerException e) {
//            Logger.getInstance().warn(e);
//        }
//    }

}
