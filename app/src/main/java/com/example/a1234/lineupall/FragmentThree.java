package com.example.a1234.lineupall;
import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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


public class FragmentThree extends android.app.Fragment{
    private Button btnUpdate;
    Activity context;

    HttpPost httppost;

    StringBuffer buffer;

    HttpResponse response;

    HttpClient httpclient;

    ProgressDialog pd;

    CustomAdapter2 adapter;

    ListView listProduct;

    ArrayList<Product> records;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment_three, container, false);
        btnUpdate = (Button) view.findViewById(R.id.btnUpdate);

        this.context = getActivity();
        records=new ArrayList<Product>();
        listProduct=(ListView) view.findViewById(R.id.product_list);

        adapter=new CustomAdapter2(context, R.layout.list_item2,R.id.pro_n,
                records);

        listProduct.setAdapter(adapter);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnUpdate:
                        Intent intent = new Intent(getActivity(), editpage.class);
                        startActivity(intent);
                        break;
                }
            }

        });

        return view;
    }
    public void onStart(){

        super.onStart();

        //execute background task

       FragmentThree.BackTask bt=new FragmentThree.BackTask();

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

            try{



                httpclient=new DefaultHttpClient();

                httppost= new HttpPost("http://192.168.1.18/client1.php");

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

                    p.setclusername(json_data.getString("name"));
                    // p.setqq(json_data.getInt("qq"));
                    p.setclphone(json_data.getString("tel"));



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
