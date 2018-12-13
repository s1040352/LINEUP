package com.example.a1234.lineupall;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
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

public class waitingtime1 extends AppCompatActivity {
    private String TAG = waitingtime1.class.getSimpleName();
    private ProgressDialog progressDialog;
    private UserSession session;
    private UserInfo userInfo;


    private ImageButton button1, button2, button3, button4, BBBB, AAAA, imageButton6, ii, iii,ia;
    private TimePicker timePicker;
    NumberPicker numberpicker;
    private TextView textView25;
    private TextView textTime1, YY, textView30, textView32, textView34, textView36;
    private TextView textTime2, YYY;

    private Intent intent;
    String H;
    String M;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        progressDialog = new ProgressDialog(this);
        session = new UserSession(this);
        userInfo = new UserInfo(this);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waitingtime1);
        findView();
        numberpicker = (NumberPicker) findViewById(R.id.numberPicker1);


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
                intent = new Intent(waitingtime1.this, client.class);
                startActivity(intent);
            }

        });
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                intent = new Intent(waitingtime1.this, storemain.class);
                startActivity(intent);
            }

        });
        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                intent = new Intent(waitingtime1.this, buttontime.class);
                startActivity(intent);
            }

        });
        button4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                intent = new Intent(waitingtime1.this, customer.class);
                startActivity(intent);
            }

        });
        timePicker = (TimePicker) findViewById(R.id.timePicker);

        //timepicker時間更改時，執行下面的動作。
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

                //取得 hour的值，透過TimeFix方法。轉換成String.並初始H。
                H = TimeFix(hourOfDay);
                //取得 minute的值，透過TimeFix方法。轉換成String.並初始M。
                M = TimeFix(minute);
                //將取得的資料設定到 textTime1

            }
        });
        ia = (ImageButton) this.findViewById(R.id.ia);
        ia.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                textView25.setText(String.valueOf(numberpicker.getValue()));
            }     });





        imageButton6 = (ImageButton) this.findViewById(R.id.imageButton6);
        imageButton6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String spike_start_1_1 = textTime1.getText().toString().trim();
                String spike_start_1_2 = textView30.getText().toString().trim();
                String spike_start_2_1 = textTime2.getText().toString().trim();
                String spike_start_2_2 = textView32.getText().toString().trim();
                String spike_waiting_time = textView25.getText().toString().trim();
                String spike_end_1_1 = YY.getText().toString().trim();
                String spike_end_1_2 = textView34.getText().toString().trim();
                String spike_end_2_1 = YYY.getText().toString();
                String spike_end_2_2 = textView36.getText().toString();
                waitingtime1(spike_start_1_1, spike_start_1_2, spike_start_2_1, spike_start_2_2, spike_end_1_1, spike_end_1_2, spike_end_2_1, spike_end_2_2, spike_waiting_time);





                Intent intent = new Intent();
// 透過Intent 轉跳Activity ,Intent 只是傳遞的媒介


// Bundle 裡面是裝Activity裡面的資料等於容器

                intent.putExtra("textTime1", textTime1.getText().toString()
                );
                intent.putExtra("textTime2", textTime2.getText().toString()
                );

                intent.putExtra("textView25", textView25.getText().toString()
                );
                intent.putExtra("textView30", textView30.getText().toString()
                );
                intent.putExtra("textView32", textView32.getText().toString()
                );
                intent.putExtra("textView34", textView34.getText().toString()
                );
                intent.putExtra("textView36", textView36.getText().toString()
                );
                intent.putExtra("YY", YY.getText().toString()
                );
                intent.putExtra("YYY", YYY.getText().toString()
                );
// 要給他一個key值(名稱)"Message"取得edittext1裡的輸入字串


// 放入bundle

                intent.setClass(waitingtime1.this, buttontime2.class);

// 跳到哪一頁

                startActivity(intent);
            }
        });

        textTime1 = (TextView) findViewById(R.id.textTime1);
        textTime2 = (TextView) findViewById(R.id.textTime2);
        textView25 = (TextView) findViewById(R.id.textView25);
        textView30 = (TextView) findViewById(R.id.textView30);
        textView32 = (TextView) findViewById(R.id.textView32);
        textView34 = (TextView) findViewById(R.id.textView34);
        textView36 = (TextView) findViewById(R.id.textView36);
        YY = (TextView) findViewById(R.id.YY);
        YYY = (TextView) findViewById(R.id.YYY);
        ii = (ImageButton) findViewById(R.id.ii);
        ii.setOnClickListener(GTimeListener);
        iii = (ImageButton) findViewById(R.id.iii);
        iii.setOnClickListener(GTimeListener);
        BBBB = (ImageButton) findViewById(R.id.imageButton);
        BBBB.setOnClickListener(GTimeListener);
        AAAA = (ImageButton) findViewById(R.id.imageButton3);
        AAAA.setOnClickListener(GTimeListener);
    }

    private ImageButton.OnClickListener
            GTimeListener =
            new Button.OnClickListener() {
                //按下按鈕後將先前Timepicker取得的值 H和M，
                //設定到 textTime2
                @Override

                public void onClick(View v) {

                    switch (v.getId()) {
                        case R.id.imageButton: {
                            textTime1.setText(H);
                            textView30.setText(M);

                            break;
                        }
                        case R.id.imageButton3: {
                            textTime2.setText(H);
                            textView32.setText(M);
                            break;
                        }
                        case R.id.ii: {
                            YY.setText(H);
                            textView34.setText(M);
                            break;
                        }
                        case R.id.iii: {
                            YYY.setText(H);
                            textView36.setText(M);
                            break;
                        }
                    }


                }
            };

    //Timepicker取得的資料為int，但時間數字小於10，是不會有像06這樣的情況。
    //所以透過TimeFix這個方法將送過來的數字轉換成String，在判斷是否需要加0。
    private static String TimeFix(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }


    public void findView() {

        imageButton6 = (ImageButton) this.findViewById(R.id.imageButton6);
        textView25 = (TextView) this.findViewById(R.id.textView25);

    }
    private void waitingtime1(final String spike_start_1_1, final String spike_start_1_2, final String spike_start_2_1, final String spike_start_2_2
            ,final String spike_end_1_1,final String spike_end_1_2,final String spike_end_2_1,final String spike_end_2_2,final String spike_waiting_time){
        // Tag used to cancel the request
        String tag_string_req = "req_waitingtime1";
        progressDialog.setMessage("sending...");
        progressDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                Utils.WAINTINGTIME1_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "waitingtime1 Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        JSONObject user = jObj.getJSONObject("user");
                        String spike_start_1_1 = user.getString("spike_start_1_1");
                        String spike_start_1_2 = user.getString("spike_start_1_2");
                        String spike_start_2_1 = user.getString("spike_start_2_1");
                        String spike_start_2_2 = user.getString("spike_start_2_2");
                        String spike_end_1_1 = user.getString("spike_end_1_1");
                        String spike_end_1_2= user.getString("spike_end_1_2");
                        String spike_end_2_1 = user.getString("spike_end_2_1");
                        String spike_end_2_2= user.getString("spike_end_2_2");
                        String spike_waiting_time= user.getString("spike_waiting_time");

                        // Inserting row in users table
                        userInfo.setspike_start_1_1(spike_start_1_1);
                        userInfo.setspike_start_1_2(spike_start_1_2);
                        userInfo.setspike_start_2_1(spike_start_2_1);
                        userInfo.setspike_start_2_2(spike_start_2_2);
                        userInfo.setspike_end_1_1(spike_end_1_1);
                        userInfo.setspike_end_1_2(spike_end_1_2);
                        userInfo.setspike_end_2_1(spike_end_2_1);
                        userInfo.setspike_end_2_2(spike_end_2_2);
                        userInfo.setspike_waiting_time(spike_waiting_time);
                        session.setLoggedin(false);

                     //    startActivity(new Intent(waitingtime1.this, buttontime2.class));
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
                Log.e(TAG, "waintingtime Error: " + error.getMessage());
                toast("Unknown Error occurred");
                progressDialog.hide();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("spike_start_1_1", spike_start_1_1);
                params.put("spike_start_1_2", spike_start_1_2);
                params.put("spike_start_2_1", spike_start_2_1);
                params.put("spike_start_2_2", spike_start_2_2);
                params.put("spike_end_1_1", spike_end_1_1);
                params.put("spike_end_1_2", spike_end_1_2);
                params.put("spike_end_2_1", spike_end_2_1);
                params.put("spike_end_2_2", spike_end_2_2);
                params.put("spike_waiting_time", spike_waiting_time);
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
