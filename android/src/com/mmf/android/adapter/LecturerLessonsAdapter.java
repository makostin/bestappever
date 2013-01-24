package com.mmf.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mmf.R;
import com.mmf.db.model.Lesson;

import java.util.List;

/**
 * @author svetlana.voyteh
 * @date: 4/6/12
 */
public class LecturerLessonsAdapter extends ArrayAdapter<Lesson> {

    int resource;

    public LecturerLessonsAdapter(Context context, int textViewResourceId, List<Lesson> objects) {
        super(context, textViewResourceId, objects);
        this.resource = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout lessonView;
        Lesson lesson = getItem(position);
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
        TextView course = (TextView) lessonView.findViewById(R.id.course);
        TextView group = (TextView) lessonView.findViewById(R.id.group);
        TextView classRoom = (TextView) lessonView.findViewById(R.id.classroom);
        TextView date = (TextView) lessonView.findViewById(R.id.time);

        subject.setText(lesson.getSubject());
        course.setText(lesson.getCourse() + " course ");
        group.setText(lesson.getSubgroup() + " group");
        classRoom.setText(lesson.getClassRoom());
        date.setText(lesson.getTime());

        return lessonView;
    }
}
