package com.example.capstone_design;
/*
 *
 *   TODO    티켓 예약 기능 출력할 Activity - 티켓들 출력해줌
 *
 */
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import static com.example.capstone_design.LoginActivity.St_id;
import static com.example.capstone_design.NewAccount_Activity.ReservationWhether;

public class Ticket_Reservation_Activity extends AppCompatActivity
        implements AdapterView.OnClickListener {
    String myJSON;

    // DB에 접근할 TAG 생성
    private static final String TAG_RESULT = "result";
    private static final String TAG_NUM = "num";
    private static final String TAG_NAME = "name";
    private static final String TAG_TIME = "time";
    private static final String TAG_DAY = "day";
    private static final String TAG_PLACE = "place";

    // Local (SQLite)에 넣을 ID값과 TIcket값 변수
    public static String Local_ID;
    public static String Local_Ticket;
    JSONArray ticket = null;
    public static Context context_Reser;
    public static ArrayList<Ticket_VO> datas = new ArrayList<>();
    public static TicketDatabaseManager DBManager;
    ListView list;

    // onCreate Method 실제 실행하는 함수
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticketing);
        context_Reser = this;

        // 데이터 베이스 Instance 호출
        DBManager = TicketDatabaseManager.getInstance(this);

        list = (ListView) findViewById(R.id.ticket_list);
        // getdata Method 접근, 아래 정의 되어 있음
        getData("http://210.124.110.96/Ticket_Value.php");
    }

    // Data 얻어오는 Method
    public void getData(String url) {
        class GetDataJSON extends AsyncTask<String, Void, String> {
            @Override
            // AsyncTask Background Method
            protected String doInBackground(String... params) {
                // uri
                String uri = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    bufferedReader = new BufferedReader(new InputStreamReader
                            (con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
            @Override
            // Server 전송 Method
            protected void onPostExecute(String result) {
                myJSON = result;
                // showList() 실행
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }
    // List 의 들어갈 값을 가져오는 Method
    protected void showList() {
        int status = 0;
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            ticket = jsonObj.getJSONArray(TAG_RESULT);
            if(status == 0) {
                for (int i = 0; i < ticket.length(); i++) {
                    // TAG 의 String을 String 변수에 대입한다.
                    JSONObject c = ticket.getJSONObject(i);
                    String num = c.getString(TAG_NUM);
                    String name = c.getString(TAG_NAME);
                    String time = c.getString(TAG_TIME);
                    String day = c.getString(TAG_DAY);
                    String place = c.getString(TAG_PLACE);

                    // ???? 뭔지 정확히 파악해라
                    Ticket_VO vo = new Ticket_VO();
                    vo.cus_ticket = num;
                    vo.cus_name = name;
                    vo.cus_time = time;
                    vo.cus_day = day;
                    vo.cus_place = place;

                    datas.add(vo);
                    status++;
                }
            }
            // list_item
            TicketAdapter adapter = new TicketAdapter(this, R.layout.list_item, datas);
            list.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // LocalDB(SQLite)에 들어갈 값
    public static void clickHandler(View view) {
        String hash;
        if (ReservationWhether.equals("1")) {
            ContentValues addTicketValue = new ContentValues();

            addTicketValue.put("ID", St_id);
            Local_Ticket = "BTS WORLD TOUR";
            addTicketValue.put("ticket", Local_Ticket);

            // 해시화 진행함
            hash = St_id+Local_Ticket;
            sha256 sh;
            sh = new sha256();
            hash = sh.Hash(hash);            // 해시값 정상적으로 들어 감
            System.out.println(hash);       // 해시값 Test Code
            Ticket_Reservation_Activity.DBManager.insert(addTicketValue);
            Local_ID = St_id;

        } else if (ReservationWhether.equals("2")) {
            ContentValues addTicketValue = new ContentValues();

            addTicketValue.put("ID", St_id);
            Local_Ticket = "KimKyungHo Concert";
            addTicketValue.put("ticket", Local_Ticket);
            // 해시화 진행함
            hash = St_id+Local_Ticket;
            sha256 sh;
            sh = new sha256();
            hash = sh.Hash(hash);            // 해시값 정상적으로 들어 감
            System.out.println(hash);       // 해시값 Test Code

            Ticket_Reservation_Activity.DBManager.insert(addTicketValue);
            Local_ID = St_id;

        } else if (ReservationWhether.equals("3")) {
            ContentValues addTicketValue = new ContentValues();

            addTicketValue.put("ID", St_id);
            Local_Ticket = "MAMMAMIA!";
            addTicketValue.put("ticket", Local_Ticket);

            // 해시화 진행함
            hash = St_id+Local_Ticket;
            sha256 sh;
            sh = new sha256();
            hash = sh.Hash(hash);            // 해시값 정상적으로 들어 감
            System.out.println(hash);       // 해시값 Test Code

            Ticket_Reservation_Activity.DBManager.insert(addTicketValue);
            Local_ID = St_id;
        } else if (ReservationWhether.equals("4")) {
            ContentValues addTicketValue = new ContentValues();

            addTicketValue.put("ID", St_id);
            Local_Ticket = "2020 Seo Kang Jun Fan Concert";
            addTicketValue.put("ticket", Local_Ticket);

            // 해시화 진행함
            hash = St_id+Local_Ticket;
            sha256 sh;
            sh = new sha256();
            hash = sh.Hash(hash);            // 해시값 정상적으로 들어 감
            System.out.println(hash);       // 해시값 Test Code

            Ticket_Reservation_Activity.DBManager.insert(addTicketValue);
            Local_ID = St_id;
        }
    }

    public void onClick(View v) {
        View list = (View) v.getParent();
    }
}