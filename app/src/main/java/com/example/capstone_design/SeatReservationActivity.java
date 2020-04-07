package com.example.capstone_design;

import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;

import android.graphics.Color;
import android.net.sip.SipSession;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static com.example.capstone_design.LoginActivity.St_id; // 현재 Login 된 id 값
import static com.example.capstone_design.Ticket_Reservation_Activity.Reservation;  // 예약된 티켓 값

public class SeatReservationActivity extends AppCompatActivity {
    String myJSON;
    JSONArray Reser_arr;
    public Button[] SeatBT = null;
    ArrayList Seat_arr = new ArrayList();
    int[] Bt_id = {R.id.a1, R.id.a2, R.id.a3, R.id.a4, R.id.a5,
            R.id.a6, R.id.a7, R.id.a8, R.id.a9};

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
        // 서버 접근
        getData("http://210.124.110.96/Seat_Value.php");

        // SeatBt Id값 얻어오기
        for (int i = 0; i < Bt_id.length; i++) {
            this.SeatBT[i] = (Button) findViewById(Bt_id[i]);
            // 예약되있는 좌석일 시 버튼 백그라운드 변경
//            if (Seat_arr.get(i).equals("a"+ i+1)) {
//                SeatBT[i].setBackgroundColor(Color.rgb(25, 26, 28));
//            }
        }

        // OnclickListener 설정
        for (int i = 0; i < Bt_id.length; i++) {
            this.SeatBT[i].setOnClickListener(BT_Listener);
        }
    }

    // 좌석 버튼들의 OnclickListener
    private View.OnClickListener BT_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for (int i = 0; i < Bt_id.length; i++) {
                if (v.getId() == SeatBT[i].getId()) {
                    System.out.println(i);
                }
            }
        }
    };

    // Data 얻어오는 Method
    public void getData(String url){
        class GetDataJSON extends AsyncTask<String,Void,String> {
            @Override
            // AsyncTask Background Method
            protected String doInBackground(String... params){
                // uri
                String uri = params[0];
                BufferedReader bufferedReader = null;
                try{
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection)url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader
                            (con.getInputStream()));
                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }
                    return sb.toString().trim();
                } catch(Exception e){
                    return null;
                }
            }
            @Override
            // Server 전송 Method
            protected void onPostExecute(String result){
                myJSON = result;
                // Ticket_data() 실행
                Ticket_data();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }

    // Ticket_Reservation Table안에 있는 Data 불러와서 변수 대입
    protected void Ticket_data(){
        try{
            JSONObject jsonObj = new JSONObject(myJSON);
            Reser_arr = jsonObj.getJSONArray(TAG_RESULT);

            for(int i=0;i<Reser_arr.length();i++)
            {
                // TAG 의 String을 String 변수에 대입한다.
                JSONObject c = Reser_arr.getJSONObject(i);
                String num = c.getString(TAG_NUM);
                String id = c.getString(TAG_ID);
                String ticket = c.getString(TAG_TICKET);
                String seat = c.getString(TAG_SEAT);

                Seat_arr.add(seat);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public class SeatVO {
        public String Seat;
    }


}