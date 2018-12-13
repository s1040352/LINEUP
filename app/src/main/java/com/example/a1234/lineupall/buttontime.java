package com.example.a1234.lineupall;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import prefs.UserInfo;
import prefs.UserSession;

public class buttontime extends AppCompatActivity {
    private String TAG = buttontime.class.getSimpleName();
    private ProgressDialog progressDialog;
    private UserSession session;
    private UserInfo userInfo;



    String SWT = "";
    String PWT = "";
    String  STH1= "",STH2= "",STM1= "",STM2= "",ETH1= "",ETH2= "",ETM1= "",ETM2= "";



    JSONObject json = null;
    JSONObject json1 = null;
    JSONObject json2 = null;
    String str = "";
    String str1 = "";
    String str2 = "";
    HttpResponse response,response1,response2;
    Context context;




    private ImageButton button1, button2, button3, button4,imageButton1,imageButton2;
    private TextView b,c,f,h,l,o,p,tv_time;
    private Intent intent;


    public static final int MSG_ONE = 1;
    private android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //通过消息的内容msg.what  分别更新ui
            switch (msg.what) {
                case MSG_ONE:
                    //获取网络时间
                    //请求网络资源是耗时操作。放到子线程中进行
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            getNetTime();
                        }
                    }).start();
                    break;
                default:
                    break;
            }
        }
    };
    // private TextView a,b,c,d,e,f,g,h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        progressDialog = new ProgressDialog(this);
        session = new UserSession(this);
        userInfo = new UserInfo(this);



        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setFlags(flag, flag);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttontime);
        initView();
        new TimeThread().start();
        button1 = (ImageButton) findViewById(R.id.client);
        button2 = (ImageButton) findViewById(R.id.qrcode);
        button3 = (ImageButton) findViewById(R.id.time);
        button4 = (ImageButton) findViewById(R.id.customer);
        c=(TextView)findViewById(R.id.c);
        b=(TextView)findViewById(R.id.b);

        h=(TextView)findViewById(R.id.h);
        l=(TextView)findViewById(R.id.l);
        o=(TextView)findViewById(R.id.o);
//        p=(TextView)findViewById(R.id.p);
        imageButton1 = (ImageButton) findViewById(R.id.imageButton1);
        imageButton2 = (ImageButton) findViewById(R.id.imageButton2);

        // Calendar mCalendar = new GregorianCalendar();
        //  p.setText(mCalendar.get(Calendar.HOUR));

        String textTime1 = getIntent().getStringExtra("textTime1");
        String textTime2 =  getIntent().getStringExtra("textTime2");
        String ST =  getIntent().getStringExtra("ST");
        String ST2 =  getIntent().getStringExtra("ST2");
        String textView21 =  getIntent().getStringExtra("textView21");
        String textView27 =  getIntent().getStringExtra("textView27");
        String b25 =  getIntent().getStringExtra("b25");
        String c28 =  getIntent().getStringExtra("c28");
        String hhh =  getIntent().getStringExtra("h");
      //  c.setText(textTime1+"~"+textTime2);
   //     b.setText(textView21);

        //  if(hhh.equals("尖峰")){
        //  f.setText(b25+"min");}
        // else if(hhh..equals("離峰")) {
        // h.setText(c28+"min"); }
       // l.setText(textView27);
     //   o.setText(ST+"~"+ST2);
   /*     if (textTime1==null&&textView21==null){
            c.setText("9:00~21:00");
            b.setText("一二三四五");
            l.setText("六日");
            o.setText("10:00~22:00");} */
        //      if(b25==null && c28==null){
        //         f.setText("15min");
        //        h.setText("15min");}
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                intent = new Intent(buttontime.this, client.class);
                startActivity(intent);
            }

        });
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                intent = new Intent(buttontime.this, storemain.class);
                startActivity(intent);
            }

        });
        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                intent = new Intent(buttontime.this, buttontime.class);
                startActivity(intent);

            }

        });
        button4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                intent = new Intent(buttontime.this, customer.class);
                startActivity(intent);
                String waitingtime = h.getText().toString();
                buttontime(waitingtime);
            }

        });
        imageButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                intent = new Intent(buttontime.this, buttontime2.class);
                startActivity(intent);
            }

        });
        imageButton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                intent = new Intent(buttontime.this, buttontime1.class);
                startActivity(intent);
            }

        });

        new GetTextViewData(context).execute();
    }

    public class TimeThread extends Thread {
        //重写run方法
        @Override
        public void run() {
            super.run();
            // do-while  一 什么什么 就
            do {
                try {
                    //每隔一秒 发送一次消息
                    Thread.sleep(1000);
                    Message msg = new Message();
                    //消息内容 为MSG_ONE
                    msg.what = MSG_ONE;
                    //发送
                    handler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        }
    }

    private void initView() {


        h = findViewById(R.id.h);
        // r=findViewById(R.id.r);
    }

    private void getNetTime() {

        URL url = null;//取得资源对象
        try {
            url = new URL("http://www.baidu.com");
            //url = new URL("http://www.ntsc.ac.cn");//中国科学院国家授时中心
            URLConnection uc = url.openConnection();//生成连接对象
            uc.connect(); //发出连接
            long ld = uc.getDate(); //取得网站日期时间
            DateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
            DateFormat formatte = new SimpleDateFormat("HH");
            DateFormat formatt = new SimpleDateFormat("mm");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(ld);
            final String format = formatter.format(calendar.getTime());
            final String a = formatte.format(calendar.getTime());
            final String b = formatt.format(calendar.getTime());
            int int1 = Integer.parseInt(a);
            int int11 = Integer.parseInt(b);
            int aa =Integer.parseInt(STH1);
            int bb = Integer.parseInt(STH2);
            int cc = Integer.parseInt(ETH1);
            int dd = Integer.parseInt(ETH2);
            int ee = Integer.parseInt(STM1);
            int ff = Integer.parseInt(STM1);
            int gg = Integer.parseInt(ETM1);
            int hh = Integer.parseInt(ETM2);
            //STH1 : STM1 - ETH1 : ETM1
            if(int1>=aa&&int1<=cc||int1>=bb&&int1<=dd)
            {
                if(int1==aa&&int11>=ee||int1==cc&&int11<gg||int1==bb&&int11>=ff||int1==dd&&int11<hh){
                    h.setText(SWT);
                }
                else
                    h.setText(PWT);
            }
            else
                h.setText(PWT);




        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private class GetTextViewData extends AsyncTask<Void, Void, Void>
    {
        public Context context;


        public GetTextViewData(Context context)
        {
            this.context = context;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {

            HttpClient myClient = new DefaultHttpClient();
            HttpPost myConnection = new HttpPost("http://192.168.1.7/onair_timing.php");
            HttpPost myConnection1 = new HttpPost("http://192.168.1.7/timing.php");
            HttpPost myConnection2 = new HttpPost("http://192.168.1.7/timing1.php");
            try {
                response = myClient.execute(myConnection);
                response1 = myClient.execute(myConnection1);
                response2 = myClient.execute(myConnection2);
                str = EntityUtils.toString(response.getEntity(), "UTF-8");
                str1 = EntityUtils.toString(response1.getEntity(), "UTF-8");
                str2 = EntityUtils.toString(response2.getEntity(), "UTF-8");
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            try{
                JSONArray jArray = new JSONArray(str);
                JSONArray jArray1 = new JSONArray(str1);
                JSONArray jArray2 = new JSONArray(str2);
                json = jArray.getJSONObject(0);
                json1 = jArray1.getJSONObject(0);
                json2 = jArray2.getJSONObject(0);



            } catch ( JSONException e) {
                e.printStackTrace();
            }

            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void result)
        {
            JSONArray jsonArray = null;
            JSONArray jsonArray1 = null;
            JSONArray jsonArray2 = null;
            JSONObject jsonObject,jsonObject1,jsonObject2;

            try {
                jsonArray = new JSONArray(str);
                jsonArray1 = new JSONArray(str1);
                jsonArray2 = new JSONArray(str2);

                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    b.setText(jsonObject.getString("weekday") );
                    c.setText(jsonObject.getString("start1")+"~"+jsonObject.getString("start2"));
                    l.setText(jsonObject.getString("holiday"));
                    o.setText(jsonObject.getString("end1")+"~"+jsonObject.getString("end2"));
                }
                for (int i = 0; i < jsonArray1.length(); i++) {
                    jsonObject1 = jsonArray1.getJSONObject(i);
                    PWT=jsonObject1.getString("peak_waiting_time");


                }
                for (int i = 0; i < jsonArray2.length(); i++) {
                    jsonObject2 = jsonArray2.getJSONObject(i);
                    STH1=jsonObject2.getString("spike_start_1_1");
                    STM1=jsonObject2.getString("spike_start_1_2");
                    STH2=jsonObject2.getString("spike_start_2_1");
                    STM2=jsonObject2.getString("spike_start_2_2");
                    ETH1=jsonObject2.getString("spike_end_1_1");
                    ETM1=jsonObject2.getString("spike_end_1_2");
                    ETH2=jsonObject2.getString("spike_end_2_1");
                    ETM2=jsonObject2.getString("spike_end_2_2");
                    SWT=jsonObject2.getString("spike_waiting_time");


                }


            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }



        }
    }
    private void buttontime(final String waitingtime){
        FakeX509TrustManager.allowAllSSL();
        // Tag used to cancel the request
        String tag_string_req = "req_waitingtime";
        progressDialog.setMessage("sending...");
        progressDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                Utils.BUTTONTIME_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "waitingtime Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        JSONObject user = jObj.getJSONObject("user");
                        String waitingtime= user.getString("waitingtime");

                        // Inserting row in users table
                        userInfo.setwaitingtime(waitingtime);

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
                Log.e(TAG, "waintingtime Error: " + error.getMessage());
                toast("Unknown Error occurred");
                progressDialog.hide();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("waitingtime", waitingtime);
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