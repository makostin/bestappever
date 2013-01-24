package com.mmf.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.mmf.R;
import com.mmf.android.service.CustomService;
import com.mmf.db.model.LecturerOption;
import com.mmf.db.model.StudentOption;
import com.mmf.service.BusinessLayerException;
import com.mmf.service.LecturerOptionService;
import com.mmf.service.StudentOptionService;
import com.mmf.util.Logger;

/**
 * @author svetlana.voyteh
 * @date: 2/12/12
 */
public class SplashScreenActivity extends Activity{

    public static final int SPLASH_DISPLAY_TIME = 1000;
    private static final String STUDENT = "Student";
    private static final String LECTURER = "Lecturer";
    
    private StudentOptionService studentOptionService;
    private LecturerOptionService lecturerOptionService;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        this.startService(new Intent(this, CustomService.class));
        new Handler().postDelayed(new Runnable() {
            public void run() {
                try {
                    intent = new Intent();
                    studentOptionService = new StudentOptionService();
                    lecturerOptionService = new LecturerOptionService();
                    
                    if(studentOptionService.isCurrentExist()){
                        StudentOption studentOption = studentOptionService.getCurrent();
                        intent.setClass(SplashScreenActivity.this, LessonActivity.class);
                        intent.putExtra("course", studentOption.getCourse());
                        intent.putExtra("group", studentOption.getSubgroup());
                        intent.putExtra("role", STUDENT);
                    } if (lecturerOptionService.isCurrentExist()){
                        LecturerOption lecturerOption = lecturerOptionService.getCurrent();
                        intent.setClass(SplashScreenActivity.this, LessonActivity.class);
                        intent.putExtra("department", lecturerOption.getDepartment());
                        intent.putExtra("lecturer", lecturerOption.getName());
                        intent.putExtra("role", LECTURER);
                    } else {
                        intent.setClass(SplashScreenActivity.this, MainActivity.class);
                        intent.putExtra("isOptionExist", false);
                    }
                    
                    SplashScreenActivity.this.startActivity(intent);
                    SplashScreenActivity.this.finish();
                } catch (BusinessLayerException ble){
                    Logger.getInstance().error(ble);
                }
            }
        }, SPLASH_DISPLAY_TIME);
    }
}
