package com.example.employeemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewEmp extends AppCompatActivity {

    TextView tvid,tvname,tvdesig,tvphone,tvmail,tvsalary,tvjoin;
    AutoCompleteTextView actv;
    Button view,clear;

    StringBuilder id=new StringBuilder();
    StringBuilder name=new StringBuilder();
    StringBuilder designation=new StringBuilder();
    StringBuilder phone=new StringBuilder();
    StringBuilder mail=new StringBuilder();
    StringBuilder salary=new StringBuilder();
    StringBuilder join=new StringBuilder();

    Cursor cu=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_emp);

        tvid=findViewById(R.id.tvId);
        tvname=findViewById(R.id.tvName);
        tvdesig=findViewById(R.id.tvDesig);
        tvphone=findViewById(R.id.tvPhn);
        tvmail=findViewById(R.id.tvEmail);
        tvsalary=findViewById(R.id.tvSalary);
        tvjoin=findViewById(R.id.tvJoin);

        actv=findViewById(R.id.actv);
        view=findViewById(R.id.view);
        clear=findViewById(R.id.clear);


        ArrayList<String> ar=new ArrayList<>(0);
        ar=new DB1(ViewEmp.this).adapterIdName();
        System.out.println(ar);

        ArrayAdapter <String> ad=new ArrayAdapter<>(ViewEmp.this, android.R.layout.simple_list_item_1, ar);
        actv.setThreshold(0);
        actv.setAdapter(ad);


        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DB1 o=new DB1(ViewEmp.this);
                cu=o.ViewEmpDB(actv.getText().toString());
                getDataFromCursor();
                setDataToTV();
                clearStringBuilder();
                actv.setEnabled(false);
                actv.setEnabled(true);
            }
        });


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB1 o=new DB1(ViewEmp.this);
                cu=o.ViewEmpDB(actv.getText().toString());
                getDataFromCursor();
                setDataToTV();
                clearStringBuilder();
            }
        });

        actv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvClear();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvClear();
                actv.setText("");
            }
        });
    }


    public void getDataFromCursor(){
        while (cu.moveToNext()){
            id.append(cu.getString(0).toString());
            name.append(cu.getString(1).toString());
            designation.append(cu.getString(2).toString());
            phone.append(cu.getString(3).toString());
            mail.append(cu.getString(4).toString());
            salary.append(cu.getString(5).toString());
            join.append(cu.getString(6).toString());
        }
    }

    public void setDataToTV(){
        tvid.setText(id.toString());
        tvname.setText(name.toString());
        tvdesig.setText(designation.toString());
        tvphone.setText(phone.toString());
        tvmail.setText(mail.toString());
        tvsalary.setText(salary.toString());
        tvjoin.setText(join.toString());
    }

    public void tvClear(){
        tvid.setText("");
        tvname.setText("");
        tvdesig.setText("");
        tvphone.setText("");
        tvmail.setText("");
        tvsalary.setText("");
        tvjoin.setText("");
    }

    public void clearStringBuilder(){
        id.setLength(0);
        name.setLength(0);
        designation.setLength(0);
        phone.setLength(0);
        mail.setLength(0);
        salary.setLength(0);
        join.setLength(0);
    }

}