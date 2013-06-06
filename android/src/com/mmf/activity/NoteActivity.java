package com.mmf.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.mmf.R;


/**
 * @author svetlana.voyteh
 * @date: 2/3/12
 */
public class NoteActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note);

        Intent intent = getIntent();
        TextView date = (TextView) findViewById(R.id.text_date);
        date.setText(intent.getStringExtra("date"));
        TextView time = (TextView) findViewById(R.id.text_time);
        time.setText(intent.getStringExtra("time"));
        TextView classroom = (TextView) findViewById(R.id.text_classroom);
        classroom.setText(String.valueOf(intent.getIntExtra("classroom", 0)));
        TextView discipline = (TextView) findViewById(R.id.text_discipline);
        discipline.setText(intent.getStringExtra("discipline"));
        TextView header = (TextView) findViewById(R.id.text_header);
        header.setText(intent.getStringExtra("header"));

        Button addNote = (Button) findViewById(R.id.button_add_note);
        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });

    }

}
