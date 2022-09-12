package database;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class UserService {

    private DatabaseHelper dbHelper;
    public UserService(Context context){
        dbHelper=new DatabaseHelper(context);
    }


    //登录用
    public boolean login(String username,String password){

        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="select * from user where username=? and password=?";
        Cursor cursor=sdb.rawQuery(sql, new String[]{username,password});
        if(cursor.moveToFirst()==true){
            cursor.close();
            return true;
        }
        return false;
    }

    //注册用
    public boolean register(User user){
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="insert into user(username,password,age,sex) values(?,?,?,?)";
        Object obj[]={user.getUsername(),user.getPassword(),user.getAge(),user.getSex()};
        sdb.execSQL(sql, obj);
        return true;
    }



    //查找任务
    public boolean findtask(String username){

        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="select * from task where username=? ";
        Cursor cursor=sdb.rawQuery(sql, new String[]{username});
        if(cursor.moveToFirst()==true){
            cursor.close();
            return true;
        }
        return false;
    }
    //删除任务
    public void deleteTask(String username,String title){//删除某 一个具体任务

        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        sdb.delete("task", "username=? and title=?", new String[]{ username,title });
    }

    //读取数据库中 username 的所有任务信息
    public ArrayList<Task>  usertask(String username){
        ArrayList<Task> allusertask=new ArrayList<Task>();
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="  select * from task where username = ?";
        Cursor cursor=sdb.rawQuery(sql, new String[]{username});
        while (cursor.moveToNext()) {//将查询结果保存在一个ArrayList中
            String time=cursor.getString(cursor.getColumnIndex("time"));
            String backgroundColor=cursor.getString(cursor.getColumnIndex("backgroundColor"));
            String clockType=cursor.getString(cursor.getColumnIndex("clockType"));
            String title=cursor.getString(cursor.getColumnIndex("title"));
            Task p=new Task(username,time,backgroundColor,clockType,title);
            allusertask.add(p);
        }
        return allusertask;
    }

    //创建任务
    public boolean addtask(Task task){
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="insert into task(username,title,backgroundColor,time,clockType) values(?,?,?,?,?)";
        Object obj[]={task.getUsername(),task.getTitle(),task.getBackgroundColor(),task.getTime(),task.getClockType()};
        sdb.execSQL(sql, obj);
        return true;
    }
    //查询个人总时间
    public String allTimeSum(String username){

        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="select sum(time) as t from task where username=? ";
        Cursor cursor=sdb.rawQuery(sql, new String[]{username});
        cursor.moveToFirst();
         String time=cursor.getString(cursor.getColumnIndex("t"));
        return time;
    }
    //查询运动总时间
    public String SportsTime(String username){

        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="select sum(time) as t from task where username=? and title= ? ";
        String title="sport";
        Cursor cursor=sdb.rawQuery(sql, new String[]{username,title});
        cursor.moveToFirst();
        String time=cursor.getString(cursor.getColumnIndex("t"));
        System.out.println((time+"!!!!!!!!!!!!!!!!!!!!!!!"));
        return time;
    }
    //查询学习总时间
    public String StudyTime(String username){

        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="select sum(time) as t from task where username=? and title= ? ";
        String title="study";
        Cursor cursor=sdb.rawQuery(sql, new String[]{username,title});
        cursor.moveToFirst();
        String time=cursor.getString(cursor.getColumnIndex("t"));
        return time;
    }
    //查询使用番茄工作法总时间
    public String PotatoTime(String username){

        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="select sum(time) as t from task where username=? and clockType= ? ";
        String clockType="番茄钟";
        Cursor cursor=sdb.rawQuery(sql, new String[]{username,clockType});
        cursor.moveToFirst();
        String time=cursor.getString(cursor.getColumnIndex("t"));
        return time;
    }
    //查询最长单次时间
    public String MaxTime(String username){

        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="select max(cast(time as int)) as a from task where username=? ";
        Cursor cursor=sdb.rawQuery(sql, new String[]{username});
        cursor.moveToFirst();
        String time=cursor.getString(cursor.getColumnIndex("a"));
        return time;
    }

}
