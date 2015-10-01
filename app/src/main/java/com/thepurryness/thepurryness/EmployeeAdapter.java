package com.thepurryness.thepurryness;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by syahm on 9/6/2015.
 */
public class EmployeeAdapter  extends BaseAdapter {

    final EmployeeDatabase.Employee[] employees;
    final Activity activity;

    public EmployeeAdapter(Activity activity,EmployeeDatabase.Employee[] employees){
        this.activity = activity;
        this.employees = employees;
    }

    @Override
    public int getCount() {
        return employees.length;
    }

    @Override
    public Object getItem(int position) {
        return employees[position]; //select element of an array
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();

        View view = inflater.inflate(R.layout.employee_list_item, parent, false);

        TextView nameView = (TextView) view.findViewById(R.id.el_name);
        TextView positionView = (TextView) view.findViewById(R.id.el_position);

        EmployeeDatabase.Employee employee = employees[position];

        nameView.setText(employee.name);
        positionView.setText(employee.position + "," + employee.department);

        return view;
    }
}