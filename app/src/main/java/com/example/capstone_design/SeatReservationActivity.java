package com.example.capstone_design;

import androidx.appcompat.app.AppCompatActivity;

import android.net.sip.SipSession;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SeatReservationActivity extends AppCompatActivity {
    public Button[] SeatBT = null;
    int[] Bt_id = { R.id.a1,R.id.a2,R.id.a3,R.id.a4,R.id.a5,
            R.id.a6,R.id.a7, R.id.a8,R.id.a9};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_reservation);

        SeatBT = new Button[9];


        // SeatBt Id값 얻어오기
        for(int i=0;i<Bt_id.length;i++){
            this.SeatBT[i] = (Button) findViewById(Bt_id[i]);
        }

        // OnclickListener 설정
        for(int i = 0 ;i<Bt_id.length;i++){
            this.SeatBT[i].setOnClickListener(BT_Listener);
        }

    }
    private View.OnClickListener BT_Listener = new View.OnClickListener()
    {

        @Override
        public void onClick(View v) {
            for (int i = 0; i < Bt_id.length; i++) {
                if(v.getId() == SeatBT[i].getId()){

                }
            }
        }
    };


}