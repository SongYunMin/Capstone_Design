package com.example.capstone_design;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class CreateQR extends AppCompatActivity {
    private ImageView Qrcode;
    private String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_q_r);

        // 데이터 베이스 Instance 호출
        Ticket_Reservation_Activity.DBManager = TicketDatabaseManager.getInstance(this);

       // Ticket_Reservation_Activity.DBManager.query().getString("ticket",null,null,null,null,null);

        Qrcode=(ImageView)findViewById(R.id.qrcode);
        text = "SongYunminNiceGuy";

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try{
            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,200,200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            Qrcode.setImageBitmap(bitmap);
        }catch(Exception e){}
    }
}
