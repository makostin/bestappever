package com.mmf.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.mmf.R;
import com.mmf.db.model.Department;
import com.mmf.db.model.Lecturer;
import com.mmf.db.model.ScheduleType;
import com.mmf.db.model.Specialty;
import com.mmf.prefs.OptionPrefs;
import com.mmf.service.BusinessLayerException;
import com.mmf.service.DepartmentService;
import com.mmf.service.LecturerService;
import com.mmf.service.SpecialtyService;
import com.mmf.util.IntentUtil;
import com.mmf.util.Logger;
import com.mmf.util.SpinnerUtils;
import com.mmf.view.ToggleButton;


/**
 * @author svetlana.voyteh
 * @date: 2/3/12
 */
public class OptionActivity extends BaseActivity {
    
    private Spinner courseSpinner;
    private Spinner groupSpinner;
    private Spinner subgroupSpinner;
    private Spinner lecturerSpinner;
    private Spinner departmentSpinner;
    private ToggleButton toggleButton;

    private ArrayAdapter<Integer> courseAdapter;
    private ArrayAdapter<Integer> groupAdapter;
    private ArrayAdapter<String> subgroupAdapter;
    private ArrayAdapter<Lecturer> lecturerAdapter;
    private ArrayAdapter<Department> departmentAdapter;
    
    private DepartmentService departmentService;
    private LecturerService lecturerService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option);
        departmentService = new DepartmentService();
        lecturerService = new LecturerService();

        try {
            initSpinners();

            Button button = (Button) findViewById(R.id.apply_button);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    saveOptions();
                    Intent intent = new Intent(OptionActivity.this, LessonActivity.class);
                    if (toggleButton.getSelectedView() == ScheduleType.STUDENT.getId()){
                        intent.putExtra(IntentUtil.SCHEDULE_TYPE_EXTRA, ScheduleType.STUDENT);
                    } else {
                        intent.putExtra(IntentUtil.SCHEDULE_TYPE_EXTRA, ScheduleType.LECTURER);
                    }
                    startActivity(intent);
                }
            });

            toggleButton = (ToggleButton) findViewById(R.id.toggle_button);
            toggleButton.setViews(findViewById(R.id.layout_student), findViewById(R.id.layout_lecturer));


        } catch (BusinessLayerException ble) {
            Logger.getInstance().error(ble);
        }
    }

    private void saveOptions() {
        OptionPrefs.Course.put(Integer.parseInt(courseSpinner.getSelectedItem().toString()));
        OptionPrefs.Group.put(Integer.parseInt(groupSpinner.getSelectedItem().toString()));
        OptionPrefs.Subgroup.put(subgroupSpinner.getSelectedItem().toString());
        Lecturer lecturer = (Lecturer)lecturerSpinner.getSelectedItem();
        if (lecturer != null){
            OptionPrefs.Lecturer.put(lecturer.getId());
        }

        Department department = (Department) departmentSpinner.getSelectedItem();
        if (department != null){
            OptionPrefs.Department.put(department.getId());
        }
    }

    private void initSpinners() throws BusinessLayerException {

        courseSpinner = (Spinner) findViewById(R.id.course_spinner);
        courseAdapter = SpinnerUtils.getCourseAdapter(this);
        courseSpinner.setAdapter(courseAdapter);
        courseSpinner.setSelection(courseAdapter.getPosition(OptionPrefs.Course.get()));

        groupSpinner = (Spinner) findViewById(R.id.group_spinner);
        groupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Integer selected = (Integer) adapterView.getSelectedItem();
                new AsyncTask<Integer, Object, Specialty>(){

                    @Override
                    protected Specialty doInBackground(Integer... param) {
                        SpecialtyService service = new SpecialtyService();
                        return service.getSpecialtyByGroupNumber(param[0]);
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
        groupSpinner.setSelection(groupAdapter.getPosition(OptionPrefs.Group.get()));

        subgroupSpinner = (Spinner) findViewById(R.id.subgroup_spinner);
        subgroupAdapter = SpinnerUtils.getSubGroupAdapter(this);
        subgroupSpinner.setAdapter(subgroupAdapter);
        subgroupSpinner.setSelection(subgroupAdapter.getPosition(OptionPrefs.Subgroup.get()));

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
        Department department = departmentService.getDepartment(OptionPrefs.Department.get());
        if (department != null){
            departmentSpinner.setSelection(departmentAdapter.getPosition(department));
        }

        lecturerSpinner = (Spinner) findViewById(R.id.lecturer_spinner);
        lecturerAdapter = SpinnerUtils.getLecturerAdapter(this);
        lecturerSpinner.setAdapter(lecturerAdapter);
        Lecturer lecturer = lecturerService.getLecturer(OptionPrefs.Lecturer.get());
        if (lecturer != null){
            lecturerSpinner.setSelection(lecturerAdapter.getPosition(lecturer));
        }
    }


}
