package com.example.capstone_design;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import java.util.ArrayList;
import static com.example.capstone_design.LoginActivity.St_id;
import static com.example.capstone_design.SeatReservationActivity.Seat_Local;
import static com.example.capstone_design.TicketAdapter.Ticket_Index;

public class CreateQR extends AppCompatActivity {
    private ImageView Qrcode;
    public String local_id;
    public String local_ticket;
    public static String QR_Hash;                       // 최종 해시값
    public TextView time;
    public Button nfcMode;
    String TICKET_BTS = "BTS WORLD TOUR";
    String TICKET_KKH = "KimKyungHo Concert";
    String TICKET_MMMIA = "MMMIA!";
    String TICKET_SKJ = "Seo Kang Jun Fan Concert";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_q_r);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        time = (TextView)findViewById(R.id.time);
        nfcMode = findViewById(R.id.nfcmode);
        // 데이터 베이스 Instance 호출
        Cursor cursor = null;
        // table값 불러오가
        cursor = Ticket_Reservation_Activity.DBManager.
                query(null, null, null,
                        null, null, null);

        // TODO : NFC 인증기능 추가 중
        nfcMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateNFC.class);
                startActivity(intent);
            }
        });

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex("ID"));
                String ticket = cursor.getString(cursor.getColumnIndex("ticket"));
                // 예약자의 id와 좌석번호가 맞으면?
                if (Ticket_Index == 1) {
                    if (St_id.equals(id) && TICKET_BTS.equals(ticket)) {
                        local_id = St_id;
                        local_ticket = Seat_Local;
                        sha256 hash = new sha256();
                        QR_Hash = hash.Hash(local_id + local_ticket);
                        System.out.println(QR_Hash);
                    }
                } else if (Ticket_Index == 2) {
                    if (St_id.equals(id) && TICKET_KKH.equals(ticket)) {
                        local_id = St_id;
                        local_ticket = Seat_Local;
                        sha256 hash = new sha256();
                        QR_Hash = hash.Hash(local_id + local_ticket);
                    }
                } else if (Ticket_Index == 3) {
                    if (St_id.equals(id) && TICKET_MMMIA.equals(ticket)) {
                        local_id = St_id;
                        local_ticket = Seat_Local;
                        sha256 hash = new sha256();
                        QR_Hash = hash.Hash(local_id + local_ticket);
                    }
                } else if (Ticket_Index == 4) {
                    if (St_id.equals(id) && TICKET_SKJ.equals(ticket)) {
                        local_id = St_id;
                        local_ticket = Seat_Local;
                        sha256 hash = new sha256();
                        QR_Hash = hash.Hash(local_id + local_ticket);
                    }
                }

                Qrcode = (ImageView) findViewById(R.id.qrcode);
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(QR_Hash, BarcodeFormat.QR_CODE,
                            200, 200);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    Intent intent = new Intent(CreateQR.this, CreateNFC.class);
                    startActivity(intent);
                    //Qrcode.setImageBitmap(bitmap);
                } catch (Exception e) {
                }
                Handler handler = new Handler();
                handler.postDelayed(new Runnable(){
                    public void run(){
                        Intent intent = new Intent(CreateQR.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },30000);
            }
        }
    }
}