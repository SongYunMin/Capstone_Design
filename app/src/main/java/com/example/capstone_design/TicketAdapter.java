package com.example.capstone_design;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.res.ResourcesCompat;

import static com.example.capstone_design.NewAccount_Activity.ReservationWhether;
import static com.example.capstone_design.TicketHolder.buttonView;


import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class TicketAdapter extends ArrayAdapter<Ticket_VO> {
    public static Context context;
    public static int resId;
    public static ArrayList<Ticket_VO> datas;
    public static Ticket_VO vo;

    public static Image Ticket_img;
    public static String Ticket_name;

    // 생성자
    public TicketAdapter(Context context, int resId, ArrayList<Ticket_VO> datas) {
        super(context, resId);
        this.context = context;
        this.resId = resId;
        this.datas = datas;
    }

    @Override
    // 내부적으로 자동 호출 됨
    public int getCount() {
        return datas.size();
    }

    @NonNull
    @Override
    // 내부적으로 자동 호출 됨
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resId, null);
            TicketHolder holder = new TicketHolder(convertView);
            convertView.setTag(holder);
        }
        TicketHolder holder = (TicketHolder) convertView.getTag();
        ImageView typeImageView = holder.typeImageView;
        TextView numView = holder.numView;
        TextView nameView = holder.nameView;
        TextView dayView = holder.dayView;
        TextView timeView = holder.timeView;
        TextView placeView = holder.placeView;
        final TextView quantityView = holder.quantityView;
        notifyDataSetChanged();
        Button buttonView = holder.buttonView;

        vo = datas.get(position);
        numView.setText(vo.cus_ticket);
        nameView.setText(vo.cus_name);
        dayView.setText("공연 일자 : " + vo.cus_day);
        timeView.setText("시간 : " + vo.cus_time);
        placeView.setText("장소 : " + vo.cus_place);
        // setText로 int를 표현하기 위해서 String 형식으로 변환 후 출력
        quantityView.setText("남은 티켓 : " + vo.cus_quantity);

        // 티켓 연번 1번 = BTS WORLD TOUR
        if (vo.cus_ticket.equals("1")) {
            typeImageView.setImageDrawable(ResourcesCompat.getDrawable
                    (context.getResources(), R.drawable.bts, null));
        } else if (vo.cus_ticket.equals("2")) {
            typeImageView.setImageDrawable(ResourcesCompat.getDrawable
                    (context.getResources(), R.drawable.kkh, null));
        } else if (vo.cus_ticket.equals("3")) {
            typeImageView.setImageDrawable(ResourcesCompat.getDrawable
                    (context.getResources(), R.drawable.mmmia, null));
        } else if (vo.cus_ticket.equals("4")) {
            typeImageView.setImageDrawable(ResourcesCompat.getDrawable
                    (context.getResources(), R.drawable.skj, null));
        }

        if (vo.cus_ticket.equals("1")) {
            buttonView.setOnClickListener(new View.OnClickListener() {
                // AlertDialog.Builder builder = new AlertDialog.Builder();
                @Override
                public void onClick(View v) {
                    String myJS;
                    int numTemp = 1;
                    Toast toast = Toast.makeText(context, "예약이 완료되었습니다", Toast.LENGTH_LONG);
                    toast.show();
                    ReservationWhether = "1";       // 예약여부
                   //vo.cus_quantity -= 1;

                    HttpConnectThread http = new HttpConnectThread(
                            "http://210.124.110.96/TicketMinus.php",
                            "quantity=" + vo.cus_quantity + "&ticketnum=" + numTemp);
                    http.start();
                    String temp = http.GetResult();
                    //Ticket_Reservation_Activity.getData("http://210.124.110.96/Ticket_Resend.php");
                }
            });
        } else if (vo.cus_ticket.equals("2")) {
            buttonView.setOnClickListener(new View.OnClickListener() {
                // AlertDialog.Builder builder = new AlertDialog.Builder();
                @Override
                public void onClick(View v) {
                    int numTemp = 2;
                    Toast toast = Toast.makeText(context, "예약이 완료되었습니다", Toast.LENGTH_LONG);
                    toast.show();
                    ReservationWhether = "2";       // 예약 여부
                   // vo.cus_quantity -= 1;
                    HttpConnectThread http = new HttpConnectThread(
                            "http://210.124.110.96/TicketMinus.php",
                            "quantity=" + vo.cus_quantity + "&ticketnum=" + numTemp);
                    http.start();
                    String temp = http.GetResult();
                }
            });
        } else if (vo.cus_ticket.equals("3")) {
            buttonView.setOnClickListener(new View.OnClickListener() {
                // AlertDialog.Builder builder = new AlertDialog.Builder();
                @Override
                public void onClick(View v) {
                    int numTemp = 3;
                    Toast toast = Toast.makeText(context, "예약이 완료되었습니다", Toast.LENGTH_LONG);
                    toast.show();
                    ReservationWhether = "3";
                    //vo.cus_quantity -= 1;

                    HttpConnectThread http = new HttpConnectThread(
                            "http://210.124.110.96/TicketMinus.php",
                            "quantity=" + vo.cus_quantity + "&ticketnum=" + numTemp);
                    http.start();
                    String temp = http.GetResult();
                }
            });
        } else if (vo.cus_ticket.equals("4")) {
            buttonView.setOnClickListener(new View.OnClickListener() {
                // AlertDialog.Builder builder = new AlertDialog.Builder();
                @Override
                public void onClick(View v) {
                    int numTemp = 4;
                    Toast toast = Toast.makeText(context, "예약이 완료되었습니다", Toast.LENGTH_LONG);
                    toast.show();
                    ReservationWhether = "4";
                    //vo.cus_quantity -= 1;

                    HttpConnectThread http = new HttpConnectThread(
                            "http://210.124.110.96/TicketMinus.php",
                            "quantity=" + vo.cus_quantity + "&ticketnum=" + numTemp);
                    http.start();
                    String temp = http.GetResult();
                }
            });
        }
        return convertView;
    }


}
