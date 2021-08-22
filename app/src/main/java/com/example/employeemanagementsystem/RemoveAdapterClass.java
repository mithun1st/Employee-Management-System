package com.example.employeemanagementsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class RemoveAdapterClass extends BaseAdapter {

    ArrayList<String> name;
    ArrayList<String> num;
    Context cc;

    public RemoveAdapterClass(Context c1, ArrayList<String> name1, ArrayList <String> num1) {

        this.cc=c1;

        this.name=name1;
        this.num=num1;

    }

    @Override
    public int getCount() {
        return num.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater li=LayoutInflater.from(cc);
        view=li.inflate(R.layout.x_adapter_remove_emp,viewGroup,false);

        TextView tvNum=view.findViewById(R.id.xid);
        TextView tvName=view.findViewById(R.id.xname);
        CheckBox checkBox=view.findViewById(R.id.cb);

        tvName.setText(name.get(i));
        tvNum.setText(num.get(i));
        checkBox.setChecked(true);

        return view;
    }
}
