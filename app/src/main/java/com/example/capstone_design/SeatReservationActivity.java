package com.example.capstone_design;

import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static com.example.capstone_design.LoginActivity.St_id;
import static com.example.capstone_design.TicketAdapter.Ticket_Index;

public class SeatReservationActivity extends AppCompatActivity {
    String myJSON;
    String temp;
    JSONArray Reser_arr;
    public Button[] SeatBT = null;
    ArrayList Seat_arr = new ArrayList();
    ArrayList Ticket_arr = new ArrayList();
    int[] Bt_id = {R.id.a0, R.id.a1, R.id.a2, R.id.a3, R.id.a4, R.id.a5,
            R.id.a6, R.id.a7, R.id.a8};
    ArrayList Seat_str = new ArrayList();
    ArrayList Ticket_str = new ArrayList();

    private static final String TAG_NUM = "num";
    private static final String TAG_ID = "id";
    private static final String TAG_TICKET = "ticket";
    private static final String TAG_SEAT = "seat";
    private static final String TAG_RESULT = "result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_reservation);
        

        SeatBT = new Button[9];
        Ticket_str.add("BTS WORLD TOUR");
        Ticket_str.add("KimKyungHo Concert");
        Ticket_str.add("MMMIA!");
        Ticket_str.add("Seo Kang Jun Fan Concert");
        // Local ID 비교
        for (int i = 0; i < Bt_id.length; i++) {
            this.SeatBT[i] = (Button) findViewById(Bt_id[i]);
            Seat_str.add("a" + i);
        }

        try {
            Seat_data();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // OnclickListener 설정
        for (int i = 0; i < Bt_id.length; i++) {
            this.SeatBT[i].setOnClickListener(BT_Listener);

        }

    }

    // 좌석 버튼들의 OnclickListener
    public View.OnClickListener BT_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for (int i = 0; i < Bt_id.length; i++) {
                if (v.getId() == SeatBT[i].getId()) {
                    if (Ticket_Index == 1) {
                        // 티켓 이름
                        HttpConnectThread http = new HttpConnectThread("http://210.124.110.96/Input_Reservation.php",
                                "ticketindex=" + Ticket_Index + "&userid=" + St_id + "&seat=" + Seat_str.get(i));
                        http.start();
                        String temp = http.GetResult();
                        Toast.makeText(getApplicationContext(), "예약이 완료 되었습니다", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else if (Ticket_Index == 2) {
                        HttpConnectThread http = new HttpConnectThread("http://210.124.110.96/Input_Reservation.php",
                                "ticketindex=" + Ticket_Index + "&userid=" + St_id + "&seat=" + Seat_str.get(i));
                        http.start();
                        String temp = http.GetResult();
                        Toast.makeText(getApplicationContext(), "예약이 완료 되었습니다", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else if (Ticket_Index == 3) {
                        HttpConnectThread http = new HttpConnectThread("http://210.124.110.96/Input_Reservation.php",
                                "ticketindex=" + Ticket_Index + "&userid=" + St_id + "&seat=" + Seat_str.get(i));
                        http.start();
                        String temp = http.GetResult();
                        Toast.makeText(getApplicationContext(), "예약이 완료 되었습니다", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else if (Ticket_Index == 4) {
                        HttpConnectThread http = new HttpConnectThread("http://210.124.110.96/Input_Reservation.php",
                                "ticketindex=" + Ticket_Index + "&userid=" + St_id + "&seat=" + Seat_str.get(i));
                        http.start();
                        String temp = http.GetResult();
                        Toast.makeText(getApplicationContext(), "예약이 완료 되었습니다", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        }
    };

    // Ticket_Reservation Table안에 있는 Data 불러와서 변수 대입
    public void Seat_data() {
        try {
            // 어떤 티켓인지 식별자 전달
                HttpConnectThread http = new HttpConnectThread(
                        "http://210.124.110.96/Seat_Value.php", "ticketindex=" + Ticket_Index);
                http.start();
                temp = http.GetResult();
                // JSONObject jsonObj = new JSONObject(myJSON);
                JSONObject jsonObj = new JSONObject(temp);
                Reser_arr = jsonObj.getJSONArray(TAG_RESULT);

                for (int i = 0; i < Reser_arr.length(); i++) {
                    // TAG 의 String을 String 변수에 대입한다.
                    JSONObject c = Reser_arr.getJSONObject(i);
                    String id = c.getString(TAG_ID);
                    //String ticket = c.getString(TAG_TICKET);
                    String seat = c.getString(TAG_SEAT);
                // JSON 인코딩 성공
                Ticket_arr.add(id);
                Seat_arr.add(seat);
            }
            // Bt_id 는 int형 !!
            for (int i = 0; i < Seat_arr.size(); i++) {
                for (int j = 0; j < Seat_str.size(); j++) {
                    if (Seat_arr.get(i).equals(Seat_str.get(j))) {
                        SeatBT[i].setBackgroundColor(Color.rgb(204, 12, 13));
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

//    // Data 얻어오는 Method
//    public void getData(String url) {
//        class GetDataJSON extends AsyncTask<String, Void, String> {
//            @Override
//            // AsyncTask Background Method
//            protected String doInBackground(String... params) {
//                // uri
//                String uri = params[0];
//                BufferedReader bufferedReader = null;
//                try {
//                    URL url = new URL(uri);
//                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
//                    StringBuilder sb = new StringBuilder();
//
//                    bufferedReader = new BufferedReader(new InputStreamReader
//                            (con.getInputStream()));
//                    String json;
//                    while ((json = bufferedReader.readLine()) != null) {
//                        sb.append(json + "\n");
//                    }
//                    return sb.toString().trim();
//                } catch (Exception e) {
//                    return null;
//                }
//            }
//
//            @Override
//            // Server 전송 Method
//            protected void onPostExecute(String result) {
//                myJSON = result;
//                // Seat_data() 실행
//                Seat_data();
//            }
//        }
//
//        GetDataJSON g = new GetDataJSON();
//        g.execute(url);
//    }