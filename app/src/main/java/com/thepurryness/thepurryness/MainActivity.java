package com.thepurryness.thepurryness;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends Activity implements TextWatcher, AdapterView.OnItemClickListener {

    EmployeeDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_list);

        db = new EmployeeDatabase(this);

        EditText searchText = (EditText) findViewById(R.id.el_searchText);
        searchText.addTextChangedListener(this);

        ListView listView = (ListView) findViewById(R.id.el_listView);
        listView.setOnItemClickListener(this);

        search("");

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // dont bother
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // dont bother
    }

    @Override
    public void afterTextChanged(Editable editable) {
        search(editable.toString());
    }

    public void search(String searchText){
        EmployeeAdapter adapter = new EmployeeAdapter(this, db.searchByName(searchText));

        ListView listView = (ListView) findViewById(R.id.el_listView);
        listView.setAdapter(adapter);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // create intention
        //get employee date form the list
        EmployeeDatabase.Employee employee = (EmployeeDatabase.Employee) parent.getItemAtPosition(position);

        // open details activity
        Intent intent = new Intent(this, EmployeeDetailsActivity.class);
        intent.putExtra("id",employee.id );

        //to start intention
        startActivity(intent);
    }
}