package com.example.a1234.lineupall;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;
//import static android.provider.BaseColumns._ID;
//import static com.example.sitting.myapplicationmixx.DbConstants.TABLE_NAME;
//import static com.example.sitting.myapplicationmixx.DbConstants.NAME;
//import static com.example.sitting.myapplicationmixx.DbConstants.GENDER;
//import static com.example.sitting.myapplicationmixx.DbConstants.ACCOUNT;
//import static com.example.sitting.myapplicationmixx.DbConstants.CODE;
//import static com.example.sitting.myapplicationmixx.DbConstants.TEL;
// import static com.example.sitting.myapplicationmixx.DbConstants.EMAIL;


public class Main2Activity extends AppCompatActivity implements OnClickListener {


    //public DBHelper dbHelper;
    public TextView result;
    public TextView reGender;
    public ListView listData;
    public EditText editName;
    public EditText editCode;
    private EditText editCheckcode;
    public EditText editTel;
    public EditText editEmail;
    public EditText editAccount;
    private Button btnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findViews();
        openDatabase();   //開啟資料庫

//       showInList();    //show listview


        radioGroup = (RadioGroup) this.findViewById(R.id.radio_group);
        textView = (TextView) this.findViewById(R.id.select_result);
        textView.setText(getSelectedInfo());

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                textView.setText(getSelectedInfo(checkedId));

            }
        });
    }


    private RadioGroup radioGroup = null;
    private TextView textView = null;

    private String getSelectedInfo() {
        int checkedId = radioGroup.getCheckedRadioButtonId();
        return getSelectedInfo(checkedId);
    }

    private String getSelectedInfo(int checkedId) {
        String selected;

        switch (checkedId) {
            case R.id.radio_male:
                selected = "先生";
                break;
            case R.id.radio_female:
                selected = "小姐";
                break;
            default:
                selected = "先生";
                break;

        }

        return selected;
    }


    private void findViews() {

        editName = (EditText) findViewById(R.id.editName);
        reGender = (TextView) this.findViewById(R.id.select_result);
        editAccount = (EditText) findViewById(R.id.editAccount);
        editCode = (EditText) findViewById(R.id.editCode);
        editCheckcode = (EditText) findViewById(R.id.editCheckcode);
        editTel = (EditText) findViewById(R.id.editTel);
        editEmail = (EditText) findViewById(R.id.editEmail);
//        editId = (EditText) findViewById(R.id.editID);
        btnAdd = (Button) findViewById(R.id.btnAdd);
//        result=(TextView)findViewById(R.id.txtResult);
//        listData=(ListView)findViewById(R.id.listData);
        btnAdd.setOnClickListener(this);
    }

    protected void onDestroy() {
        super.onDestroy();
        closeDatabase();     //關閉資料庫
    }

    private void openDatabase() {
 //       dbHelper = new DBHelper(this);   //取得DBHelper物件

    }

    private void closeDatabase() {
  //      dbHelper.close();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
//                int nameLength = editName.getText().toString().length();
//                if (nameLength < 2 || nameLength > 5) {
//                    Toast.makeText(this, "用戶名需介於2~5個字", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                int accountLength = editAccount.getText().toString().length();
//                if (accountLength < 2 || accountLength > 10) {
//                    Toast.makeText(this, "帳號需介於2~10個字", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                int codeLength = editCode.getText().toString().length();
//                if (codeLength < 2 || codeLength > 10) {
//                    Toast.makeText(this, "密碼需介於2~10個字", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//
//                String codeA = (editCode.getText().toString());
//                String codeB = (editCheckcode.getText().toString());
//                if (codeA.equals(codeB)) {
//                    editCheckcode.setText("");
//                } else {
//                    Toast.makeText(this, "與密碼不符", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                int telLength = editTel.getText().toString().length();
//                if (telLength != 10) {
//                    Toast.makeText(this, "手機格式錯誤", Toast.LENGTH_SHORT).show();

//                    return;
//                }
//
//                String email = (editEmail.getText().toString());
//                String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//                if (!email.matches(emailRegex)) {
//                    Toast.makeText(this, "email不符合格式", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                add();
//                break;
//限制條件
            default:
                break;
        }
        Intent intent = new Intent();
        intent.setClass(Main2Activity.this  , Main3Activity.class);
        startActivity(intent);
        Main2Activity.this.finish();//關掉第一頁
//        show();
//        showInList();
    }

    private void add() {
 //       SQLiteDatabase db = dbHelper.getWritableDatabase();  //透過dbHelper取得讀取資料庫的SQLiteDatabase物件，可用在新增、修改與刪除
   //     ContentValues values = new ContentValues();  //建立 ContentValues 物件並呼叫 put(key,value) 儲存欲新增的資料，key 為欄位名稱  value 為對應值。
    //    values.put(NAME, editName.getText().toString());
     //   values.put(GENDER, reGender.getText().toString());
     //   values.put(ACCOUNT, editAccount.getText().toString());
     //   values.put(CODE, editCode.getText().toString());
       // values.put(TEL, editTel.getText().toString());
     //   values.put(EMAIL, editEmail.getText().toString());
     //   db.insert(TABLE_NAME, null, values);
        cleanEditText();
    }


    private void cleanEditText() {
        editName.setText("");
        reGender.setText("");
        editAccount.setText("");
        editCode.setText("");
        editTel.setText("");
        editEmail.setText("");
    }


}