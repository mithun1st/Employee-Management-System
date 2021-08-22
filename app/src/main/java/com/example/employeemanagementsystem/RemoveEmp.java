package com.example.employeemanagementsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
//import android.widget.SearchView;
import androidx.appcompat.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RemoveEmp extends AppCompatActivity {


    private ListView lv;
    private SearchView sv;
    private TextView tv;
    private Button delete;

    ArrayAdapter <String > adp;

    AlertDialog ad;
    AlertDialog.Builder adb;

    ArrayList<String> id=new ArrayList<String>(0);
    ArrayList<String> name=new ArrayList<String>(0);
    ArrayList<String> designation=new ArrayList<String>(0);
    ArrayList<String> phone=new ArrayList<String>(0);
    ArrayList<String> mail=new ArrayList<String>(0);
    ArrayList<String> salary=new ArrayList<String>(0);
    ArrayList<String> join=new ArrayList<String>(0);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_emp);

        lv=findViewById(R.id.removeListId);
        sv=findViewById(R.id.sv);
        delete=findViewById(R.id.delete);
        tv=findViewById(R.id.tvempno);


        fetch();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(RemoveEmp.this,id.get(i),Toast.LENGTH_SHORT).show();
                lv.setSelection(1);
            }
        });


        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                dialog(i);
                return false;
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean con=false;

                for (int i=0;i<lv.getCount();i++){
                    if(lv.isItemChecked(i)){
                        System.out.println();
                        con=new DB1(RemoveEmp.this).deleteEmp(lv.getItemAtPosition(i).toString());
                    }
                }
                if (con){
                    Toast.makeText(RemoveEmp.this, "Delete Successfully", Toast.LENGTH_SHORT).show();
                    fetch();
                }
                else{
                    Toast.makeText(RemoveEmp.this, "Delete Operation Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adp.getFilter().filter(s);
                return false;
            }
        });


    }



    //----------------function

    public void fetch(){
        Cursor cu=new DB1(RemoveEmp.this).readAllStuffFromDb();

        id.clear();
        name.clear();
        designation.clear();
        phone.clear();
        mail.clear();
        salary.clear();
        join.clear();

        while (cu.moveToNext()){
            id.add(cu.getString(0));
            name.add(cu.getString(1));
            designation.add(cu.getString(2));
            phone.add(cu.getString(3));
            mail.add(cu.getString(4));
            salary.add(cu.getString(5));
            join.add(cu.getString(6));
        }
        //adp=new RemoveAdapterClass(RemoveEmp.this, name, id);
        adp=new ArrayAdapter<String>(RemoveEmp.this, android.R.layout.simple_list_item_multiple_choice,id);
        lv.setAdapter(adp);
        tv.setText("Total Employee Number: "+String.valueOf(lv.getCount()));
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