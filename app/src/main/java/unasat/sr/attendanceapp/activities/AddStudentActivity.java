package unasat.sr.attendanceapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import unasat.sr.attendanceapp.R;
import unasat.sr.attendanceapp.database.DtbHelper;
import unasat.sr.attendanceapp.entities.StudentEntity;

public class AddStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        EditText firstName = (EditText) findViewById(R.id.firstName);
        EditText lastName = (EditText) findViewById(R.id.lastName);
        EditText emailAddress = (EditText) findViewById(R.id.emailAddress);
        Button submitBtn = (Button) findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first_name = firstName.getText().toString();
                String last_name = lastName.getText().toString();
                String email_address = emailAddress.getText().toString();

                StudentEntity studentEntity = new StudentEntity();
                studentEntity.setStudent_firstname(first_name);
                studentEntity.setStudent_lastname(last_name);
                studentEntity.setStudent_emailaddress(email_address);

                DtbHelper dtBhelper = new DtbHelper(AddStudentActivity.this);
                dtBhelper.addStudent(studentEntity);

                Intent intent = new Intent(AddStudentActivity.this, DashboardActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Student Added, Just like Magic!!", Toast.LENGTH_LONG).show();

            }
        });
    }
}