package com.example.employeemanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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
            about();
        }

        if(item.getItemId()==R.id.share){
            Intent is=new Intent(Intent.ACTION_SEND);
            is.setType("text/plain");

            is.putExtra(Intent.EXTRA_SUBJECT,"Employee Management System");
            is.putExtra(Intent.EXTRA_TEXT,"share with your friends: https://github.com/mithun1st");

            startActivity(Intent.createChooser(is,"Share Now"));
        }

        if(item.getItemId()==R.id.feedback){

            Intent i=new Intent(MainActivity.this,FeedBack.class);
            startActivity(i);

        }



        if(item.getItemId()==R.id.develop){
            Toast.makeText(MainActivity.this,"Mahadi Hassan",Toast.LENGTH_SHORT).show();
            develop();
        }

        return super.onOptionsItemSelected(item);
    }

    //function
    public void about(){
        AlertDialog adAbout;
        AlertDialog.Builder abAbout=new AlertDialog.Builder(this);

        abAbout.setIcon(R.drawable.ic_baseline_perm_identity_24);
        abAbout.setTitle("Build & Develop by");
        //ab.setMessage("");
        abAbout.setCancelable(false);

        //
        LayoutInflater li=getLayoutInflater();
        View v= (View) li.inflate(R.layout.about,findViewById(R.id.cv));
        abAbout.setView(v);
        //

        abAbout.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
//
//
//        ab.setNeutralButton("Nu+", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });


        abAbout.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        adAbout=abAbout.create();
        adAbout.show();
    }





    public void develop(){
        AlertDialog adAbout;
        AlertDialog.Builder abAbout=new AlertDialog.Builder(this);

        abAbout.setIcon(R.drawable.ic_baseline_perm_identity_24);
        abAbout.setTitle("Build & Develop by");
        //ab.setMessage("");
        abAbout.setCancelable(false);

        //
        LayoutInflater li=getLayoutInflater();
        View v= (View) li.inflate(R.layout.about,findViewById(R.id.cv));
        abAbout.setView(v);


        abAbout.setNeutralButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        adAbout=abAbout.create();
        adAbout.show();
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
            Intent i =new Intent(MainActivity.this, RemoveEmp.class);
            startActivity(i);

        }

        if(view.getId()==R.id.stuffListButtonId){
            Intent i=new Intent(MainActivity.this, StuffList.class);
            startActivity(i);
        }

    }

}