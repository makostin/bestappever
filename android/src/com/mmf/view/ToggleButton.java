package com.mmf.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.mmf.R;

public class ToggleButton extends RelativeLayout {

    private ImageView image1;
    private ImageView image2;
    private View view1;
    private View view2;

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
                image1.setVisibility(VISIBLE);
                image2.setVisibility(INVISIBLE);
                changeViews(view1, view2);
            }
        });

        Button button2 = (Button) findViewById(R.id.btn2);
        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                image1.setVisibility(INVISIBLE);
                image2.setVisibility(VISIBLE);
                changeViews(view2, view1);
            }
        });

        image1 = (ImageView) findViewById(R.id.image_student);
        image2 = (ImageView) findViewById(R.id.image_lecturer);
	}
	
    public void setViews(View idView1, View idView2){
        this.view1 = idView1;
        this.view2 = idView2;
    }

    public int getSelectedView(){
        return image1.isShown() ? 1 : 2;
    }

    private void changeViews(View visible, View invisible){
        if (visible != null && invisible != null){
            visible.setVisibility(VISIBLE);
            invisible.setVisibility(INVISIBLE);
        }
    }

}
