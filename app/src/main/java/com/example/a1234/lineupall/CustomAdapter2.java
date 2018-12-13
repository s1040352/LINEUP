package com.example.a1234.lineupall;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class CustomAdapter2 extends ArrayAdapter<Product>   {


    int groupid;

    ArrayList<Product> records;

    Context context;










    public CustomAdapter2(Context context, int vg, int id, ArrayList<Product>
            records) {

        super(context, vg, id, records);

        this.context = context;

        groupid = vg;

        this.records = records;



    }



    public View getView(int position, View convertView, ViewGroup parent) {



        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(groupid, parent, false);

        TextView textclusername = (TextView) itemView.findViewById(R.id.pro_n);
        textclusername.setText("名字： "+records.get(position).getclusername());

        TextView textclphone  = (TextView) itemView.findViewById(R.id.pro_qq);
        textclphone .setText("電話："+records.get(position).getclphone());
        //  TextView textid = (TextView) itemView.findViewById(R.id.textView2);
        //  textid.setText(records.get(position).getidn());

        //  TextView textqq = (TextView) itemView.findViewById(R.id.pro_uprice);
        // textqq.setText(records.get(position).getaccount() );ImageButton buttonOption = (ImageButton) itemView.findViewById(R.id.imageButton);




        return itemView;

    }





}


