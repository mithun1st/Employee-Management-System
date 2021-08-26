package com.example.employeemanagementsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StuffList extends AppCompatActivity {

    private ListView lv;
    private TextView tv,sc;

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
        tv=findViewById(R.id.tvempno);
        sc=findViewById(R.id.snack);


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
        tv.setText("Total Employee: "+String.valueOf(lv.getCount()));

        //snack view
        snackBar();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dialog(i);
            }
        });
    }

    //function

    public void snackBar(){
        if(lv.getCount()==0){
            lv.setVisibility(View.GONE);
            sc.setVisibility(View.VISIBLE);
        }
        else {
            lv.setVisibility(View.VISIBLE);
            sc.setVisibility(View.GONE);
        }
    }

    public void dialog(int i){
        adb=new AlertDialog.Builder(this);
        adb.setTitle("Employee Record");
        adb.setIcon(R.drawable.ic_baseline_work_24);
        adb.setMessage("Detail's Of- "+name.get(i).toUpperCase());

        adb.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        //setView
        LayoutInflater li=getLayoutInflater();
        View v=li.inflate(R.layout.x_dialog_view, null,false);

        TextView tvid=v.findViewById(R.id.tvId);
        TextView tvname=v.findViewById(R.id.tvName);
        TextView tvdesig=v.findViewById(R.id.tvDesig);
        TextView tvphone=v.findViewById(R.id.tvPhn);
        TextView tvmail=v.findViewById(R.id.tvEmail);
        TextView tvsalary=v.findViewById(R.id.tvSalary);
        TextView tvjoin=v.findViewById(R.id.tvJoin);

        tvid.setText(id.get(i));
        tvname.setText(name.get(i));
        tvdesig.setText(designation.get(i));
        tvphone.setText(phone.get(i));
        tvmail.setText(mail.get(i));
        tvsalary.setText(salary.get(i));
        tvjoin.setText(join.get(i));

        adb.setView(v);

        ad=adb.create();
        ad.show();
    }

}