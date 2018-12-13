package com.example.a1234.lineupall;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

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


public class buttontime2 extends AppCompatActivity {
    JSONObject json = null;
    JSONObject json1 = null;
    String str = "";
    String str1 = "";
    HttpResponse response,response1;
    Context context;
   // Button button7;

    private ImageButton button1, button2, button3, button4, imageButton1, imageButton2, imageButton4;
    private Intent intent;
    private TextView b, c, k, l;
    private TextView tv, tv2, h, r;
    public static final int MSG_ONE = 1;
    private Handler handler = new Handler() {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setFlags(flag, flag);
        setContentView(R.layout.activity_buttontime2);
        initView();
        new TimeThread().start();


        button1 = (ImageButton) findViewById(R.id.client);
        button2 = (ImageButton) findViewById(R.id.qrcode);
        button3 = (ImageButton) findViewById(R.id.time);
        button4 = (ImageButton) findViewById(R.id.customer);
        //button7 = (Button)findViewById(R.id.button7);
        imageButton1 = (ImageButton) findViewById(R.id.imageButton1);
        imageButton2 = (ImageButton) findViewById(R.id.imageButton2);
        b = (TextView) findViewById(R.id.textView17);
        c = (TextView) findViewById(R.id.textView12);
        k = (TextView) findViewById(R.id.textView29);
        l = (TextView) findViewById(R.id.textView31);

        imageButton4 = (ImageButton) findViewById(R.id.imageButton4);
        String b25 = getIntent().getStringExtra("textView25");
        String c28 = getIntent().getStringExtra("textView37");
        String textTime1 = getIntent().getStringExtra("textTime1");
        String textTime2 = getIntent().getStringExtra("textTime2");
        String YY = getIntent().getStringExtra("YY");
        String YYY = getIntent().getStringExtra("YYY");
        String textView30 = getIntent().getStringExtra("textView30");
        String textView32 = getIntent().getStringExtra("textView32");
        String textView34 = getIntent().getStringExtra("textView34");
        String textView36 = getIntent().getStringExtra("textView36");
        // b.setText(b25);
        //  c.setText(c28);

        //  k.setText(textTime1+":"+ textView30+"~"+textTime2+":"+ textView32);
        //  l.setText(YY+":"+ textView34+"~"+YYY+":"+textView36);
        if (b25 == null) {
            b.setText("15");
        }
        if (c28 == null) {
            c.setText("15");
        }
        if (textTime1 == null && textTime2 == null) {
            k.setText("11:00~13:00");
        }
        if (YY == null && YYY == null) {
            l.setText("17:00~19:00");
        }
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                intent = new Intent(buttontime2.this, client.class);
                startActivity(intent);
            }

        });
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                intent = new Intent(buttontime2.this, storemain.class);
                startActivity(intent);
            }

        });
        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                intent = new Intent(buttontime2.this, buttontime.class);
                startActivity(intent);
            }

        });
        button4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                intent = new Intent(buttontime2.this, customer.class);
                startActivity(intent);
            }

        });
        imageButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                intent = new Intent(buttontime2.this, waitingtime2.class);
                startActivity(intent);
            }

        });
        imageButton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                intent = new Intent(buttontime2.this, waitingtime1.class);
                startActivity(intent);
            }

        });
        imageButton4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent();
                //     intent.putExtra("b25", b.getText().toString()
                //       );
                //      intent.putExtra("c28", c.getText().toString()
                //      );
                //      intent.putExtra("h", h.getText().toString()
                //      );

                intent.setClass(buttontime2.this, buttontime.class);
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
        tv = findViewById(R.id.tv);
        tv2 = findViewById(R.id.tv2);
        h = findViewById(R.id.h);
        // r=findViewById(R.id.r);
    }

    private void getNetTime() {
        String textTime1 = getIntent().getStringExtra("textTime1");
        String textTime2 = getIntent().getStringExtra("textTime2");
        String YY = getIntent().getStringExtra("YY");
        String YYY = getIntent().getStringExtra("YYY");
        String textView30 = getIntent().getStringExtra("textView30");
        String textView32 = getIntent().getStringExtra("textView32");
        String textView34 = getIntent().getStringExtra("textView34");
        String textView36 = getIntent().getStringExtra("textView36");
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
            final String a = formatte.format(calendar.getTime());
            final String b = formatt.format(calendar.getTime());
            int int1 = Integer.parseInt(a);
            int int11 = Integer.parseInt(b);

            int aa = Integer.parseInt(textTime1);
            int bb = Integer.parseInt(textView30);
            int cc = Integer.parseInt(textTime2);
            int dd = Integer.parseInt(textView32);
            int ee = Integer.parseInt(YY);
            int ff = Integer.parseInt(YYY);
            int gg = Integer.parseInt(textView34);
            int hh = Integer.parseInt(textView36);

            //      r.setText(qq);
            //15:30-17:00
         //   if (int1 >= aa && int1 <= bb || int1 >= ee && int1 <= ff) {
         //       if (int1 == aa && int11 < cc || int1 == bb && int11 > dd || int1 == ee && int11 < gg || int1 == ff && int11 > hh) {
                   // h.setText("離峰");
         //       } else
                //    h.setText("尖峰");
        //    } else {
              //  h.setText("離峰");
       //     }
           // runOnUiThread(new Runnable() {
           //     @Override
            //    public void run() {
                 //   tv.setText(a);
                 //   tv2.setText(b);

              //  }
//
         //   });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class GetTextViewData extends AsyncTask<Void, Void, Void> {
        public Context context;


        public GetTextViewData(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            HttpClient myClient = new DefaultHttpClient();
            HttpPost myConnection = new HttpPost("http://192.168.1.7/timing1.php");
            HttpPost myConnection1 = new HttpPost("http://192.168.1.7/timing.php");
            try {
                response = myClient.execute(myConnection);
                response1 = myClient.execute(myConnection1);
                str = EntityUtils.toString(response.getEntity(), "UTF-8");
                str1 = EntityUtils.toString(response1.getEntity(), "UTF-8");
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
                JSONArray jArray = new JSONArray(str);
                JSONArray jArray1 = new JSONArray(str1);
                json = jArray.getJSONObject(0);
                json1 = jArray1.getJSONObject(0);


            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            JSONArray jsonArray = null;
            JSONArray jsonArray1 = null;
            JSONObject jsonObject,jsonObject1;
            try {
                jsonArray = new JSONArray(str);
                jsonArray1 = new JSONArray(str1);
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    k.setText(jsonObject.getString("spike_start_1_1")+":"+jsonObject.getString("spike_start_1_2")
                            +"~"
                            +jsonObject.getString("spike_start_2_1")+":"+jsonObject.getString("spike_start_2_2"));
                    l.setText(jsonObject.getString("spike_end_1_1")+":"+jsonObject.getString("spike_end_1_2")
                            +"~"
                            +jsonObject.getString("spike_end_2_1")+":"+jsonObject.getString("spike_end_2_2"));
                    //  c.setText(jsonObject.getString("peak_waiting_time"));
                    b.setText(jsonObject.getString("spike_waiting_time"));
                }
                for (int i = 0; i < jsonArray1.length(); i++) {
                    jsonObject1 = jsonArray1.getJSONObject(i);

                    c.setText(jsonObject1.getString("peak_waiting_time"));

                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }
    }
}

