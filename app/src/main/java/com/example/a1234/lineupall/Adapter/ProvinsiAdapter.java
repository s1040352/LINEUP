package com.example.a1234.lineupall.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.a1234.lineupall.Model.Provinsi;
import com.example.a1234.lineupall.R;

import java.util.List;

public class ProvinsiAdapter extends ArrayAdapter<Provinsi> {

    private List<Provinsi> provinsiList;
    private Context  mCtx;

    public ProvinsiAdapter(List<Provinsi> P,Context c){
        super(c,R.layout.listprovinsi,P);
        this.provinsiList = P;
        this.mCtx = c;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.listprovinsi,null,true);

        TextView id=(TextView) view.findViewById(R.id.textViewa);
        TextView cl_username=(TextView) view.findViewById(R.id.textViews);
        TextView cl_phone=(TextView) view.findViewById(R.id.textViewd);

        Provinsi provinsi = provinsiList.get(position);
        cl_username.setText(provinsi.getCl_username());
        id.setText(provinsi.getId());
        cl_phone.setText(provinsi.getCl_phone());

        return view;
    }
}