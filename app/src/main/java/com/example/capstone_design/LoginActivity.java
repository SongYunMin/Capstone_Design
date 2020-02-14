package com.example.capstone_design;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/*
        2020.1.29.10:10 - 로그인이 되지만 Parse Error 발생 -> 미해결
 */

public class LoginActivity extends AppCompatActivity {
    EditText ed_id,ed_pw;
    // St_id 가 전역에 접근해야 함
    public static String St_id;
    public static String St_pw;
    public static String LoginCheck;
    public static Context Check;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // XML Code에서 ID/PW 를 불러오는 코드
        ed_id = (EditText) findViewById(R.id.username);
        ed_pw = (EditText) findViewById(R.id.password);
    }

    // 로그인 버튼을 누르면 로그인
    public void bt_Login(View v){
        Button Login = (Button) findViewById(R.id.submit);

        // ID, PW 를 String 변수에 저장
        St_pw = ed_pw.getText().toString();
        St_id = ed_id.getText().toString();

        // 통신 스레드 실행
        HttpConnectThread http = new HttpConnectThread("http://210.124.110.96/Android_Check.php",
                "memberID="+St_id+"&memberPw="+St_pw);
        http.start();
        String result = http.GetResult();

        if (result.equals("False\n")) {
            Toast.makeText(getApplicationContext(),"아이디 혹은 비밀번호가 일치하지 않습니다.",Toast.LENGTH_LONG).show();
        }
        else{
            LoginCheck = result;
            Check = this;

            Login.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View w){
                    Toast.makeText(getApplicationContext(),"로그인 되었습니다",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}