package com.example.a1234.lineupall;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class customer extends AppCompatActivity {
    private ImageButton button1,button2,button3,button4;
    private Intent intent;
    Activity context;

    HttpPost httppost;

    StringBuffer buffer;

    HttpResponse response;

    HttpClient httpclient;

    ProgressDialog pd;

    CustomAdapter1 adapter;

    ListView listProduct;

    ArrayList<Product> records;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        context=this;

        records=new ArrayList<Product>();

        listProduct=(ListView)findViewById(R.id.product_list);

        adapter=new CustomAdapter1(context, R.layout.list_item1,R.id.pro_n,
                records);

        listProduct.setAdapter(adapter);
        button1=(ImageButton)findViewById(R.id.client);
        button2=(ImageButton)findViewById(R.id.qrcode);
        button3=(ImageButton)findViewById(R.id.time);
        button4=(ImageButton)findViewById(R.id.customer);
        button1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                intent=new Intent(customer.this,client.class);
                startActivity(intent);
            }

        });
        button2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                intent=new Intent(customer.this,storemain.class);
                startActivity(intent);
            }

        });
        button3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                intent=new Intent(customer.this,buttontime.class);
                startActivity(intent);
            }

        });
        button4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                intent=new Intent(customer.this,customer.class);
                startActivity(intent);
            }

        });

    }
    public void onStart(){

        super.onStart();

        //execute background task

        BackTask bt=new BackTask();

        bt.execute();



    }



    //background process to make a request to server and list product
    // information

    private class BackTask extends AsyncTask<Void,Void,Void> {

        protected void onPreExecute(){

            super.onPreExecute();

            pd = new ProgressDialog(context);

            pd.setTitle("Retrieving data");

            pd.setMessage("Please wait.");

            pd.setCancelable(true);

            pd.setIndeterminate(true);

            pd.show();



        }



        protected Void doInBackground(Void...params){



            InputStream is=null;

            String result="";
            FakeX509TrustManager.allowAllSSL();
            try{



                httpclient=new DefaultHttpClient();

                httppost= new HttpPost("http://192.168.1.7/store.php");

                response=httpclient.execute(httppost);

                HttpEntity entity = response.getEntity();

                // Get our response as a String.

                is = entity.getContent();



            }catch(Exception e){



                if(pd!=null)

                    pd.dismiss(); //close the dialog if error occurs

                Log.e("ERROR", e.getMessage());



            }



            //convert response to string

            try{

                BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"),8);

                StringBuilder sb = new StringBuilder();

                String line = null;

                while ((line = reader.readLine()) != null) {

                    sb.append(line+"\n");

                }

                is.close();

                result=sb.toString();

            }catch(Exception e){

                Log.e("ERROR", "Error converting result "+e.toString());



            }



            //parse json data

            try{

                // Remove unexpected characters that might be added to beginning of the
                //  string

                result=result.substring(result.indexOf("["));

                JSONArray jArray =new JSONArray(result);

                for(int i=0;i<jArray.length();i++){

                    JSONObject json_data =jArray.getJSONObject(i);

                    Product p=new Product();

                    p.setst_name(json_data.getString("st_name"));
                    // p.setqq(json_data.getInt("qq"));
                    p.setst_phone(json_data.getString("st_phone"));



                    records.add(p);



                }





            }

            catch(Exception e){

                Log.e("ERROR", "Error pasting data "+e.toString());





            }



            return null;

        }





        protected void onPostExecute(Void result){



            if(pd!=null) pd.dismiss(); //close dialog

            Log.e("size", records.size() + "");

            adapter.notifyDataSetChanged(); //notify the ListView to get new records



        }



    }
}
