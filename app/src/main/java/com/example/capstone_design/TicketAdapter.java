package com.example.capstone_design;

/*
 *
 *    TODO ->  티켓 ListView 출력 위한 Adapter
 *
 */

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;

import static com.example.capstone_design.NewAccount_Activity.ReservationWhether;

import java.util.ArrayList;

public class TicketAdapter extends ArrayAdapter<Ticket_VO> {
    // 변수 선언부
    public static Context context;
    public static int resId;
    public static ArrayList<Ticket_VO> datas;
    public static Ticket_VO vo;
    public static int Ticket_Index;

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
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.
                    LAYOUT_INFLATER_SERVICE);
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
        Button buttonView = holder.buttonView;


        vo = datas.get(position);
        numView.setText(vo.cus_ticket);
        nameView.setText(vo.cus_name);
        dayView.setText("공연 일자 : " + vo.cus_day);
        timeView.setText("시간 : " + vo.cus_time);
        placeView.setText("장소 : " + vo.cus_place);

        // 티켓 연번에 따라 데이터 변경
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
        // TODO -> vo.cus_ticket 값이 모두 돌지 않아서 리스트를 끝까지 출력해내지 않으면 에러 발생
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vo.cus_ticket.equals("1")) {
                    Intent intent = new Intent(context, SeatReservationActivity.class);
                    context.startActivity(intent);
                    Ticket_Index = 1;
                    ReservationWhether = "1";       // 예약여부
                    Ticket_Reservation_Activity.clickHandler(v);
                } else if (vo.cus_ticket.equals("2")) {
                    Intent intent = new Intent(context, SeatReservationActivity.class);
                    context.startActivity(intent);
                    Ticket_Index = 2;
                    ReservationWhether = "2";       // 예약여부
                    Ticket_Reservation_Activity.clickHandler(v);
                } else if (vo.cus_ticket.equals("3")) {
                    Intent intent = new Intent(context, SeatReservationActivity.class);
                    context.startActivity(intent);
                    Ticket_Index = 3;
                    ReservationWhether = "3";       // 예약여부
                    Ticket_Reservation_Activity.clickHandler(v);
                } else if (vo.cus_ticket.equals("4")) {
                    Intent intent = new Intent(context, SeatReservationActivity.class);
                    context.startActivity(intent);
                    Ticket_Index = 4;
                    ReservationWhether = "4";       // 예약여부
                    Ticket_Reservation_Activity.clickHandler(v);
                }
            }
        });
        return convertView;
    }
}
