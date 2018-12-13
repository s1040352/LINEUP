package com.example.a1234.lineupall;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import prefs.UserInfo;
import prefs.UserSession;

public class Login extends Activity implements View.OnClickListener{
    private static final String TAG = Login.class.getSimpleName();
    private TextView textview,gohome;
    private Button button1;
    private EditText nameText,passText;
    private Intent intent;
    private ProgressDialog progressDialog;
    private UserSession session;
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nameText=(EditText)findViewById(R.id.username);
        passText=(EditText)findViewById(R.id.pasw);
        button1=(Button)findViewById(R.id.tijiao);
        textview=(TextView)findViewById(R.id.zhuce);
        gohome = (TextView) findViewById(R.id.suibian);
        progressDialog  = new ProgressDialog(this);
        session         = new UserSession(this);
        userInfo        = new UserInfo(this);

        if(session.isUserLoggedin()){
            startActivity(new Intent(this, storemain.class));
            finish();
        }

        button1.setOnClickListener(this);
        textview.setOnClickListener(this);
        gohome.setOnClickListener(this);

    }

    private void login(final String st_username,final String password){
        // Tag used to cancel the request
        String tag_string_req = "req_login";
        progressDialog.setMessage("Logging in...");
        progressDialog.show();
       // if (st_username!=null&&password!=null) {
        //    Intent b = new Intent(Login.this, storemain.class);
         //   startActivity(b);
        //}
        StringRequest strReq = new StringRequest(Request.Method.POST,
                Utils.LOGIN_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        // Now store the user in SQLite
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
                        session.setLoggedin(true);

                        startActivity(new Intent(Login.this, storemain.class));
                        finish();
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
                params.put("st_username", st_username);
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

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tijiao:
                String st_username = nameText.getText().toString().trim();
                String password  = passText.getText().toString().trim();

                login(st_username, password);
                break;

            case R.id.zhuce:
                startActivity(new Intent(this, RegisterActivity.class));
                break;

            case R.id.suibian:
                startActivity(new Intent(this, LINEUPMAIN.class));
                break;
        }
    }
}