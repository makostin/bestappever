package com.mmf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.mmf.R;
import com.mmf.db.model.Entity;

import java.util.List;

/**
 * User: svetlana.voyteh
 * Date: 18.03.13
 */
public abstract class EntityArrayAdapter<T extends Entity> extends ArrayAdapter<T> implements SpinnerAdapter{

    public EntityArrayAdapter(Context context, int textViewResourceId, List<T> objects) {
        super(context, textViewResourceId, objects);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LinearLayout departmentView;
        T entity = getItem(position);
        if(convertView == null){
            departmentView = new LinearLayout(getContext());
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.spinner_item, departmentView, true);
        } else {
            departmentView = (LinearLayout) convertView;
        }

        TextView subject = (TextView) departmentView.findViewById(R.id.item);
        subject.setText(getValue(entity));

        return departmentView;
    }

    protected abstract String getValue(T entity);

}


