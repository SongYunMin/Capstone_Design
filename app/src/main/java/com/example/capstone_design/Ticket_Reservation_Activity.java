//package com.example.capstone_design;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.database.Cursor;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.ListAdapter;
//import android.widget.ListView;
//import android.widget.SimpleAdapter;
//import android.widget.Toast;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class Ticket_Reservation_Activity extends AppCompatActivity {
//    String myJSON;
//
//    private static final String TAG_RESULT = "result";
//    private static final String TAG_NUM = "num";
//    private static final String TAG_NAME = "name";
//    private static final String TAG_TIME = "time";
//    private static final String TAG_DAY = "day";
//    private static final String TAG_PLACE = "place";
//    private static final String TAG_PHOTO = "photo";
//
//    JSONArray ticket = null;
//    // ListView 출력 사진
//    // "내티켓" 메뉴 출력 사진
//    // 티켓 값이 들어있음
//    public static ArrayList<HashMap<String,String>>TicketList;
//    ListView list = findViewById(R.id.list_item);
//
//    // onCreate Method 실제 실행하는 함수
//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_ticketing);
//
//        list = (ListView) findViewById(R.id.ticket_list);
//        TicketList = new ArrayList<HashMap<String, String>>();
//
//        // getdata Method 접근, 아래 정의 되어 있음
//        getData("http://210.124.110.96/Ticket_Value.php");
//    }
//
//    // List 의 들어갈 값을 가져오는 Method
//    protected void showList(){
//        ArrayList<Ticket_VO> datas = new ArrayList<>();
//        try{
//            JSONObject jsonObj = new JSONObject(myJSON);
//            ticket = jsonObj.getJSONArray(TAG_RESULT);
//
//            for(int i=0;i<ticket.length();i++){
//                JSONObject c = ticket.getJSONObject(i);
//                String num = c.getString(TAG_NUM);
//                String name = c.getString(TAG_NAME);
//                String time = c.getString(TAG_TIME);
//                String day = c.getString(TAG_DAY);
//                String place = c.getString(TAG_PLACE);
//                String photo = c.getString(TAG_PHOTO);
//
//                // AsyncTask Generic Type
//                HashMap<String, String>ticket = new HashMap<String, String>();
//
//                ticket.put(TAG_NUM,num);
//                ticket.put(TAG_NAME,name);
//                ticket.put(TAG_TIME,time);
//                ticket.put(TAG_DAY,day);
//                ticket.put(TAG_PLACE,place);
//                ticket.put(TAG_PHOTO,photo);
//
//                // '내 티켓' 기능은 이 ArrayList를 활용하여 구현 가능할 것으로 보여 짐.
//                // index 는 0부터 시작
//                TicketList.add(ticket);
//            }
//
////            // SimpleAdapter 로는 Img 파일 동시 삽입 불가! CumtomAdapter 사용해야 함
//////            ListAdapter adapter = new SimpleAdapter(
//////                    Ticket_Reservation_Activity.this,TicketList,R.layout.list_item,
//////                    new String[]{TAG_NUM,TAG_NAME,TAG_TIME,TAG_DAY,TAG_PLACE,TAG_PHOTO},
//////                    new int[]{R.id.num,R.id.name,R.id.time,R.id.day,R.id.place}
//////            );
//////            ticket_index = R.id.num;
//////            list.setAdapter(adapter);
//        TicketAdapter adapter = new TicketAdapter(this,R.layout.list_item,datas);
//        list.setAdapter(adapter);
//
//        }catch (JSONException e){
//            e.printStackTrace();
//        }
//    }
//
//
//
//    // 예약 버튼 눌렀을 시
//    public void bt_reservation(View v)
//    {
//        Button Reservation = (Button) findViewById(R.id.bt_reser);
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(Ticket_Reservation_Activity.this);
//        builder.setTitle("예약")
//                .setMessage("예약 하시겠습니까?")
//                .setNegativeButton("다음에", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
//                    }
//                })
//                // 예약 완료 시 Ticket Value 출력 되어야 함
//                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//        builder.setCancelable(false);
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }
//
//    // Data 얻어오는 Method
//    public void getData(String url){
//        View.OnClickListener onClickListener;
//        class GetDataJSON extends AsyncTask<String,Void,String>{
//            @Override
//            // AsyncTask Background Method
//            protected String doInBackground(String... params){
//                String uri = params[0];
//                BufferedReader bufferedReader=null;
//                try{
//                    URL url = new URL(uri);
//                    HttpURLConnection con = (HttpURLConnection)url.openConnection();
//                    StringBuilder sb = new StringBuilder();
//
//                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//
//                    String json;
//                    while((json = bufferedReader.readLine())!= null){
//                        sb.append(json+"\n");
//                    }
//                    return sb.toString().trim();
//                } catch(Exception e){
//                    return null;
//                }
//            }
//            @Override
//            // Server 전송 Method
//            protected void onPostExecute(String result){
//                myJSON = result;
//                showList();
//            }
//        }
//        GetDataJSON g = new GetDataJSON();
//        g.execute(url);
//    }
//    public void onClick(View v)
//    {
//        View list = (View)v.getParent();
//    }
//}