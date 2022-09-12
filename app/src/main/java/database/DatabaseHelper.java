package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    static String name="user.db";

    static int dbVersion=2;

    public DatabaseHelper(Context context) {

        super(context, name, null, dbVersion);

    }
    //只在创建的时候用一次

    public void onCreate(SQLiteDatabase db) {

        String sql="create table user(id integer primary key autoincrement," +
                "username varchar(20)," +
                "password varchar(20)," +
                "age integer," +
                "sex varchar(2))";
        String sql2="create table task(id integer primary key autoincrement," +
                "username varchar(20)," +
                "title varchar(20)," +
                "backgroundColor varchar(20)," +
                "time varchar(10)," +
                "clockType int(2))";
        db.execSQL(sql);
        db.execSQL(sql2);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists user");
        onCreate(db);
    }



}
