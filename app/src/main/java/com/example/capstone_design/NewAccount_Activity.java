package com.example.capstone_design;

/*
 *
 *       회원가입 Activity
 *
 */

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewAccount_Activity extends AppCompatActivity {
    EditText ed_name, ed_id, ed_pw, ed_pwck, ed_phone;
    String st_name, st_id, st_pw, st_pwck, st_phone;
    public static String ReservationWhether;
    public static int status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newaccount);
        // XML 코드에서 입력한 값들을 불러옴
        ed_name = (EditText) findViewById(R.id.NAME);
        ed_id = (EditText) findViewById(R.id.ID);
        ed_pw = (EditText) findViewById(R.id.PW);
        ed_pwck = (EditText) findViewById(R.id.PWCK);
        ed_phone = (EditText) findViewById(R.id.PHONE);
    }

    public void bt_join(View view) {
        Button Join = (Button) findViewById(R.id.submit);

        // 받아온 Edit 값을 String으로 변환
        st_name = ed_name.getText().toString();
        st_id = ed_id.getText().toString();
        st_pw = ed_pw.getText().toString();
        st_pwck = ed_pwck.getText().toString();
        st_phone = ed_phone.getText().toString();
        if (st_pw.equals(st_pwck)) {
            //String 형으로 입력받은 코드들을 서버로 전송
            HttpConnectThread http = new HttpConnectThread(
                    "http://192.168.0.108/new_member.php",
                    "userid=" + st_id + "&username=" + st_name + "&userpassword=" + st_pw +
                            "&userphone=" + st_phone);
            http.start();
            String temp = http.GetResult();

            // 알림 창
            AlertDialog.Builder builder = new AlertDialog.Builder(NewAccount_Activity.this);
            builder.setTitle("지문인증요청")
                    .setMessage("안전한 티켓 거래를 위해 지문인증이 필요합니다. 지금 인증하시겠습니까?")
                    .setNegativeButton("다음에", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    // "인증" 버튼을 누르면 FingerPrint_Activity 로 이동
                    .setPositiveButton("인증", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            status = 1;     // 회원가입을 통한 지문인증인지 구분해야 함(NFC 때문)
                            Intent intent = new Intent(getApplicationContext(), FingerPrintActivity.class);
                            startActivity(intent);
                        }
                    });
            builder.setCancelable(false);
            AlertDialog dialog = builder.create();
            dialog.show();
            // "회원가입" 버튼을 눌렀을때 이벤트 처리
//            Join.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // Toast 메세지
//                }
//            });
        } else {
            Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다",
                    Toast.LENGTH_LONG).show();
        }

    }
}