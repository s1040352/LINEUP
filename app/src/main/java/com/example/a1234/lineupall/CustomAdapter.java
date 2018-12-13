package com.example.a1234.lineupall;

/**
 * Created by 1234 on 2018/10/16.
 */

import java.io.IOException;
import java.util.ArrayList;

import android.app.Dialog;
import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;



import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;



public class CustomAdapter extends ArrayAdapter<Product>   {

    HttpResponse response;
    int groupid;

    ArrayList<Product> records;

    Context context;










    public CustomAdapter(Context context, int vg, int id, ArrayList<Product>
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
        textclusername.setText(records.get(position).getclusername());

        TextView textclphone  = (TextView) itemView.findViewById(R.id.pro_qq);
        textclphone.setText(records.get(position).getclphone());
        //  TextView textid = (TextView) itemView.findViewById(R.id.textView2);
        //  textid.setText(records.get(position).getidn());

        //  TextView textqq = (TextView) itemView.findViewById(R.id.pro_uprice);
        // textqq.setText(records.get(position).getaccount() );
        ImageButton buttonOption = (ImageButton) itemView.findViewById(R.id.imageButton);


        buttonOption.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog);
                if (android.os.Build.VERSION.SDK_INT > 9) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                }
                dialog.setCancelable(false);
                dialog.show();

                Button btn_yes,btn_no;
                btn_yes = dialog.findViewById(R.id.btn_yes);
                btn_no = dialog.findViewById(R.id.btn_no);

                btn_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String url = "http://192.168.1.7/call_time.php";
                        HttpClient client = new DefaultHttpClient();

                        try {
                            client.execute(new HttpGet(url));
                        } catch(IOException e) {
                            //do something here
                        }


                        Toast.makeText(context, "帶位成功", Toast.LENGTH_SHORT).show();

                        dialog.dismiss();

                    }

                });

                btn_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }

                });
            }
        });


        return itemView;

    }





}


