package com.example.employeemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddEmp extends AppCompatActivity {

    Button addEmp;
    EditText id,name,desig,phone,mail,salary;
    String sid,sname,sdesig,sphone,smail,ssalary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emp);

        id=findViewById(R.id.etid);
        name=findViewById(R.id.etName);
        desig=findViewById(R.id.etDesig);
        phone=findViewById(R.id.etPhn);
        mail=findViewById(R.id.etEmail);
        salary=findViewById(R.id.etSalary);

        addEmp=findViewById(R.id.addEmp);



        addEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getEditText();

                if(check()){
                    DB1 o = new DB1(AddEmp.this);
                    String flag=o.addEmpData(sid,sname,sdesig,sphone,smail,ssalary);

                    if(flag.matches("Employee Added")){
                        editTextClear();
                    }

                    Toast.makeText(AddEmp.this, flag,Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    //----------function
    private void editTextClear() {
        id.getText().clear();
        name.getText().clear();
        desig.getText().clear();
        phone.getText().clear();
        mail.getText().clear();
        salary.getText().clear();
    }

    public void getEditText(){
        sid=id.getText().toString();
        sname=name.getText().toString();
        sdesig=desig.getText().toString();
        sphone=phone.getText().toString();
        smail=mail.getText().toString();
        ssalary=salary.getText().toString();
    }

    private boolean check() {
        Boolean ch=true;

        if (sid.isEmpty()){
            id.setError("ID field is Empty !");
            id.requestFocus();
            ch=false;
        }
        else if (sname.isEmpty()){
            name.setError("Name field is Empty !");
            name.requestFocus();
            ch=false;
        }
        else if (sphone.isEmpty() && smail.isEmpty()){
            phone.setError("Phone Or Email must have fill at least one field !");
            mail.setError("Phone Or Email must have fill at least one field !");

            mail.requestFocus();
            phone.requestFocus();

            ch=false;
        }
        return ch;
    }

}