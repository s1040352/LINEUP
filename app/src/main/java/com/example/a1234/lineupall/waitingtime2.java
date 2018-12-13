package com.example.a1234.lineupall;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import prefs.UserInfo;
import prefs.UserSession;

public class waitingtime2 extends AppCompatActivity {
    private String TAG = waitingtime2.class.getSimpleName();
    private ProgressDialog progressDialog;
    private UserSession session;
    private UserInfo userInfo;


    private ImageButton button1, button2, button3, button4,imageButton6,monday;
    NumberPicker numberpicker;
    private TextView textView28,textView37;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        progressDialog = new ProgressDialog(this);
        session = new UserSession(this);
        userInfo = new UserInfo(this);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waitingtime2);
        findView();
        numberpicker = (NumberPicker)findViewById(R.id.numberPicker2);



        numberpicker.setMinValue(0);

        numberpicker.setMaxValue(30);
        button1 = (ImageButton) findViewById(R.id.client);
        button2 = (ImageButton) findViewById(R.id.qrcode);
        button3 = (ImageButton) findViewById(R.id.time);
        button4 = (ImageButton) findViewById(R.id.customer);

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                intent = new Intent(waitingtime2.this, client.class);
                startActivity(intent);
            }

        });
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                intent = new Intent(waitingtime2.this, storemain.class);
                startActivity(intent);
            }

        });
        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                intent = new Intent(waitingtime2.this, buttontime.class);
                startActivity(intent);
            }

        });
        button4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                intent = new Intent(waitingtime2.this, customer.class);
                startActivity(intent);
            }

        });
        imageButton6 = (ImageButton) this.findViewById(R.id.imageButton6);
        monday = (ImageButton) this.findViewById(R.id.monday);
        monday.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                textView37.setText(String.valueOf(numberpicker.getValue()));
            }     });
        imageButton6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String peak_waiting_time = textView37.getText().toString();
                waitingtime2(peak_waiting_time);


                Intent intent = new Intent();
                intent.putExtra( "textView37", textView37.getText().toString()
                );
// 要給他一個key值(名稱)"Message"取得edittext1裡的輸入字串



// 放入bundle

                intent.setClass(waitingtime2.this, buttontime2.class);

// 跳到哪一頁

                startActivity(intent);
            }
        });
            }
    public void findView() {
        imageButton6=(ImageButton)this.findViewById(R.id.imageButton6);
        textView28= (TextView) this.findViewById(R.id.textView28);
        textView37= (TextView) this.findViewById(R.id.textView37);

    }
    private void waitingtime2(final String peak_waiting_time){
        FakeX509TrustManager.allowAllSSL();
        // Tag used to cancel the request
        String tag_string_req = "req_waitingtime2";
        progressDialog.setMessage("sending...");
        progressDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                Utils.WAINTINGTIME2_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "waitingtime2 Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        JSONObject user = jObj.getJSONObject("user");
                        String peak_waiting_time= user.getString("peak_waiting_time");

                        // Inserting row in users table
                        userInfo.setpeak_waiting_time(peak_waiting_time);

                        session.setLoggedin(false);

                      //  startActivity(new Intent(waitingtime2.this, buttontime2.class));
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        toast(errorMsg);
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    toast("Json error: " + e.getMessage());

                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "waintingtime2 Error: " + error.getMessage());
                toast("Unknown Error occurred");
                progressDialog.hide();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("peak_waiting_time", peak_waiting_time);
                return params;
            }

        };

        // Adding request to request queue
        AndroidLoginController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void toast(String x){
        Toast.makeText(this, x, Toast.LENGTH_SHORT).show();
    }
}
