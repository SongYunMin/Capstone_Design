package com.example.capstone_design;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public final class InsertSQLite {
    public static Context Insert_context;
    private InsertSQLite(){}

    public static class FeedEntry implements BaseColumns{
        public static final String TABLE_NAME = "Reservation_Local";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_TICKET = "ticket";
    }


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " ( " +
                    FeedEntry._ID + " INTEGER PRIMARY KEY, " +
                    FeedEntry.COLUMN_NAME_ID + "TEXT," +
                    FeedEntry.COLUMN_NAME_TICKET + "TEXT)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;


    public static class FeedReaderDbHelper extends SQLiteOpenHelper {
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "Mobile_Ticketing.db";

        public FeedReaderDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }

}



