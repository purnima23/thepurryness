package com.thepurryness.thepurryness;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class EmployeeDetailsActivity extends Activity {

    static final String TAG = "EmployeeDetailsActivity";
    EmployeeDatabase.Employee employee;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_details);

        //Get Intention
        String employeeID = getIntent().getStringExtra("id");

        // get employee data
        EmployeeDatabase db = new EmployeeDatabase(this);

        employee = db.getEmployee(employeeID);

        //Get the views
        TextView nameView = (TextView) findViewById(R.id.ed_name);
        TextView positionView = (TextView) findViewById(R.id.ed_position);
        TextView officePhoneView = (TextView) findViewById(R.id.ed_office_phone);
        TextView hpView = (TextView) findViewById(R.id.ed_hp);
        TextView emailView = (TextView) findViewById(R.id.ed_email);
        TextView supervisorView = (TextView) findViewById(R.id.ed_supervisor);
        TextView idView = (TextView) findViewById(R.id.ed_employeeID);

        //Get the info, got the views, fill them up
        nameView.setText(employee.name);
        positionView.setText(employee.position + ", " + employee.department);
        officePhoneView.setText("Call Office: " + employee.officePhone);
        hpView.setText("Call Hp:  " + employee.cellPhone);
        supervisorView.setText("Supervisor " + employee.supervisorName);
        idView.setText("Employee ID" + employee.id);

        if(employee.supervisorID != null){
            //there is a supervisor
            supervisorView.setText("Supervisor: " + employee.supervisorName);
        }
        else {
            View container = findViewById(R.id.supervisorLayout);
            container.setVisibility(View.GONE);
        }

    }


    public void callOffice(View button) {
        Log.d( TAG, "callOffice button clicked");
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" +employee.officePhone)  );
        startActivity(intent);
    }

    public void callHp(View button){
        Log.d(TAG, "callHp button clicked");
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" +employee.cellPhone)  );
        startActivity(intent);
    }

    public void email(View button){
        Log.d(TAG, "email button clicked");

        try {

            //try to send email
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("plan/text");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{employee.email});
            startActivity(intent);

        } catch (Throwable e) {
            // send email failed, show error message
            Toast.makeText(
                    this,
                    "Cannot send email, please setup your email account first",
                    Toast.LENGTH_LONG
            ).show();
        }

    }

    public void viewSupervisor(View buttonn){
        Log.d( TAG, "viewSupervisor button clicked");
        Intent intent = new Intent(this, EmployeeDetailsActivity.class);
        intent.putExtra("id", employee.supervisorID);
        startActivity(intent);
    }
}
