package com.example.a1234.lineupall;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

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
import java.util.EnumMap;
import java.util.Map;

public class storemain extends AppCompatActivity
{
    TextView textview5,textview7;
    JSONObject json = null;
    JSONObject json1 = null;
    String str = "";
    String str1 = "";
    HttpResponse response,response1;
    Context context;


    private static String DATABASE_TABLE = "students";
    private SQLiteDatabase db;
    private StdDBHelper dbHelper;
    private EditText txtID, txtName;
    private ImageButton button1,button2,button3,button4;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storemain);

        button1=(ImageButton)findViewById(R.id.client);
        button2=(ImageButton)findViewById(R.id.qrcode);
        button3=(ImageButton)findViewById(R.id.time);
        button4=(ImageButton)findViewById(R.id.customer);

        textview5=(TextView)findViewById(R.id.textView5);
        textview7=(TextView)findViewById(R.id.textView7);

        button1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                intent=new Intent(storemain.this,client.class);
                startActivity(intent);
            }

        });
        button2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                intent=new Intent(storemain.this,storemain.class);
                startActivity(intent);
            }

        });
        button3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                intent=new Intent(storemain.this,buttontime.class);
                startActivity(intent);
            }

        });
        button4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                intent=new Intent(storemain.this,customer.class);
                startActivity(intent);
            }

        });


        // 取得EditText元件
        //txtName = (EditText) findViewById(R.id.txtName);
        //txtGrade=(EditText) findViewById(R.id.txtGrade);
        new GetTextViewData(context).execute();

    }

    public void onbuttonclick(View v)
    {

        // QR code 的內容
        String QRCodeContent = "https://192.168.1.7/input.php";
        // QR code 寬度
        int QRCodeWidth = 800;
        // QR code 高度
        int QRCodeHeight = 800;
        // QR code 內容編碼
        Map<EncodeHintType, Object> hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        MultiFormatWriter writer = new MultiFormatWriter();
        try
        {
            // 容錯率姑且可以將它想像成解析度，分為 4 級：L(7%)，M(15%)，Q(25%)，H(30%)
            // 設定 QR code 容錯率為 H
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

            // 建立 QR code 的資料矩陣
            BitMatrix result = writer.encode(QRCodeContent, BarcodeFormat.QR_CODE, QRCodeWidth, QRCodeHeight, hints);
            // ZXing 還可以生成其他形式條碼，如：BarcodeFormat.CODE_39、BarcodeFormat.CODE_93、BarcodeFormat.CODE_128、BarcodeFormat.EAN_8、BarcodeFormat.EAN_13...

            //建立點陣圖
            Bitmap bitmap = Bitmap.createBitmap(QRCodeWidth, QRCodeHeight, Bitmap.Config.ARGB_8888);
            // 將 QR code 資料矩陣繪製到點陣圖上
            for (int y = 0; y<QRCodeHeight; y++)
            {
                for (int x = 0;x<QRCodeWidth; x++)
                {
                    bitmap.setPixel(x, y, result.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }

            ImageView imgView = (ImageView) findViewById(R.id.imageView);
            // 設定為 QR code 影像
            imgView.setImageBitmap(bitmap);
        }
        catch (WriterException e)
        {
            // TODO Auto-generated catch block
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
            HttpPost myConnection = new HttpPost("http://192.168.1.7/crode_people.php");
            HttpPost myConnection1 = new HttpPost("http://192.168.1.7/crode_people_1.php");

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


            try{
                JSONArray jArray = new JSONArray(str);
                JSONArray jArray1 = new JSONArray(str1);
                json = jArray.getJSONObject(0);
                json1 = jArray1.getJSONObject(0);



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
            JSONObject jsonObject,jsonObject1;
            try {
                jsonArray = new JSONArray(str);
                jsonArray1 = new JSONArray(str1);
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    textview5.setText(jsonObject.getString("id"));
                }
                for (int i = 0; i < jsonArray1.length(); i++) {
                    jsonObject1 = jsonArray1.getJSONObject(i);
                    textview7.setText(jsonObject1.getString("max(id)"));
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }



        }
    }



}
