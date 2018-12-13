package com.example.a1234.lineupall;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

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

public class buttontime1 extends AppCompatActivity {
    private String TAG = buttontime1.class.getSimpleName();
    private ProgressDialog progressDialog;
    private UserSession session;
    private UserInfo userInfo;

    private TimePicker timePicker;
    private ImageButton button1,button2,button3,button4,BBBB,AAAA,imageButton6,Monday,Sunday,SB,SB2;
    private TextView textTime1;
    private TextView textTime2;
    private TextView ST,ST2;
    private TextView textView21;
    private TextView textView27;
    private Intent intent;
    String H;
    String M;

    private CheckBox checkbox1;
    private CheckBox checkbox2;
    private CheckBox checkbox3;
    private CheckBox checkbox4;
    private CheckBox checkbox5;
    private CheckBox checkbox6;
    private CheckBox checkbox7;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressDialog  = new ProgressDialog(this);
        session         = new UserSession(this);
        userInfo        = new UserInfo(this);



        setContentView(R.layout.activity_buttontime1);
        findView();
        button1=(ImageButton)findViewById(R.id.client);
        button2=(ImageButton)findViewById(R.id.qrcode);
        button3=(ImageButton)findViewById(R.id.time);
        button4=(ImageButton)findViewById(R.id.customer);

        button1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                intent=new Intent(buttontime1.this,client.class);
                startActivity(intent);
            }

        });
        button2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                intent=new Intent(buttontime1.this,storemain.class);
                startActivity(intent);
            }

        });
        button3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                intent=new Intent(buttontime1.this,buttontime.class);
                startActivity(intent);
            }

        });
        button4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                intent=new Intent(buttontime1.this,customer.class);
                startActivity(intent);
            }

        });






        timePicker    =    (TimePicker)findViewById(R.id.timePicker);

        //timepicker時間更改時，執行下面的動作。
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener(){
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

                //取得 hour的值，透過TimeFix方法。轉換成String.並初始H。
                H = TimeFix(hourOfDay);
                //取得 minute的值，透過TimeFix方法。轉換成String.並初始M。
                M = TimeFix(minute);
                //將取得的資料設定到 textTime1

            }
        });




        imageButton6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String start1 = textTime1.getText().toString().trim();
                String start2 = textTime2.getText().toString().trim();
                String weekday = textView21.getText().toString().trim();
                String end1 = ST.getText().toString().trim();
                String end2 = ST2.getText().toString();
                String holiday = textView27.getText().toString();

                buttontime1(start1, start2, end1,end2,weekday,holiday);




                Intent intent = new Intent();
// 透過Intent 轉跳Activity ,Intent 只是傳遞的媒介




// Bundle 裡面是裝Activity裡面的資料等於容器

                intent.putExtra( "textTime1", textTime1.getText().toString()
                );
                intent.putExtra( "textTime2", textTime2.getText().toString()
                );
                intent.putExtra( "textView21", textView21.getText().toString()
                );
                intent.putExtra( "textView27", textView27.getText().toString()
                );
                intent.putExtra( "ST", ST.getText().toString()
                );
                intent.putExtra( "ST2", ST2.getText().toString()
                );
// 要給他一個key值(名稱)"Message"取得edittext1裡的輸入字串



// 放入bundle

                intent.setClass(buttontime1.this, buttontime.class);

// 跳到哪一頁

                startActivity(intent);
            }
        });
        Monday.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String str = "";//串複選題多個答案
// 複選按鈕 每個選項用if判斷是否被按下
                if (checkbox1.isChecked()) {
                    str+="Mon ";
                }
                if (checkbox2.isChecked()) {
                    str+="Tue ";

                }
                if (checkbox3.isChecked()) {
                    str+="Wed ";

                }
                if (checkbox4.isChecked()) {
                    str+="Thurs ";

                }
                if (checkbox5.isChecked()) {
                    str+="Fri ";

                }
                if (checkbox6.isChecked()) {

                    str+="Sat ";

                }
                if (checkbox7.isChecked()) {
                    str+="Sun ";

                }
                textView21.setText(str);
            }
        });
        Sunday.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String str = "";//串複選題多個答案
// 複選按鈕 每個選項用if判斷是否被按下
                if (checkbox1.isChecked()) {
                    str+="Mon ";
                }
                if (checkbox2.isChecked()) {
                    str+="Tue ";

                }
                if (checkbox3.isChecked()) {
                    str+="Wed ";

                }
                if (checkbox4.isChecked()) {
                    str+="Thurs ";

                }
                if (checkbox5.isChecked()) {
                    str+="Fri ";

                }
                if (checkbox6.isChecked()) {

                    str+="Sat ";

                }
                if (checkbox7.isChecked()) {
                    str+="Sun ";

                }
                textView27.setText(str);
            }
        });

        textTime1    =    (TextView)findViewById(R.id.textTime1);
        textTime2    =    (TextView)findViewById(R.id.textTime2);
        ST=(TextView)findViewById(R.id.ST);
        ST2=(TextView)findViewById(R.id.ST2);
        BBBB=(ImageButton) findViewById(R.id.imageButton);
        BBBB.setOnClickListener(GTimeListener);
        BBBB.getBackground().setAlpha(0);
        AAAA=(ImageButton) findViewById(R.id.imageButton3);
        AAAA.getBackground().setAlpha(0);
        AAAA.setOnClickListener(GTimeListener);
        AAAA=(ImageButton) findViewById(R.id.imageButton3);
        AAAA.getBackground().setAlpha(0);
        AAAA.setOnClickListener(GTimeListener);
        SB=(ImageButton) findViewById(R.id.SB);
        SB.getBackground().setAlpha(0);
        SB.setOnClickListener(GTimeListener);
        SB2=(ImageButton) findViewById(R.id.SB2);
        SB2.getBackground().setAlpha(0);
        SB2.setOnClickListener(GTimeListener);
    }

    private ImageButton.OnClickListener
            GTimeListener =
            new Button.OnClickListener(){
                //按下按鈕後將先前Timepicker取得的值 H和M，
                //設定到 textTime2
                @Override

                public void onClick(View v) {

                    switch (v.getId()) {
                        case R.id.imageButton: {
                            textTime1.setText(H + ":" + M);
                            break;
                        }
                        case R.id.imageButton3: {
                            textTime2.setText(H + ":" + M);
                            break;
                        }
                        case R.id.SB: {
                            ST.setText(H + ":" + M);
                            break;
                        }
                        case R.id.SB2: {
                            ST2.setText(H + ":" + M);
                            break;
                        }

                    }


                }
            };

    //Timepicker取得的資料為int，但時間數字小於10，是不會有像06這樣的情況。
    //所以透過TimeFix這個方法將送過來的數字轉換成String，在判斷是否需要加0。
    private static String TimeFix(int c){
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }


    public void findView() {
        checkbox1 = (CheckBox) this.findViewById(R.id.checkBox1);
        checkbox2 = (CheckBox) this.findViewById(R.id.checkBox2);
        checkbox3 = (CheckBox) this.findViewById(R.id.checkBox3);
        checkbox4 = (CheckBox) this.findViewById(R.id.checkBox4);
        checkbox5 = (CheckBox) this.findViewById(R.id.checkBox5);
        checkbox6 = (CheckBox) this.findViewById(R.id.checkBox6);
        checkbox7 = (CheckBox) this.findViewById(R.id.checkBox7);
        imageButton6=(ImageButton)this.findViewById(R.id.imageButton6);
        Monday=(ImageButton)this.findViewById(R.id.monday);
        Sunday=(ImageButton)this.findViewById(R.id.sunday);
        textView21= (TextView) this.findViewById(R.id.textView21);
        textView27= (TextView) this.findViewById(R.id.textView27);

    }
    private void buttontime1(final String start1, final String start2, final String end1, final String end2
            ,final String weekday,final String holiday){
        // Tag used to cancel the request
        String tag_string_req = "req_buttontime1";
        progressDialog.setMessage("sending...");
        progressDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                Utils.BUTTONTIME1_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Buttomtime1 Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        JSONObject user = jObj.getJSONObject("user");
                        String start1 = user.getString("start1");
                        String start2 = user.getString("start2");
                        String end1 = user.getString("end1");
                        String end2 = user.getString("end2");
                        String weekday = user.getString("weekday");
                        String holiday = user.getString("holiday");

                        // Inserting row in users table
                        userInfo.setstart1(start1);
                        userInfo.setstart2(start2);
                        userInfo.setend1(end1);
                        userInfo.setend2(end2);
                        userInfo.setweekday(weekday);
                        userInfo.setholiday(holiday);
                        session.setLoggedin(false);

                  //      startActivity(new Intent(buttontime1.this, buttontime.class));
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
                Log.e(TAG, "buttontime Error: " + error.getMessage());
                toast("Unknown Error occurred");
                progressDialog.hide();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("start1", start1);
                params.put("start2", start2);
                params.put("end1", end1);
                params.put("end2", end2);
                params.put("weekday", weekday);
                params.put("holiday", holiday);

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
