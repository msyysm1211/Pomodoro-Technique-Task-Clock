package com.example.msy;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import database.Task;
import database.UserService;

public class NavigationTest extends AppCompatActivity {
    private TextView mTextMessage;
    private String username;//登录账号
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {//切换不同底部栏时跳转的不同界面
            switch (item.getItemId()) {

                case R.id.tab_one:
                   // mTextMessage.setText(R.string.title_home);
                    System.out.println(1);
                    return true;
                case R.id.tab_two:
                   // mTextMessage.setText(R.string.title_dashboard);
                    Intent intent = new Intent(NavigationTest.this,TabtwoActivity.class);
                    intent.putExtra("username",username);
                    startActivity(intent);
                   overridePendingTransition(0, 0);
                    System.out.println(2);
                    return true;
                case R.id.tab_three:
                    //mTextMessage.setText(R.string.title_notifications);
                    Intent intent2 = new Intent(NavigationTest.this,Tabthree2Activity.class);
                    intent2.putExtra("username",username);
                    startActivity(intent2);
                    overridePendingTransition(0, 0);
                    System.out.println(3);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_test);
        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");//得到传递得用户名称


        Button add=(Button)findViewById(R.id.add);//增加任务的右上角按钮+号 跳转
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationTest.this,AddTaskActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
        //生成初始化 按钮序列
        //先将数据库查询结果保存
        ArrayList<Task> result=new ArrayList<Task>();//结果集
        UserService uService=new UserService(NavigationTest.this);
        result =uService.usertask(username);

        //每有一个结果创建一个按钮
        LinearLayout ArrButton=(LinearLayout)findViewById(R.id.alltask);
        for(int i=0;i<result.size();i++){
            Button myButton = new Button(NavigationTest.this);
            myButton.setText(result.get(i).getTitle());//将按钮文本框输入
            myButton.setTextSize(20);//字体大小
            myButton.setTextColor(android.graphics.Color.parseColor("#FFFFFFFF"));//字体颜色
            myButton.setBackgroundColor(Color.parseColor(result.get(i).getBackgroundColor()));//设置按钮背景颜色
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 200);
            ArrButton.addView(myButton, lp);

            myButton.setTag(result.get(i));//将这个任务的信息存入到按钮的tag当中

            myButton.setOnClickListener(new Button.OnClickListener() {//给每个按钮任务设置监听事件  跳转到 计时器那个界面
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(NavigationTest.this,ClockActivity.class);//跳转到计时器页面
                    Task temp=(Task)v.getTag();
                    intent.putExtra("username",username);
                    intent.putExtra("task",temp.getTitle());//传设置的标题
                    intent.putExtra("time",temp.getTime());//传设置的时间
                    intent.putExtra("tpye",temp.getClockType());//传设置的时间
                    startActivity(intent);
                }
            });

            myButton.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Intent intent = new Intent(NavigationTest.this,ShowClock.class);
                    Task temp=(Task)v.getTag();
                    //创建删除任务时候的对话框
                   showThree(temp.getUsername(),temp.getTitle());
                   System.out.println(temp.getUsername()+temp.getTitle());
                    return true;
                }
            });


            }
        }
    private void showThree(final String usernmae, final String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setIcon(R.mipmap.ic_launcher).setTitle("")
                .setMessage("是否删除该任务").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {//确定删除 删除数据库
                        //ToDo: 你想做的事情

                        UserService uService=new UserService(NavigationTest.this);
                        uService.deleteTask(usernmae,title);
                        Intent intent2 = new Intent(NavigationTest.this,NavigationTest.class);//刷新删除后的界面
                        intent2.putExtra("username",username);
                        startActivity(intent2);
                        overridePendingTransition(0, 0);
                        Toast.makeText(NavigationTest.this, "删除成功", Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {//不删除 返回上一个菜单
                        //ToDo: 你想做的事情
                        dialogInterface.dismiss();
                    }
                });
        builder.create().show();

    }


    }


