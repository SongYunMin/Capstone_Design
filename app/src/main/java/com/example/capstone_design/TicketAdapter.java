package com.example.capstone_design;

import android.content.Context;
import android.content.DialogInterface;
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

import java.util.ArrayList;

public class TicketAdapter extends ArrayAdapter<Ticket_VO> {
    Context context;
    int resId;
    ArrayList<Ticket_VO> datas;

    public TicketAdapter(Context context, int resId, ArrayList<Ticket_VO> datas){
        super(context,resId);
        this.context = context;
        this.resId=resId;
        this.datas=datas;
    }

    @Override
    public int getCount(){
        return datas.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resId,null);
            TicketHolder holder = new TicketHolder(convertView);
            convertView.setTag(holder);
        }
        TicketHolder holder = (TicketHolder)convertView.getTag();

        ImageView typeImageView = holder.typeImageView;
        TextView nameView = holder.nameView;
        TextView dayView = holder.dayView;
        TextView timeView = holder.timeView;
        TextView placeView = holder.placeView;
        final Button buttonView = holder.buttonView;

        final Ticket_VO vo = datas.get(position);

        nameView.setText(vo.cus_name);
        dayView.setText(vo.cus_day);
        timeView.setText(vo.cus_time);
        placeView.setText(vo.cus_place);

        // 티켓 연번 1번 = BTS WORLD TOUR
        if(vo.cus_ticket.equals("1")){
            typeImageView.setImageDrawable(ResourcesCompat.getDrawable
                    (context.getResources(),R.drawable.bts,null));
        }
        else if(vo.cus_ticket.equals("2")){
            typeImageView.setImageDrawable(ResourcesCompat.getDrawable
                    (context.getResources(),R.drawable.kkh,null));
        }
        else if(vo.cus_ticket.equals("3")){
            typeImageView.setImageDrawable(ResourcesCompat.getDrawable
                    (context.getResources(),R.drawable.mmmia,null));
        }
        else if(vo.cus_ticket.equals("4")){
            typeImageView.setImageDrawable(ResourcesCompat.getDrawable
                    (context.getResources(),R.drawable.skj,null));
        }


        buttonView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Toast toast=Toast.makeText(context,"예약이 완료되었습니다",Toast.LENGTH_LONG);
            }
        });

        return convertView;
    }


}
