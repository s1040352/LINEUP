package com.example.a1234.lineupall;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText loginaccount;
    private EditText loginpassword;
    private Button btnLogin;
    private Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        loginaccount = (EditText) findViewById(R.id.loginaccount);
        loginpassword = (EditText) findViewById(R.id.loginpassword);
        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                startActivity(intent);

//                check();
            }
        });
        btnSignup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
    }

//    private void check() {
//
//        int accountLength = loginaccount.getText().toString().length();
//        if (accountLength < 2 || accountLength > 10) {
//            Toast.makeText(this, "帳號需介於2~10個字", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        int codeLength = loginpassword.getText().toString().length();
//        if (codeLength < 2 || codeLength > 10) {
//            Toast.makeText(this, "密碼需介於2~10個字", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        else
//            cleanEditText();
////跳不出
//    }

    private void cleanEditText() {
        loginaccount.setText("");
        loginpassword.setText("");

    }
//    public void onClick(View view) {
//        switch(view.getId()){
//            case R.id.btnLogin:
//                        int accountLength = loginaccount.getText().toString().length();
//                        if (accountLength < 2 || accountLength > 10) {
//                            Toast.makeText(this, "帳號需介於2~10個字", Toast.LENGTH_SHORT).show();
//                            return;
//                            }
//
//                        int codeLength = loginpassword.getText().toString().length();
//                        if (codeLength < 2 || codeLength > 10) {
//                            Toast.makeText(this, "密碼需介於2~10個字", Toast.LENGTH_SHORT).show();
//                            return;
//                    }
//
//                Intent intent = new Intent();
//                intent.setClass(MainActivity.this, showdetail.class);
//                startActivity(intent);
//            case R.id.btnSignup:
//                Intent intent1 = new Intent();
//                intent1.setClass(MainActivity.this, signup.class);
//                startActivity(intent1);
//                default:
//                    break;
//        }
//
//    }
}