package com.mmf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mmf.R;
import com.mmf.db.model.Schedule;

import java.util.List;

/**
 * @author svetlana.voyteh
 * @date 1/19/12
 */
public class StudentLessonsAdapter extends ArrayAdapter<Schedule> {

    int resource;
    
    public StudentLessonsAdapter(Context context, int textViewResourceId, List<Schedule> objects) {
        super(context, textViewResourceId, objects);
        this.resource = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout lessonView;
        Schedule lesson = getItem(position);
        if(convertView == null){
            lessonView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi;
            vi = (LayoutInflater)getContext().getSystemService(inflater);
            vi.inflate(resource, lessonView, true);
        } else {
            lessonView = (LinearLayout) convertView;
        }

        TextView subject = (TextView) lessonView.findViewById(R.id.subject);
        TextView lecturer = (TextView) lessonView.findViewById(R.id.lecturer);
        TextView classRoom = (TextView) lessonView.findViewById(R.id.classroom);
        TextView date = (TextView) lessonView.findViewById(R.id.time);

        subject.setText(lesson.getDiscipline());
        lecturer.setText(String.valueOf(lesson.getLecturerId()));
        classRoom.setText(lesson.getClassroom());
        date.setText(lesson.getTime());

        return lessonView;
    }
}
