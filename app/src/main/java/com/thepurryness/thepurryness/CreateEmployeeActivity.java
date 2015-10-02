package com.thepurryness.thepurryness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.zip.CheckedInputStream;

public class CreateEmployeeActivity extends AppCompatActivity implements View.OnTouchListener {
    static final String TAG = "CreateEmployeeDetailsActivity";

    boolean hasSupervisor = true;

    EmployeeDatabase.Employee supervisor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_employee);

        findViewById(R.id.ec_supervisorName).setOnTouchListener(this);  //implement


    }

    public void onToggleSupervisor(View view) {
        Log.d(TAG, "Supervisor checkbox toggled");

        CheckBox checkBox = (CheckBox) view; //view = checkBox;
        checkBox.isChecked();
        hasSupervisor = checkBox.isChecked();

        if(hasSupervisor) {

            findViewById(R.id.ec_supervisorLayout).setVisibility(View.VISIBLE);
        }
        else {
            findViewById(R.id.ec_supervisorLayout).setVisibility(View.GONE);
        }

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(motionEvent.getAction() != MotionEvent.ACTION_UP)
            return false;

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("isSelectMode", true);

        startActivityForResult(intent, 1001, null);
        return false; //if return true keyboard will pop up
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1001 && resultCode == RESULT_OK){
            //Have successfully selected supervisor
            supervisor = MainActivity.selectedEmployee;
            ((TextView)findViewById(R.id.ec_supervisorName)).setText(supervisor.name);

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
