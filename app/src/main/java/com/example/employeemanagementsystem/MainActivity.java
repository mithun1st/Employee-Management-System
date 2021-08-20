package com.example.employeemanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button addEmp, viewEmp, editEmp, removeEmp , listEmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assign xml component
        addEmp=findViewById(R.id.addEmp);
        viewEmp=findViewById(R.id.viewEmp);
        editEmp=findViewById(R.id.editEmp);
        removeEmp=findViewById(R.id.removeEmp);
        listEmp=findViewById(R.id.stuffListButtonId);


        addEmp.setOnClickListener(this);
        viewEmp.setOnClickListener(this);
        editEmp.setOnClickListener(this);
        removeEmp.setOnClickListener(this);
        listEmp.setOnClickListener(this);



        //initial database
        DB1 o=new DB1(MainActivity.this);
        SQLiteDatabase sqLiteDatabase=o.getWritableDatabase();

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_option, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.about){
            Toast.makeText(MainActivity.this,"Mahadi Hassan",Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }


    //-------------------operational button

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.addEmp){
            Intent i=new Intent(MainActivity.this, AddEmp.class);
            startActivity(i);
        }

        if(view.getId()==R.id.viewEmp){
            Intent i=new Intent(MainActivity.this, ViewEmp.class);
            startActivity(i);
        }
        if(view.getId()==R.id.editEmp){
            Intent i=new Intent(MainActivity.this, EditDeta.class);
            startActivity(i);

        }
        if(view.getId()==R.id.removeEmp){

        }

        if(view.getId()==R.id.stuffListButtonId){
            Intent i=new Intent(MainActivity.this, ListEmp.class);
            startActivity(i);
        }

    }

}