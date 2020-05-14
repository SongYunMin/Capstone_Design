package com.example.capstone_design;

/*
 *
 *       좌석 현황을 출력해줄 Activity
 *
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.capstone_design.LoginActivity.St_id;
import static com.example.capstone_design.TicketAdapter.Ticket_Index;

public class SeatReservationActivity extends AppCompatActivity {
    String Seat_JSON;
    public static String Seat_Local;
    String temp;
    JSONArray Reser_arr;
    View v;
    LinearLayout r;
    public Button[] SeatBT = null;
    ArrayList Seat_arr = new ArrayList();
    ArrayList Ticket_arr = new ArrayList();
    public static ArrayList Seat_arr_tem;
    int[] Bt_id = {R.id.a0, R.id.a1, R.id.a2, R.id.a3, R.id.a4, R.id.a5,
            R.id.a6, R.id.a7, R.id.a8};
    ArrayList Seat_str = new ArrayList();
    ArrayList Ticket_str = new ArrayList();
    String TICKET_BTS = "BTS WORLD TOUR";
    String TICKET_KKH = "KimKyungHo Concert";
    String TICKET_MMMIA = "MMMIA!";
    String TICKET_SKJ = "Seo Kang Jun Fan Concert";

    private static final String TAG_NUM = "num";
    private static final String TAG_ID = "id";
    private static final String TAG_TICKET = "ticket";
    private static final String TAG_SEAT = "seat";
    private static final String TAG_RESULT = "result";
    private static final String NFC_TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_reservation);
        r = (LinearLayout) findViewById(R.id.Seat_Layout);
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
        Seat_data("http://210.124.110.96/Seat_Value.php");
        // OnclickListener 설정
        for (int i = 0; i < Bt_id.length; i++) {
            this.SeatBT[i].setOnClickListener(BT_Listener);
        }
    }

    // Ticket_Reservation Table안에 있는 Data 불러와서 변수 대입
    public void Seat_data(String url) {
        Seat_arr_tem = new ArrayList();
        class GetSeatDataJSON extends AsyncTask<String, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... params) {
                String result = null;
                try {
                    HttpConnectThread http = new HttpConnectThread(
                            "http://210.124.110.96/Seat_Value.php",
                            "ticketindex=" + Ticket_Index);
                    http.start();
                    // JSONObject jsonObj = new JSONObject(myJSON);
                    // 딜레이 위해
                    for (int i = 0; i < 10000; i++) {
                        System.out.println("qwe");
                    }

                    temp = http.GetResult();
                    JSONObject jsonObj = new JSONObject(temp);
                    Reser_arr = jsonObj.getJSONArray(TAG_RESULT);

                    for (int i = 0; i < Reser_arr.length(); i++) {
                        // TAG 의 String을 String 변수에 대입한다.
                        JSONObject c = Reser_arr.getJSONObject(i);
                        String id = c.getString(TAG_ID);
                        String seat = c.getString(TAG_SEAT);
                        // JSON 인코딩 성공
                        Ticket_arr.add(id);
                        Seat_arr.add(seat);
                    }
                    Seat_arr_tem = Seat_arr;
                    for (int i = 0; i < Seat_arr_tem.size(); i++) {
                        System.out.println(Seat_arr_tem.get(i));
                    }

                    return temp;
                } catch (Exception e) {
                    return null;
                }
            }
            @Override
            protected void onPostExecute(String result) {
                Seat_JSON = result;
                System.out.println("나와 시발라마");
                ChangeBackground();
            }
        }
        GetSeatDataJSON Seat = new GetSeatDataJSON();
        Seat.execute(url);
    }

    public void ChangeBackground() {
        // Seat_arr이 '0' 인 오류 있음
        // 디버깅 시에는 정상 작동
        for (int i = 0; i < Seat_arr_tem.size(); i++) {
            for (int j = 0; j < Seat_str.size(); j++) {
                if (Seat_arr_tem.get(i).equals(Seat_str.get(j))) {
                    SeatBT[j].setBackgroundColor(Color.rgb(165, 0, 0));
                    SeatBT[j].setEnabled(false);
                    SeatBT[j].setTextColor(Color.rgb(255, 255, 255));
                    SeatBT[j].setText("예약불가");
                    r.postInvalidate();
                    System.out.println("나는 살아있다!!!");
                }
            }
        }
    }

    // 좌석 버튼들의 OnclickListener
    public View.OnClickListener BT_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for (int i = 0; i < Bt_id.length; i++) {
                if (v.getId() == SeatBT[i].getId()) {
                    if (Ticket_Index == 1) {                // 1번 티켓일때
                        // 티켓 이름
                        HttpConnectThread http = new HttpConnectThread("http://210.124.110.96/Input_Reservation.php",
                                "ticketindex=" + Ticket_Index + "&userid=" + St_id + "&seat=" + Seat_str.get(i) + "&ticketname=" + TICKET_BTS);
                        http.start();
                        String temp = http.GetResult();
                        Seat_Local = Seat_str.get(i).toString();        // 추후에 사용될(QR코드 값을 넣을 때) Seat_Local
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

}