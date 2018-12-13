package com.example.a1234.lineupall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class editpage extends AppCompatActivity {
    private Button btnCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_editpage);

    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnUpdate:
                Intent intent = new Intent(this,Main3Activity.class);
                startActivity(intent);
                break;
        }
    }
}
