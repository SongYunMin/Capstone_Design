package com.example.capstone_design;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

//import static com.example.capstone_design.TicketAdapter.quantityView;
//import static com.example.capstone_design.TicketAdapter.vo;
import static com.example.capstone_design.TicketAdapter.context;

//class MyTicket_Adapter extends AppCompatActivity
//{
//    ImageView Myticket_img = (ImageView) findViewById(R.id.my_ticket_img);
////    TextView Myticket_name = (TextView) findViewById(R.id.my_ticket_name);
////    TextView Myticket_day = (TextView) findViewById(R.id.my_ticket_day);
////    TextView Myticket_time = (TextView) findViewById(R.id.my_ticket_time);
////    TextView MyTicket_place = (TextView) findViewById(R.id.my_ticket_place);
//
//    MyTicket_Adapter()
//    {
//        Myticket_img.setImageDrawable(ResourcesCompat.getDrawable
//                (context.getResources(),R.drawable.bts,null));
//
//    }
//}


public class Ticket_Reservation_Activity extends AppCompatActivity {
    String myJSON;

    private static final String TAG_RESULT = "result";
    private static final String TAG_NUM = "num";
    private static final String TAG_NAME = "name";
    private static final String TAG_TIME = "time";
    private static final String TAG_DAY = "day";
    private static final String TAG_PLACE = "place";
    private static final String TAG_QUANTITY = "quantity";
    private static final String TAG_PHOTO = "photo";
    TicketAdapter adapter;
    JSONArray ticket = null;

    public static ImageView MyTicket_img;
    public static TextView MyTicket_name;

    public static ArrayList<HashMap<String,String>>TicketList;
    ListView list;

    // onCreate Method 실제 실행하는 함수
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticketing);
        MyTicket_img = (ImageView) findViewById(R.id.my_ticket_img);
        MyTicket_name = (TextView) findViewById(R.id.my_ticket_name);
        list = (ListView) findViewById(R.id.ticket_list);
        TicketList = new ArrayList<HashMap<String, String>>();

        // getdata Method 접근, 아래 정의 되어 있음
        getData("http://210.124.110.96/Ticket_Value.php");
    }

    // List 의 들어갈 값을 가져오는 Method
    protected void showList(){
        ArrayList<Ticket_VO> datas = new ArrayList<>();
        try{
            JSONObject jsonObj = new JSONObject(myJSON);
            ticket = jsonObj.getJSONArray(TAG_RESULT);

            for(int i=0;i<ticket.length();i++){

                JSONObject c = ticket.getJSONObject(i);
                String num = c.getString(TAG_NUM);
                String name = c.getString(TAG_NAME);
                String time = c.getString(TAG_TIME);
                String day = c.getString(TAG_DAY);
                String place = c.getString(TAG_PLACE);
                String quantity = c.getString(TAG_QUANTITY);
                String photo = c.getString(TAG_PHOTO);
                int quantity_int = Integer.parseInt(quantity);

                Ticket_VO vo = new Ticket_VO();
                vo.cus_ticket = num;
                vo.cus_name = name;
                vo.cus_time = time;
                vo.cus_day = day;
                vo.cus_place = place;
                vo.cus_quantity = quantity_int;

                datas.add(vo);
            }

        TicketAdapter adapter = new TicketAdapter(this,R.layout.list_item,datas);
        list.setAdapter(adapter);

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

     // 사용 안함
    public void bt_reservation(View v)
    {

    }

    // Data 얻어오는 Method
    public void getData(String url){
        class GetDataJSON extends AsyncTask<String,Void,String>{
            @Override
            // AsyncTask Background Method
            protected String doInBackground(String... params){
                String uri = params[0];
                BufferedReader bufferedReader = null;
                try{
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection)url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

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
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }
    public void onClick(View v)
    {
        View list = (View)v.getParent();
    }
}