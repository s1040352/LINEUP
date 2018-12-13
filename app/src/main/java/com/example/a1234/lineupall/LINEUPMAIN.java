package com.example.a1234.lineupall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LINEUPMAIN extends AppCompatActivity {
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lineupmain);
        Button button = (Button)findViewById(R.id.button);
        Button button2 = (Button)findViewById(R.id.button2);
        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub
                intent=new Intent(LINEUPMAIN.this,MainActivity.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub
                intent=new Intent(LINEUPMAIN.this,Login.class);
                startActivity(intent);
            }
        });
    }
}
