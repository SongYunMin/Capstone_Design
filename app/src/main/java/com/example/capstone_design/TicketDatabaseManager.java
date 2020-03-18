package com.example.capstone_design;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TicketDatabaseManager {
    static final String DB_NAME = "Mobile_Ticketing_Service";   // DB 이름
    static final String TABLE_NAME= "Reservation_Local";        // TABLE 이름
    static final int DB_VERSION = 1;                            // DB Version

    Context dbContext = null;

    private static TicketDatabaseManager myDBManager = null;
    private SQLiteDatabase myDatabase = null;



    // 싱글톤 패턴으로 구현
    public static TicketDatabaseManager getInstance(Context context)
    {
        if(myDBManager == null)
        {
            myDBManager = new TicketDatabaseManager(context);
        }

        return myDBManager;
    }

    private TicketDatabaseManager(Context context)
    {
        dbContext = context;

        // DB Open
        myDatabase = context.openOrCreateDatabase(DB_NAME,context.MODE_PRIVATE,null);

        // Table 생성
        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLE_NAME +
                "(" + "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ID TEXT," +
                "ticket TEXT);");
    }

    public long insert(ContentValues addLocalTicket)
    {
        return myDatabase.insert(TABLE_NAME,null,addLocalTicket);
    }

    public Cursor query(String[] colums,
                        String selection,
                        String[] selectionArgs,
                        String groupBy,
                        String having,
                        String orderby)
    {
        return myDatabase.query(TABLE_NAME,
                colums,
                selection,
                selectionArgs,
                groupBy,
                having,
                orderby);
    }


}
