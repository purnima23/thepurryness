package com.thepurryness.thepurryness;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by syahmi on 9/6/2015.
 */
public class EmployeeDatabase extends SQLiteOpenHelper {
    public static class Employee {
        public String id;
        public String name;
        public String position;
        public String officePhone;
        public String cellPhone;
        public String department;
        public String email;
        public int salary;
        public String profileURL;
        public String supervisorID;
        public int annualLeaveLeft;
        public String supervisorName;
    }
    public EmployeeDatabase(Context context) {
        super(context, "EmployeeDatabase", null, 1);
    }
    public Employee[] searchByName(String name){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT ID FROM employee WHERE name LIKE ?",
                new String[] { "%" + name + "%"} // looking anywhere. could be name in front or end of name
        );
        Employee[] employees = new Employee[cursor.getCount()]; //how many result get from this statement
        for (int c = 0; c < employees.length; c++){
            cursor.moveToPosition(c);
            String id = cursor.getString(cursor.getColumnIndex("ID"));
            employees[c] = getEmployee(id);
        }
        cursor.close();
        db.close();
        return employees;
    }
    public Employee getEmployee(String id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT emp.name, emp.position, emp.officePhone, emp.cellPhone, emp.department, emp.email, " +
                        "emp.salary, emp.profileUrl, emp.annualLeaveLeft, emp.supervisorID, mgr.name supervisorName " +
                        "FROM employee emp LEFT OUTER JOIN employee mgr ON emp.supervisorID = mgr.ID WHERE emp.ID = ?",
                new String[]{id}
        );
        cursor.moveToPosition(0);
        Employee employee = new Employee();
        employee.name            = cursor.getString(cursor.getColumnIndex("name"));
        employee.position        = cursor.getString(cursor.getColumnIndex("position"));
        employee.officePhone     = cursor.getString(cursor.getColumnIndex("officePhone"));
        employee.cellPhone       = cursor.getString(cursor.getColumnIndex("cellPhone"));
        employee.department      = cursor.getString(cursor.getColumnIndex("department"));
        employee.email           = cursor.getString(cursor.getColumnIndex("email"));
        employee.salary          = cursor.getInt(cursor.getColumnIndex("salary"));
        employee.profileURL      = cursor.getString(cursor.getColumnIndex("profileURL"));
        employee.supervisorID = cursor.getString(cursor.getColumnIndex("supervisorID"));
        employee.annualLeaveLeft = cursor.getInt(cursor.getColumnIndex("annualLeaveLeft"));
        employee.supervisorName = cursor.getString(cursor.getColumnIndex("supervisorName"));
        employee.id = id;
        cursor.close();
        db.close();
        return employee;
    }
    private void addEmployee(SQLiteDatabase db,String id, String name, String position, String officePhone, String cellPhone,
                             String department, String email, int salary, String profileURL,
                             String supervisorID, int annualLeaveLeft) {
        ContentValues values = new ContentValues();
        values.put("ID", id);
        values.put("name", name);
        values.put("position", position);
        values.put("officePhone", officePhone);
        values.put("cellPhone", cellPhone);
        values.put("department", department);
        values.put("email", email);
        values.put("salary", salary);
        values.put("profileURL", profileURL);
        if (supervisorID != null)
            values.put("supervisorID", supervisorID);
        values.put("annualLeaveLeft", annualLeaveLeft);
        db.insert("employee", "name", values);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // name, email, id, salary, profile image, position, telephone number, supervisor, annual leave,
        String sql = "CREATE TABLE IF NOT EXISTS employee (" +
                "ID TEXT(12) PRIMARY KEY, " +
                "name TEXT," +
                "position TEXT," +
                "officePhone TEXT," +
                "cellPhone TEXT," +
                "department TEXT," +
                "email TEXT," +
                "salary INT, " +
                "profileURL TEXT," +
                "supervisorID TEXT(12)," +
                "annualLeaveLeft INT)";
        db.execSQL(sql);
        addEmployee(db,
                "888888888801",
                "ronaldinho",
                "senior developer",
                "+039121212",
                "+601321231231",
                "Development",
                "ronaldinho@msn.com",
                3000,
                "www.msn.com/ronaldinho.jpg",
                null,
                3
        );
        addEmployee(db,
                "888888888802",
                "ronald0",
                "senior developer",
                "+0162322023",
                "+60133332323",
                "Development",
                "ronaldo@msn.com",
                3000,
                "www.msn.com/ronaldo.jpg",
                null,
                4
        );
        addEmployee(db,
                "888888888803",
                "beckham",
                "senior developer",
                "+039142323",
                "+6013423255454",
                "Development",
                "beckham@msn.com",
                3000,
                "www.msn.com/beckham.jpg",
                null,
                3
        );
        addEmployee(db,
                "888888888804",
                "rooney",
                "junior developer",
                "+039145454",
                "+60137774747",
                "Development",
                "rooney@msn.com",
                3000,
                "www.msn.com/rooney.jpg",
                "888888888801",
                3
        );
        addEmployee(db,
                "888888888805",
                "de gea",
                "junior developer",
                "+0399949494",
                "+60129944455",
                "Development",
                "de_gea@msn.com",
                3000,
                "www.msn.com/degea.jpg",
                "8888888801",
                3
        );
        addEmployee(db,
                "888888888806",
                "chicharito",
                "junior developer",
                "+0383435656",
                "+60172232323",
                "Development",
                "chicharito@msn.com",
                3000,
                "www.msn.com/chicharito.jpg",
                "888888888802",
                3
        );
        addEmployee(db,
                "888888888807",
                "messi",
                "junior developer",
                "+037454545",
                "+60112324445",
                "Development",
                "messi@msn.com",
                3000,
                "www.msn.com/messi.jpg",
                "888888888802",
                3
        );
        addEmployee(db,
                "888888888808",
                "lampard",
                "junior developer",
                "+037774747",
                "+6012323232",
                "Development",
                "lampard@msn.com",
                3000,
                "www.msn.com/lampard.jpg",
                "888888888803",
                3
        );
        addEmployee(db,
                "888888888809",
                "keane",
                "junior developer",
                "+039341212",
                "+601344231231",
                "Development",
                "keane@msn.com",
                3000,
                "www.msn.com/keane.jpg",
                "888888888803",
                3
        );
        addEmployee(db,
                "888888888810",
                "ronaldinhohoho",
                "junior developer",
                "+039121212",
                "+601321231231",
                "Development",
                "ronaldinhohoho@msn.com",
                3000,
                "www.msn.com/ronaldinhohoho.jpg",
                "888888888801",
                3
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
