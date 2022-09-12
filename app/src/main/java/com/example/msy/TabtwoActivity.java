package com.example.msy;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import database.UserService;

public class TabtwoActivity extends AppCompatActivity {
    private String username;
    private TextView alltime;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {//切换不同底部栏时跳转的不同界面
            switch (item.getItemId()) {

                case R.id.tab_one:
                    // mTextMessage.setText(R.string.title_home);
                    Intent intent = new Intent(TabtwoActivity.this,NavigationTest.class);
                    intent.putExtra("username",username);
                    startActivity(intent);
                    overridePendingTransition(0, 0);////设置过度动画底部切换
                    System.out.println(1);
                    return true;
                case R.id.tab_two:
                    // mTextMessage.setText(R.string.title_dashboard);
                    System.out.println(2);
                    return true;
                case R.id.tab_three:
                    //mTextMessage.setText(R.string.title_notifications);
                    Intent intent2 = new Intent(TabtwoActivity.this,Tabthree2Activity.class);
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
        setContentView(R.layout.activity_tabtwo);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");//得到传递得用户名称
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.navigation);//设置底部导航默认选择的图标
        navigationView.setSelectedItemId(R.id.tab_two);

        UserService uService=new UserService(TabtwoActivity.this);
        //设置总时长
         alltime=(TextView)findViewById(R.id.alltime);
        if(uService.allTimeSum(username)!=null)
            alltime.setText("总时长："+uService.allTimeSum(username)+"分钟");
        else
            alltime.setText("总时长："+0+"分钟");

        alltime=(TextView)findViewById(R.id.sport);
        if(uService.SportsTime(username)!=null)
            alltime.setText("运动："+uService.SportsTime(username)+"分钟");
        else
            alltime.setText("运动："+0+"分钟");

        alltime=(TextView)findViewById(R.id.study);
        if(uService.StudyTime(username)!=null)
            alltime.setText("学习："+uService.StudyTime(username)+"分钟");
        else
            alltime.setText("学习："+0+"分钟");

        alltime=(TextView)findViewById(R.id.tomato);

        if(uService.PotatoTime(username)!=null)
             alltime.setText("番茄工作法："+uService.PotatoTime(username)+"分钟");
        else
            alltime.setText("番茄工作法："+0+"分钟");

        alltime=(TextView)findViewById(R.id.largesttime);
        if(uService.MaxTime(username)!=null)
            alltime.setText("最长时间："+uService.MaxTime(username)+"分钟");
        else
            alltime.setText("最长时间："+0+"分钟");


    }
}
