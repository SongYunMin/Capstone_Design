package com.example.capstone_design;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


// LoginActivity 에서 가져온 id 값을 사용함
import static com.example.capstone_design.LoginActivity.St_id;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button new_Account_button = (Button) findViewById(R.id.newAccount);
        final Button Login_button = (Button) findViewById(R.id.Login);
        Button Logout_button = (Button) findViewById(R.id.Logout);
        Button Ticketing_button = (Button) findViewById(R.id.Ticketing);
        Button MyTicket_button = (Button) findViewById(R.id.myTicket);

        // 로그인이 완료 되었을때 MainActivity 왼쪽 상단에 로그인된 아이디가 출력되는 식별자
        TextView LoginComplet = findViewById(R.id.LoginCompletion);

        // 로그인이 완료 되었을 떄 MainActivity 좌측 상단에 표시되는 TextView
        if (St_id != null) {
            LoginComplet.setText(St_id + "님 환영합니다");
            LoginComplet.setVisibility(View.VISIBLE);
            // 로그인 버튼은 숨기고 로그아웃 버튼 생성 되게함
            Login_button.setVisibility(View.GONE);
            Logout_button.setVisibility(View.VISIBLE);
        } else {
            Logout_button.setVisibility(View.GONE);
            Login_button.setVisibility(View.VISIBLE);
        }

        // 버튼 클릭시 회원가입 창으로 이동되는 코드 Intent를 이용함
        new_Account_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(St_id == null){
                Intent intent = new Intent(getApplicationContext(), NewAccount_Activity.class);
                startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(),"이미 로그인 되어있습니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 버튼 클릭시 로그인 창으로 이동되는 코드 Intent를 이용함 (로그인시 진입 못하게 수정)
        Login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (St_id == null) {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "이미 로그인 되어있습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 버튼 클릭시 티켓예약 창으로 이동되는 코드 intent를 이용함(로그인시 에만)
        Ticketing_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (St_id != null) {
                    Intent intent = new Intent(getApplicationContext(), Ticket_Reservation_Activity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "먼저 로그인을 해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 버튼 클릭시 티켓예약 창으로 이동되는 코드 intent를 이용함(로그인시 에만
        MyTicket_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (St_id != null) {
                    Intent intent = new Intent(getApplicationContext(), MyTicketActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "먼저 로그인을 해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}