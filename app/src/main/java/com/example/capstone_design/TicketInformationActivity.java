package com.example.capstone_design;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.capstone_design.NewAccount_Activity.ReservationWhether;
import static com.example.capstone_design.NewAccount_Activity.status;

public class TicketInformationActivity extends AppCompatActivity {
    String temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_information);

        // layout 파일속 요소의 ID를 가져옴
        final ImageView TicketImage = (ImageView) findViewById(R.id.ticket_img);
        final TextView TicketName = (TextView) findViewById(R.id.ticket_name);
        final TextView TicketDay = (TextView) findViewById(R.id.ticket_day);
        final TextView TicketTime = (TextView) findViewById(R.id.ticket_time);
        final TextView TicketPlace = (TextView) findViewById(R.id.ticket_place);
        final EditText TicketEdit = (EditText) findViewById(R.id.EditTicket);
        final Button TicketButton = (Button) findViewById(R.id.information_submit);

        // 현재 예약의 상태(ReservationWhether) 에 따라서 이미지 및 텍스트 출력 변경
        if (ReservationWhether.equals("1")) {
            TicketImage.setImageResource(R.drawable.bts);
            TicketName.setText("BTS WORLD TOUR");
            TicketDay.setText("2019.10.26");
            TicketTime.setText("18:00");
            TicketPlace.setText("OLIMPIC STADIUM");
        } else if (ReservationWhether.equals("2")) {
            TicketImage.setImageResource(R.drawable.kkh);
            TicketName.setText("KimKyungHo Concert");
            TicketDay.setText("2019.10.26");
            TicketTime.setText("19:30");
            TicketPlace.setText("Chuncheon Art Center");
        } else if (ReservationWhether.equals("3")) {
            TicketImage.setImageResource(R.drawable.mmmia);
            TicketName.setText("MAMMAMIA!");
            TicketDay.setText("2019.10.29");
            TicketTime.setText("18:00");
            TicketPlace.setText("Seoul Art Center");
        } else if (ReservationWhether.equals("4")) {
            TicketImage.setImageResource(R.drawable.skj);
            TicketName.setText("SeoKangJun Fan Concert");
            TicketDay.setText("2020.1.4");
            TicketTime.setText("14:00");
            TicketPlace.setText("Suwon WorldCup Stadium");
        }

        TicketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp = TicketEdit.getText().toString();
                // 예약 매수가 1장이라면?
                if (temp.equals("1")) {
                    // 예약 완료
                    Toast.makeText(TicketInformationActivity.this,
                            "예약이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                // 예약 매수가 여러장이라면?
                else {
                    // 예약자 정보 입력 Activity로 이동
                    Toast.makeText(TicketInformationActivity.this,
                            "예약자 정보를 입력해주세요", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), PhoneNumberActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}
