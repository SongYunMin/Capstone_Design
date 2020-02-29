package com.example.capstone_design;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.example.capstone_design.NewAccount_Activity.ReservationWhether;
public class MyTicketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ticket);

        // 티켓이 예메가 되어있는 상태
        final ImageView TicketImage = (ImageView)findViewById(R.id.my_ticket_img);
        final TextView NameText = (TextView)findViewById(R.id.my_ticket_name);
        final TextView DayText = (TextView)findViewById(R.id.my_ticket_day);
        final TextView TimeText = (TextView)findViewById(R.id.my_ticket_time);
        final TextView PlaceText = (TextView)findViewById(R.id.my_ticket_place);
        final Button TicketButton = (Button)findViewById(R.id.my_ticket_button);
        final TextView NoTicketText = (TextView)findViewById(R.id.noticket);


         // MyTIcket Activity (XML)
          if(ReservationWhether.equals("1")){
            TicketImage.setImageResource(R.drawable.bts);
            NameText.setText("BTS WORLD TOUR");
            DayText.setText("2019.10.26");
            TimeText.setText("18:00");
            PlaceText.setText("OLIMPIC STADIUM");
        }
        else if(ReservationWhether.equals("2")){
            TicketImage.setImageResource(R.drawable.kkh);
            NameText.setText("KimKyungHo Concert");
            DayText.setText("2019.10.26");
            TimeText.setText("19:30");
            PlaceText.setText("Chunchepn Art Center");
        }
        else if(ReservationWhether.equals("3")){
            TicketImage.setImageResource(R.drawable.mmmia);
            NameText.setText("MAMMAMIA!");
            DayText.setText("2019.10.29");
            TimeText.setText("18:00");
            PlaceText.setText("Seoul Art Center");
        }
        else if(ReservationWhether.equals("4")){
            TicketImage.setImageResource(R.drawable.skj);
            NameText.setText("Seo Kang Jun Fan Concert");
            DayText.setText("2020.1.4");
            TimeText.setText("14:00");
            PlaceText.setText("Suwon WorldCup Stadium");
        }
        else{
            NoTicketText.setText("예약된 티켓이 없습니다");
            NoTicketText.setVisibility(View.VISIBLE);
        }

    }
}
