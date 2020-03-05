package com.example.capstone_design;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TicketHolder {
    public ImageView typeImageView;
    public static TextView numView;
    public TextView nameView;
    public TextView dayView;
    public TextView timeView;
    public TextView placeView;
    public TextView quantityView;
    public static Button buttonView;

    public TicketHolder(View root){
        typeImageView = root.findViewById(R.id.ticket_photo);
        numView = root.findViewById(R.id.num);
        nameView = root.findViewById(R.id.name);
        dayView = root.findViewById(R.id.day);
        timeView = root.findViewById(R.id.time);
        placeView = root.findViewById(R.id.place);
        quantityView = root.findViewById(R.id.quantity);
        buttonView = root.findViewById(R.id.bt_reser);
    }
}
