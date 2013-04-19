package com.mmf.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.mmf.R;
import com.mmf.util.EntityRegistry;

public class ToggleButton extends RelativeLayout {

    private ImageView imageStudent;
    private ImageView imageLecturer;
    private View studentView;
    private View lecturerView;

	public ToggleButton(Context context) {
		this(context, null);
	}
	
	public ToggleButton(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public ToggleButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		((Activity)getContext())
		.getLayoutInflater()
		.inflate(R.layout.toggle_button, this, true);
		
		setUpViews();
	}


    private void setUpViews() {
        Button button1 = (Button) findViewById(R.id.btn1);
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                imageStudent.setVisibility(VISIBLE);
                imageLecturer.setVisibility(INVISIBLE);
                changeViews(studentView, lecturerView);
            }
        });

        Button button2 = (Button) findViewById(R.id.btn2);
        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                imageStudent.setVisibility(INVISIBLE);
                imageLecturer.setVisibility(VISIBLE);
                changeViews(lecturerView, studentView);
            }
        });

        imageStudent = (ImageView) findViewById(R.id.image_student);
        imageLecturer = (ImageView) findViewById(R.id.image_lecturer);
	}
	
    public void setViews(View idView1, View idView2){
        this.studentView = idView1;
        this.lecturerView = idView2;
    }

    private void changeViews(View visible, View invisible){
        if (visible != null && invisible != null){
            visible.setVisibility(VISIBLE);
            invisible.setVisibility(INVISIBLE);
        }
    }

}
