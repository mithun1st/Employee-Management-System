package com.example.employeemanagementsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class StuffList extends AppCompatActivity {

    private ListView lv;

    ArrayList<String> id=new ArrayList<String>(0);
    ArrayList<String> name=new ArrayList<String>(0);
    ArrayList<String> designation=new ArrayList<String>(0);
    ArrayList<String> phone=new ArrayList<String>(0);
    ArrayList<String> mail=new ArrayList<String>(0);
    ArrayList<String> salary=new ArrayList<String>(0);
    ArrayList<String> join=new ArrayList<String>(0);

    AlertDialog ad;
    AlertDialog.Builder adb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stuff_list);

        lv=findViewById(R.id.stuffListId);


        Cursor cu=new DB1(StuffList.this).readAllStuffFromDb();
        Toast.makeText(StuffList.this,"Total "+cu.getCount()+" Record Found",Toast.LENGTH_SHORT);
        while (cu.moveToNext()){
            id.add(cu.getString(0));
            name.add(cu.getString(1));
            designation.add(cu.getString(2));
            phone.add(cu.getString(3));
            mail.add(cu.getString(4));
            salary.add(cu.getString(5));
            join.add(cu.getString(6));
        }


        StuffAdapterClass sac=new StuffAdapterClass(StuffList.this, name, id);
        lv.setAdapter(sac);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dialog(i);
            }
        });


    }

    public void dialog(int i){
        adb=new AlertDialog.Builder(this);
        adb.setTitle("Detail's Of- "+name.get(i).toUpperCase());
        adb.setIcon(R.drawable.ic_baseline_work_24);
        adb.setMessage("\n \tID: "+id.get(i)+"\n"+
                "\tName: "+name.get(i)+"\n"+
                "\tDesignation: "+designation.get(i)+"\n"+
                "\tPhone: "+phone.get(i)+"\n"+
                "\tMail: "+mail.get(i)+"\n"+
                "\tSalary: "+id.get(i)+"\n"+
                "\tJoin: "+join.get(i));

        adb.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        ad=adb.create();
        ad.show();
    }

}