package com.example.a1234.lineupall;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
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

public class RegisterActivity extends Activity {
    private String TAG = RegisterActivity.class.getSimpleName();
    private EditText edittext1,edittext2,edittext3,edittext4,edittext5,edittext6;
    private Button button;
    private ProgressDialog progressDialog;
    private UserSession session;
    private UserInfo userInfo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edittext1=(EditText)findViewById(R.id.editview1);
        edittext2=(EditText)findViewById(R.id.editview2);
        edittext3=(EditText)findViewById(R.id.editview3);
        edittext4=(EditText)findViewById(R.id.editview4);
        edittext5=(EditText)findViewById(R.id.editview5);
        edittext6=(EditText)findViewById(R.id.editview6);
        button=(Button)findViewById(R.id.tijiao);
        progressDialog  = new ProgressDialog(this);
        session         = new UserSession(this);
        userInfo        = new UserInfo(this);

        button.setOnClickListener(new OnClickListener(){

            public void onClick(View v) {
                // TODO Auto-generated method stub
                String st_name = edittext5.getText().toString().trim();
                String st_username = edittext1.getText().toString().trim();
                String st_address = edittext4.getText().toString().trim();
                String st_phone = edittext6.getText().toString().trim();
                String password = edittext2.getText().toString().trim();
                String repassword = edittext3.getText().toString().trim();

                signup(st_name, st_username, st_address, st_phone,password);

                if (!password.equals(repassword)) {
                    Toast.makeText(RegisterActivity.this, "两次密码不一致", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
        private void signup(final String st_name, final String st_username, final String st_address, final String st_phone,final String password){

        // Tag used to cancel the request
            String tag_string_req = "req_signup";
            progressDialog.setMessage("Signing up...");
            progressDialog.show();
            FakeX509TrustManager.allowAllSSL();
            StringRequest strReq = new StringRequest(Request.Method.POST,
                    Utils.REGISTER_URL, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Log.d(TAG, "Register Response: " + response.toString());




                    try {
                        JSONObject jObj = new JSONObject(response);
                        boolean error = jObj.getBoolean("error");

                        // Check for error node in json
                        if (!error) {
                            JSONObject user = jObj.getJSONObject("user");
                            String st_name = user.getString("st_name");
                            String st_username = user.getString("st_username");
                            String st_address = user.getString("st_address");
                            String st_phone = user.getString("st_phone");

                            // Inserting row in users table
                            userInfo.setName(st_name);
                            userInfo.setUsername(st_username);
                            userInfo.setAddress(st_address);
                            userInfo.setPhone(st_phone);
                            session.setLoggedin(false);

                            startActivity(new Intent(RegisterActivity.this, Login.class));
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
                    Log.e(TAG, "Login Error: " + error.getMessage());
                    toast("Unknown Error occurred");
                    progressDialog.hide();
                }
            }) {

                @Override
                protected Map<String, String> getParams() {
                    // Posting parameters to login url
                    Map<String, String> params = new HashMap<>();
                    params.put("st_name", st_name);
                    params.put("st_username", st_username);
                    params.put("st_address", st_address);
                    params.put("st_phone", st_phone);
                    params.put("password", password);

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