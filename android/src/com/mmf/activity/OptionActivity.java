package com.mmf.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.mmf.R;
import com.mmf.db.model.Department;
import com.mmf.db.model.Lecturer;
import com.mmf.db.model.Specialty;
import com.mmf.prefs.CredentialsPrefs;
import com.mmf.prefs.OptionPrefs;
import com.mmf.rest.DataLoader;
import com.mmf.rest.exceptions.RestException;
import com.mmf.service.BusinessLayerException;
import com.mmf.service.DepartmentService;
import com.mmf.service.LecturerService;
import com.mmf.service.SpecialtyService;
import com.mmf.util.Logger;
import com.mmf.util.SpinnerUtils;
import com.mmf.view.ToggleButton;
import org.apache.http.auth.InvalidCredentialsException;


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
            if(CredentialsPrefs.IsLogined.get()){
                findViewById(R.id.layout_login).setVisibility(View.INVISIBLE);
                findViewById(R.id.layout_logout).setVisibility(View.VISIBLE);
                ((TextView)findViewById(R.id.text_loginName)).setText(CredentialsPrefs.Login.get());
            } else {
                findViewById(R.id.layout_login).setVisibility(View.VISIBLE);
                findViewById(R.id.layout_logout).setVisibility(View.INVISIBLE);
            }

            Button buttonLogin = (Button) findViewById(R.id.button_login);
            buttonLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String login = ((EditText)findViewById(R.id.login)).getText().toString();
                    final String password = ((EditText)findViewById(R.id.password)).getText().toString();
                    new AsyncTask<Object, Object, Boolean>(){

                        @Override
                        protected Boolean doInBackground(Object... param) {
                            try {
                                return DataLoader.getInstance().login(login, password);
                            } catch (InvalidCredentialsException e) {
                                Logger.getInstance().error(e);
                            } catch (RestException e) {
                                Logger.getInstance().error(e);
                            }
                            return false;
                        }

                        @Override
                        protected void onPostExecute(Boolean isLogin) {
                            if (isLogin){
                                findViewById(R.id.layout_login).setVisibility(View.INVISIBLE);
                                findViewById(R.id.layout_logout).setVisibility(View.VISIBLE);
                                CredentialsPrefs.IsLogined.put(true);
                                CredentialsPrefs.Login.put(login);
                                CredentialsPrefs.Password.put(password);
                                ((TextView)findViewById(R.id.text_loginName)).setText(CredentialsPrefs.Login.get());
                            } else {
                                Toast.makeText(OptionActivity.this, getString(R.string.validate_messages_incorrect_login_or_password), Toast.LENGTH_LONG).show();
                            }
                        }
                    }.execute();
                }
            });

            Button buttonLogout = (Button) findViewById(R.id.button_logout);
            buttonLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CredentialsPrefs.IsLogined.put(false);
                    CredentialsPrefs.Login.put("");
                    CredentialsPrefs.Password.put("");
                    findViewById(R.id.layout_login).setVisibility(View.VISIBLE);
                    findViewById(R.id.layout_logout).setVisibility(View.INVISIBLE);
                }
            });

//            Button button = (Button) findViewById(R.id.button_apply);
//            button.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View view) {
//                    saveOptions();
//                    Intent intent = new Intent(OptionActivity.this, LessonActivity.class);
//                    if (toggleButton.getSelectedView() == ScheduleType.STUDENT.getId()){
//                        intent.putExtra(IntentUtil.SCHEDULE_TYPE_EXTRA, ScheduleType.STUDENT);
//                    } else {
//                        intent.putExtra(IntentUtil.SCHEDULE_TYPE_EXTRA, ScheduleType.LECTURER);
//                    }
//                    startActivity(intent);
//                }
//            });

            toggleButton = (ToggleButton) findViewById(R.id.toggle_button);
            toggleButton.setViews(findViewById(R.id.layout_student), findViewById(R.id.layout_lecturer));


        } catch (BusinessLayerException ble) {
            Logger.getInstance().error(ble);
        }
    }

//    private void saveOptions() {
//        OptionPrefs.Course.put(Integer.parseInt(courseSpinner.getSelectedItem().toString()));
//        OptionPrefs.Group.put(Integer.parseInt(groupSpinner.getSelectedItem().toString()));
//        OptionPrefs.Subgroup.put(subgroupSpinner.getSelectedItem().toString());
//        Lecturer lecturer = (Lecturer)lecturerSpinner.getSelectedItem();
//        if (lecturer != null){
//            OptionPrefs.Lecturer.put(lecturer.getId());
//        }
//
//        Department department = (Department) departmentSpinner.getSelectedItem();
//        if (department != null){
//            OptionPrefs.Department.put(department.getId());
//        }
//    }

    private void initSpinners() throws BusinessLayerException {

        courseSpinner = (Spinner) findViewById(R.id.course_spinner);
        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                OptionPrefs.Course.put((Integer) adapterView.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        courseAdapter = SpinnerUtils.getCourseAdapter(this);
        courseSpinner.setAdapter(courseAdapter);
        courseSpinner.setSelection(courseAdapter.getPosition(OptionPrefs.Course.get()));

        groupSpinner = (Spinner) findViewById(R.id.group_spinner);
        groupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                OptionPrefs.Group.put((Integer) adapterView.getSelectedItem());
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
            }
        });
        groupAdapter = SpinnerUtils.getGroupAdapter(this);
        groupSpinner.setAdapter(groupAdapter);
        groupSpinner.setSelection(groupAdapter.getPosition(OptionPrefs.Group.get()));

        subgroupSpinner = (Spinner) findViewById(R.id.subgroup_spinner);
        subgroupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                OptionPrefs.Subgroup.put(adapterView.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        subgroupAdapter = SpinnerUtils.getSubGroupAdapter(this);
        subgroupSpinner.setAdapter(subgroupAdapter);
        subgroupSpinner.setSelection(subgroupAdapter.getPosition(OptionPrefs.Subgroup.get()));

        departmentSpinner = (Spinner) findViewById(R.id.department_spinner);
        departmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Department selected = (Department) adapterView.getSelectedItem();
                if (selected != null){
                    OptionPrefs.Department.put(selected.getId());
                }
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
            }
        });
        departmentAdapter = SpinnerUtils.getDepartmentAdapter(this);
        departmentSpinner.setAdapter(departmentAdapter);
        Department department = departmentService.getDepartment(OptionPrefs.Department.get());
        if (department != null){
            departmentSpinner.setSelection(departmentAdapter.getPosition(department));
        }

        lecturerSpinner = (Spinner) findViewById(R.id.lecturer_spinner);
        lecturerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Lecturer lecturer = (Lecturer)adapterView.getSelectedItem();
                if (lecturer != null){
                    OptionPrefs.Lecturer.put(lecturer.getId());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        lecturerAdapter = SpinnerUtils.getLecturerAdapter(this);
        lecturerSpinner.setAdapter(lecturerAdapter);
        Lecturer lecturer = lecturerService.getLecturer(OptionPrefs.Lecturer.get());
        if (lecturer != null){
            lecturerSpinner.setSelection(lecturerAdapter.getPosition(lecturer));
        }
    }


}
