package com.example.a1234.lineupall;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Store12 extends AppCompatActivity {
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store12);
        Button btn_yes = (Button)findViewById(R.id.btn_yes);
        Button btn_no = (Button)findViewById(R.id.btn_no);
        btn_yes.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {


                Toast.makeText(v.getContext(), "排隊成功", Toast.LENGTH_SHORT).show();
                intent=new Intent(Store12.this,Main3Activity.class);
                startActivity(intent);
            }
        });
        btn_no.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {


                Toast.makeText(v.getContext(), "您取消了排隊", Toast.LENGTH_SHORT).show();
                intent=new Intent(Store12.this,Main3Activity.class);
                startActivity(intent);
            }
        });
    }
}
