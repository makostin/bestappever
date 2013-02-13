package com.mmf.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import com.mmf.R;
import com.mmf.service.BusinessLayerException;
import com.mmf.service.LecturerOptionService;
import com.mmf.service.StudentOptionService;
import com.mmf.util.Logger;

/**
 * @author svetlana.voyteh
 * @date: 2/3/12
 */
public class MainActivity extends Activity {
    
    private static final String STUDENT = "Student";
    private static final String LECTURER = "Lecturer";

    private Spinner courseSpinner;
    private Spinner groupSpinner;
    private Spinner subgroupSpinner;
    private Spinner lecturerSpinner;
    private Spinner departmentSpinner;
    private Spinner userSpinner;

    private ArrayAdapter<CharSequence> courseAdapter;
    private ArrayAdapter<CharSequence> groupAdapter;
    private ArrayAdapter<CharSequence> subgroupAdapter;
    private ArrayAdapter<CharSequence> lecturerAdapter;
    private ArrayAdapter<CharSequence> departmentAdapter;
    private ArrayAdapter<CharSequence> userAdapter;
    
    private LecturerOptionService lecturerOptionService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        try {
            initSpinners();

            Button button = (Button) findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, LessonActivity.class);
                    if (STUDENT.equals(userSpinner.getSelectedItem().toString())){
                        intent.putExtra("role", STUDENT);
                        intent.putExtra("course", Integer.parseInt(courseSpinner.getSelectedItem().toString()));
                        intent.putExtra("group", groupSpinner.getSelectedItem().toString() + subgroupSpinner.getSelectedItem().toString());

                    } if (LECTURER.equals(userSpinner.getSelectedItem().toString())){
                        intent.putExtra("role", LECTURER);
                        intent.putExtra("lecturer", lecturerSpinner.getSelectedItem().toString());
                        intent.putExtra("department", departmentSpinner.getSelectedItem().toString());

                    }
                    MainActivity.this.startActivity(intent);
                    MainActivity.this.finish();
                }
            });

            boolean isOptionExist = getIntent().getBooleanExtra("isOptionExist", false);
            if(isOptionExist){
                setSpinnerSelection(getIntent().getStringExtra("role"));
            }

        } catch (BusinessLayerException ble) {
            Logger.getInstance().error(ble);
        }
    }

    private void initSpinners() throws BusinessLayerException {

        userSpinner = (Spinner) findViewById(R.id.user_spinner);
        userAdapter = ArrayAdapter.createFromResource(this, R.array.user_array, android.R.layout.simple_spinner_item);
        userAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userSpinner.setAdapter(userAdapter);
        userSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (STUDENT.equals(userAdapter.getItem(i))){
                    findViewById(R.id.lecturer_layout).setVisibility(View.INVISIBLE);
                    findViewById(R.id.student_layout).setVisibility(View.VISIBLE);
                } if (LECTURER.equals(userAdapter.getItem(i))){
                    findViewById(R.id.lecturer_layout).setVisibility(View.VISIBLE);
                    findViewById(R.id.student_layout).setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        courseSpinner = (Spinner) findViewById(R.id.course_spinner);
        courseAdapter = ArrayAdapter.createFromResource(this, R.array.course_array, android.R.layout.simple_spinner_item);
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseSpinner.setAdapter(courseAdapter);

        groupSpinner = (Spinner) findViewById(R.id.group_spinner);
        groupAdapter = ArrayAdapter.createFromResource(this, R.array.group_array, android.R.layout.simple_spinner_item);
        groupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groupSpinner.setAdapter(groupAdapter);

        subgroupSpinner = (Spinner) findViewById(R.id.subgroup_spinner);
        subgroupAdapter = ArrayAdapter.createFromResource(this, R.array.subgroup_array, android.R.layout.simple_spinner_item);
        subgroupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subgroupSpinner.setAdapter(subgroupAdapter);

        departmentSpinner = (Spinner) findViewById(R.id.department_spinner);
        lecturerOptionService = new LecturerOptionService();
        if (lecturerOptionService.isEmpty()){
            lecturerOptionService.setAllLecturers();
        }

        String[] departments = lecturerOptionService.getAllDepartments();
        departmentAdapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, departments);
        departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departmentSpinner.setAdapter(departmentAdapter);
        departmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    String[] lecturers = lecturerOptionService.getLecturersOfDepartment(departmentAdapter.getItem(i).toString());
                    lecturerAdapter = new ArrayAdapter<CharSequence>(MainActivity.this, android.R.layout.simple_spinner_item, lecturers);
                    lecturerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    lecturerSpinner.setAdapter(lecturerAdapter);
                } catch (BusinessLayerException ble) {
                    Logger.getInstance().error(ble);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        lecturerSpinner = (Spinner) findViewById(R.id.lecturer_spinner);
        String[] lecturers = lecturerOptionService.getLecturersOfDepartment(departments[0]);
        lecturerAdapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, lecturers);
        lecturerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lecturerSpinner.setAdapter(lecturerAdapter);
    }

    private void setSpinnerSelection(String role){
        try {
            int position = userAdapter.getPosition(role);
            userSpinner.setSelection(position);

            if (STUDENT.equals(role)){
                StudentOptionService studentOptionService = new StudentOptionService();
                StudentOption studentOption = studentOptionService.getCurrent();
                position = courseAdapter.getPosition(String.valueOf(studentOption.getCourse()));
                courseSpinner.setSelection(position);

                String group = studentOption.getSubgroup().substring(0, studentOption.getSubgroup().length()-1);
                position = groupAdapter.getPosition(group);
                groupSpinner.setSelection(position);

                String subgroup = studentOption.getSubgroup().substring(studentOption.getSubgroup().length()-1);
                position = subgroupAdapter.getPosition(subgroup);
                subgroupSpinner.setSelection(position);

            } if (LECTURER.equals(role)){
                LecturerOption lecturerOption = lecturerOptionService.getCurrent();
                position = departmentAdapter.getPosition(lecturerOption.getDepartment());
                departmentSpinner.setSelection(position);

                position = lecturerAdapter.getPosition(lecturerOption.getName());
                lecturerSpinner.setSelection(position);
            }

        } catch (BusinessLayerException ble){
            Logger.getInstance().error(ble);
        }
    }
}
