package com.mmf.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.mmf.R;
import com.mmf.db.dao.impl.LecturerDao;
import com.mmf.db.dao.impl.SpecialtyDao;
import com.mmf.db.model.Department;
import com.mmf.db.model.Lecturer;
import com.mmf.db.model.Specialty;
import com.mmf.prefs.CredentialsPrefs;
import com.mmf.prefs.OptionPrefs;
import com.mmf.service.BusinessLayerException;
import com.mmf.util.EntityRegistry;
import com.mmf.util.Logger;
import com.mmf.util.SpinnerUtils;
import com.mmf.util.StringUtils;

import java.util.List;

/**
 * @author svetlana.voyteh
 * @date: 2/3/12
 */
public class OptionActivity extends Activity {
    
    private Spinner courseSpinner;
    private Spinner groupSpinner;
    private Spinner subgroupSpinner;
    private Spinner lecturerSpinner;
    private Spinner departmentSpinner;
    private TabHost tabHost;

    private ArrayAdapter<Integer> courseAdapter;
    private ArrayAdapter<Integer> groupAdapter;
    private ArrayAdapter<String> subgroupAdapter;
    private ArrayAdapter<Lecturer> lecturerAdapter;
    private ArrayAdapter<Department> departmentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option);

        try {
            initSpinners();

            Button button = (Button) findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    OptionPrefs.Course.put(Integer.parseInt(courseSpinner.getSelectedItem().toString()));
                    OptionPrefs.Group.put(Integer.parseInt(groupSpinner.getSelectedItem().toString()));
                    OptionPrefs.Subgroup.put(subgroupSpinner.getSelectedItem().toString());
                    startActivity(new Intent(OptionActivity.this, LessonActivity.class));
                }
            });


            TabHost tabHost=(TabHost)findViewById(android.R.id.tabhost);
            tabHost.setup();

            TabHost.TabSpec studentSpec = tabHost.newTabSpec("Student");
            studentSpec.setContent(R.id.studentTab);
            studentSpec.setIndicator("Student");

            TabHost.TabSpec lecturerSpec = tabHost.newTabSpec("Lecturer");
            lecturerSpec.setIndicator("Lecturer");
            lecturerSpec.setContent(R.id.lecturerTab);

            tabHost.addTab(studentSpec);
            tabHost.addTab(lecturerSpec);

        } catch (BusinessLayerException ble) {
            Logger.getInstance().error(ble);
        }
    }

    private void initSpinners() throws BusinessLayerException {

        courseSpinner = (Spinner) findViewById(R.id.course_spinner);
        courseAdapter = SpinnerUtils.getCourseAdapter(this);
        courseSpinner.setAdapter(courseAdapter);

        groupSpinner = (Spinner) findViewById(R.id.group_spinner);
        groupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Integer selected = (Integer) adapterView.getSelectedItem();
                new AsyncTask<Integer, Object, Specialty>(){

                    @Override
                    protected Specialty doInBackground(Integer... param) {
                        SpecialtyDao dao = (SpecialtyDao) EntityRegistry.get().getEntityDao(Specialty.class);
                        return dao.getSpecialtyByGroupNumber(param[0]);
                    }

                    @Override
                    protected void onPostExecute(Specialty specialty) {
                        TextView specialtyView = (TextView)findViewById(R.id.specialty);
                        if (specialty != null){
                            specialtyView.setVisibility(View.VISIBLE);
                            specialtyView.setText(getString(R.string.specialty) + " " + specialty.getName());
                        } else {
                            specialtyView.setText("");
                            specialtyView.setVisibility(View.GONE);
                        }
                    }
                }.execute(selected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        groupAdapter = SpinnerUtils.getGroupAdapter(this);
        groupSpinner.setAdapter(groupAdapter);

        subgroupSpinner = (Spinner) findViewById(R.id.subgroup_spinner);
        subgroupAdapter = SpinnerUtils.getSubGroupAdapter(this);
        subgroupSpinner.setAdapter(subgroupAdapter);

        departmentSpinner = (Spinner) findViewById(R.id.department_spinner);
        departmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Department selected = (Department) adapterView.getSelectedItem();
                new AsyncTask<Long, Object, ArrayAdapter<Lecturer>>(){
                    @Override
                    protected ArrayAdapter<Lecturer> doInBackground(Long... param) {
                        return SpinnerUtils.getLecturerAdapter(OptionActivity.this, param[0]);
                    }

                    @Override
                    protected void onPostExecute(ArrayAdapter<Lecturer> adapter) {
                        lecturerAdapter = adapter;
                        lecturerSpinner.setAdapter(lecturerAdapter);
                    }
                }.execute(selected.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        departmentAdapter = SpinnerUtils.getDepartmentAdapter(this);
        departmentSpinner.setAdapter(departmentAdapter);

        lecturerSpinner = (Spinner) findViewById(R.id.lecturer_spinner);
        lecturerAdapter = SpinnerUtils.getLecturerAdapter(this);
        lecturerSpinner.setAdapter(lecturerAdapter);
    }


}
