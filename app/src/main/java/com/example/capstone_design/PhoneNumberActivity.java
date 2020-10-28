package com.example.capstone_design;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class PhoneNumberActivity extends AppCompatActivity {
    public Button plusbt;
    public Button submit;
    public EditText ticketnum;
    public LinearLayout li;
    public static String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);

        ticketnum = findViewById(R.id.edit);
        plusbt = findViewById(R.id.plusbt);
        li = findViewById(R.id.li);
        submit = findViewById(R.id.submit);

        plusbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(ticketnum.getText().toString());

                for (int i = 0; i < num; i++) {
                    EditText et = new EditText((getApplicationContext()));
                    LinearLayout.LayoutParams p = new LinearLayout.LayoutParams
                            (ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT);
                    et.setLayoutParams(p);
                    et.setHint("전화번호를 입력하세요");
                    et.setId(num);
                    et.setHintTextColor(Color.parseColor("#FFFFFF"));
                    et.setTextColor(Color.parseColor("#FFFFFF"));
                    li.addView(et);
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PhoneNumberActivity.this,
                        "예약이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }

        });


    }
}