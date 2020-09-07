package com.example.capstone_design;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PhoneNumberActivity extends AppCompatActivity {

    public Button plusEdit;
    public EditText test;

    // TODO : 여러명을 입력받고, 좌석도 그만큼 예약 되어야 함
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);

        plusEdit = new Button(this);
        plusEdit=findViewById(R.id.testbt);
        plusEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusEdit.setText("Test");
                setContentView(R.layout.activity_phone_number);
            }
        });



    }
}