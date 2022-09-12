package database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TaskService {
    private DatabaseHelper dbHelper;
    public TaskService(Context context){
        dbHelper=new DatabaseHelper(context);
    }
    //
    public boolean login(String username){

        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="select * from task where username=? ";
        Cursor cursor=sdb.rawQuery(sql, new String[]{username});
        if(cursor.moveToFirst()==true){
            cursor.close();
            return true;
        }
        return false;
    }

    //创建任务
    public boolean register(Task task){
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="insert into task(username,title,backgroundColor,time,clockType) values(?,?,?,?,?)";
        Object obj[]={task.getUsername(),task.getTitle(),task.getBackgroundColor(),task.getTime(),task.getClockType()};
        sdb.execSQL(sql, obj);
        return true;
    }
}
