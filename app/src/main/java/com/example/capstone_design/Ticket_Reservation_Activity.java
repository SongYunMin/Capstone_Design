package com.example.capstone_design;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.ContentValues;
import android.content.Context;
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
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import static com.example.capstone_design.LoginActivity.St_id;
import static com.example.capstone_design.NewAccount_Activity.ReservationWhether;
import static com.example.capstone_design.TicketAdapter.vo;


public class Ticket_Reservation_Activity extends AppCompatActivity
        implements AdapterView.OnClickListener {
    String myJSON;

    private static final String TAG_RESULT = "result";
    private static final String TAG_NUM = "num";
    private static final String TAG_NAME = "name";
    private static final String TAG_TIME = "time";
    private static final String TAG_DAY = "day";
    private static final String TAG_PLACE = "place";
    private static final String TAG_QUANTITY = "quantity";
    private static final String TAG_PHOTO = "photo";

    public static String Local_ID;
    public static String Local_Ticket;
    TicketAdapter adapter;
    JSONArray ticket = null;
    public static Context context_Reser;
//    public static ImageView MyTicket_img;
//    public static TextView MyTicket_name;

    //public static ArrayList<HashMap<String,String>>TicketList;
    public static ArrayList<Ticket_VO> datas = new ArrayList<>();
    public static TicketDatabaseManager DBManager;
    ListView list;
    String[] datass;

    // onCreate Method 실제 실행하는 함수
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticketing);
        context_Reser = this;

        DBManager = TicketDatabaseManager.getInstance(this);

        list = (ListView) findViewById(R.id.ticket_list);
        // getdata Method 접근, 아래 정의 되어 있음
        getData("http://210.124.110.96/Ticket_Value.php");
    }

    // List 의 들어갈 값을 가져오는 Method
    protected void showList(){
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

                Ticket_VO vo = new Ticket_VO();
                vo.cus_ticket = num;
                vo.cus_name = name;
                vo.cus_time = time;
                vo.cus_day = day;
                vo.cus_place = place;

                datas.add(vo);

            }
            // list_item
        TicketAdapter adapter = new TicketAdapter(this,R.layout.list_item,datas);
        list.setAdapter(adapter);

        }catch (JSONException e){
            e.printStackTrace();
        }
    }
    public static void clickHandler(View view)
    {
        if(ReservationWhether.equals("1")) {
            ContentValues addTicketValue = new ContentValues();

            addTicketValue.put("ID", St_id);
            addTicketValue.put("ticket", R.string.BTS);

            Ticket_Reservation_Activity.DBManager.insert(addTicketValue);

            Local_ID = St_id;
            Local_Ticket = "BTS WORLD TOUR";
        }
        else if(ReservationWhether.equals("2")){
            ContentValues addTicketValue = new ContentValues();

            addTicketValue.put("ID", St_id);
            addTicketValue.put("ticket", R.string.KKH);

            Ticket_Reservation_Activity.DBManager.insert(addTicketValue);

            Local_ID = St_id;
            Local_Ticket = "KimKyungHo Concert";
        }
        else if(ReservationWhether.equals("3")){
            ContentValues addTicketValue = new ContentValues();

            addTicketValue.put("ID", St_id);
            addTicketValue.put("ticket", R.string.MMMIA);

            Ticket_Reservation_Activity.DBManager.insert(addTicketValue);

            Local_ID = St_id;
            Local_Ticket = "MAMMAMIA!";
        }
        else if(ReservationWhether.equals("4")){
            ContentValues addTicketValue = new ContentValues();

            addTicketValue.put("ID", St_id);
            addTicketValue.put("ticket", R.string.SKJ);

            Ticket_Reservation_Activity.DBManager.insert(addTicketValue);

            Local_ID = St_id;
            Local_Ticket = "Seo Kang Jun Fan Concert";
        }
    }

//    public void getTIcketData()
//    {
//        ArrayList TIcketList = new ArrayList<>();
//
//        String[] columns = new String[] {"_id","ID","ticket"};
//        Cursor cursor = Ticket_Reservation_Activity.DBManager.query(columns, null, null,null,null,null);
//        if(cursor != null)
//        {
//            while (cursor.moveToNext())
//            {
//                TicketItem currentData = new TicketItem();
//
//                currentData.setId(cursor.getInt(0));
//                currentData.setTitle(cursor.getString(1));
//                currentData.setCategory(cursor.getString(2));
//                currentData.setGrade(cursor.getString(3));
//
//                TicketList.add(currentData);
//            }
//        }
//    }


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