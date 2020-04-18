package com.example.capstone_design;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.capstone_design.Ticket_VO;

import java.util.ArrayList;

public class SeatAdapter extends ArrayAdapter<SeatVO> {
    Context context;
    int resId;
    ArrayList<SeatVO> datas;

    public SeatAdapter(Context context, int resId, ArrayList<SeatVO> datas) {
        super(context, resId);
        this.context = context;
        this.resId = resId;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

}
