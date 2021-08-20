package com.example.employeemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class EditDeta extends AppCompatActivity implements View.OnClickListener{

    Button edit,update,clear;
    EditText name,desig,phone,mail,salary;
    AutoCompleteTextView actv;

    DB1 o;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_deta);

        actv=findViewById(R.id.actv);
        edit=findViewById(R.id.edit);
        update=findViewById(R.id.update);
        clear=findViewById(R.id.clear);

        name=findViewById(R.id.etName);
        desig=findViewById(R.id.etDesig);
        phone=findViewById(R.id.etPhn);
        mail=findViewById(R.id.etEmail);
        salary=findViewById(R.id.etSalary);

        actv.setOnClickListener(this);
        edit.setOnClickListener(this);
        update.setOnClickListener(this);
        clear.setOnClickListener(this);

        //initial edit text disable
        editTextEnable(false);

        o=new DB1(EditDeta.this);

        ArrayList <String> al=new ArrayList<String> (0);
        al=o.adapterId();

        ArrayAdapter <String> ad =new ArrayAdapter<String>(EditDeta.this, android.R.layout.simple_list_item_1,al);
        actv.setThreshold(1);
        actv.setAdapter(ad);


        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                fetch();
                editTextEnable(true);
            }
        });

    }

    @Override
    public void onClick(View view) {

        if(view==edit){
            fetch();
            editTextEnable(true);
            Toast.makeText(EditDeta.this, "Edit Enable",Toast.LENGTH_SHORT).show();
        }

        if(view==update){

            if(name.getText().toString().isEmpty()){
                name.setError("Name Field can't be Empty");
            }
            else if(phone.getText().toString().isEmpty() && mail.getText().toString().isEmpty()){
                phone.setError("At lest full-fill one field Phone or Mail ");
                mail.setError("At lest full-fill one field Mail or Phone ");
            }
            else{
                Boolean b=o.updateInfo(actv.getText().toString(), name.getText().toString(), desig.getText().toString(),
                        phone.getText().toString(), mail.getText().toString(),salary.getText().toString());
                if(b){
                    Toast.makeText(EditDeta.this, "Update Successfully",Toast.LENGTH_SHORT).show();
                    clear();
                    editTextEnable(false);
                }
                else {
                    Toast.makeText(EditDeta.this, "Update Failed",Toast.LENGTH_SHORT).show();
                }
            }
        }

        if(view==clear){
            clear();
            editTextEnable(false);
            Toast.makeText(EditDeta.this, "Clear",Toast.LENGTH_SHORT).show();
        }

    }



    //clear display
    public void clear(){
        actv.setText("");

        name.setText("");
        desig.setText("");
        phone.setText("");
        mail.setText("");
        salary.setText("");
    }

    public void editTextEnable(Boolean b){
        name.setEnabled(b);
        desig.setEnabled(b);
        phone.setEnabled(b);
        mail.setEnabled(b);
        salary.setEnabled(b);
    }

    public void fetch(){

        Cursor cu=o.fetchData(actv.getText().toString());
        System.out.println(cu.getCount());

        while (cu.moveToNext()){
            name.setText(cu.getString(0).toString());
            desig.setText(cu.getString(1).toString());
            phone.setText(cu.getString(2).toString());
            mail.setText(cu.getString(3).toString());
            salary.setText(cu.getString(4).toString());
        }
    }
}