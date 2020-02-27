package com.example.capstone_design;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

        if(ReservationWhether.equals("1"))
        {
           // NameText.setText( );
        }


    }
}
