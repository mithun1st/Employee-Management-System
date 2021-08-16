package com.example.employeemanagementsystem;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DB1 extends SQLiteOpenHelper {

    static String dbName="empDatabase.db";
    static String tabName="employee";
    static String col1="id", col2="name", col3="designation",col4="phone", col5="mail", col6="salary", col7="joinDate";

    public static Context con;

    SQLiteDatabase db= this.getWritableDatabase();


    public DB1(Context context) {
        super(context, dbName,null,1);
        this.con=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String q="CREATE TABLE "+tabName+" ("+col1+" int PRIMARY Key,"+col2+" varchar(30) NOT NULL,"+col3+" varchar(20), "+col4+" varchar(14) UNIQUE NOT NULL, "+col5+" varchar(20) UNIQUE, "+col6+" float, "+col7+" Date);";
        Log.v("sql",q);
        db.execSQL(q);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String q="DROP TABLE IF EXISTS "+tabName;
        Log.v("sql1",q);

        db.execSQL(q);
        onCreate(db);
    }


    public void fnc(){

        String id="120", name="mthun",designation="teacher",phone="017",mail="yahoo", salary="5332.32";


        String q="INSERT INTO "+tabName+" ("+col1+", "+col2+", "+col3+", "+col4+", "+col5+", "+col6+", "+col7+") VALUES "+" ("+id+", '"+name+"', '"+designation+"', '"+phone+"', '"+mail+"', "+salary+",  CURRENT_DATE );";
        Log.v("sql1",q);


        try {
            db.execSQL(q);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    //operation
    public Cursor readAllStuffFromDb(){
        Cursor cu=null;
        String q1="SELECT * FROM "+ tabName;
        try{
            cu=db.rawQuery(q1,null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return cu;
    }


    public String addEmpData(String sid, String sname, String sdesig, String sphone, String smail, String ssalary) {
        String q="INSERT INTO "+tabName+" ("+col1+", "+col2+", "+col3+", "+col4+", "+col5+", "+col6+", "+col7+") VALUES "+" ("+sid+", '"+sname+"', '"+sdesig+"', '"+sphone+"', '"+smail+"', "+ssalary+",  CURRENT_DATE );";
        try {
            db.execSQL(q);
            return "Employee Added";
        }
        catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }
}