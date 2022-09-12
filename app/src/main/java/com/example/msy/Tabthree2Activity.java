package com.example.msy;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import database.UserService;

public class Tabthree2Activity extends AppCompatActivity {
    private String username;
    private TextView mTextMessage;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {//切换不同底部栏时跳转的不同界面
            switch (item.getItemId()) {

                case R.id.tab_one:
                    Intent intent = new Intent(Tabthree2Activity.this,NavigationTest.class);
                    intent.putExtra("username",username);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    System.out.println(1);
                    return true;
                case R.id.tab_two:
                    Intent intent2 = new Intent(Tabthree2Activity.this,TabtwoActivity.class);
                    intent2.putExtra("username",username);
                    startActivity(intent2);
                    overridePendingTransition(0, 0);
                    System.out.println(2);
                    return true;
                case R.id.tab_three:
                    System.out.println(3);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabthree2);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");//得到传递得用户名称

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.navigation);//设置底部导航默认选择的图标
        navigationView.setSelectedItemId(R.id.tab_three);
        getPieChartData();
    }
    private void getPieChartData() {
        UserService uService=new UserService(Tabthree2Activity.this);
        String sport=uService.SportsTime(username);//运动时间
        String study=uService.StudyTime(username);//学习时间
        String tomato=uService.PotatoTime(username);//番茄时间

        if(sport==null)
            sport="0";
        if(study==null)
            study="0";
        if(tomato==null)
            tomato="0";

        Float sp = Float.parseFloat(sport);
        Float st = Float.parseFloat(study);
        Float tom = Float.parseFloat(tomato);
        float a= sp/(sp+st+tom);
        float b= st/(sp+st+tom);
        float c= tom/(sp+st+tom);
        System.out.println(sp);
        System.out.println(st);
        System.out.println(tom);
        System.out.println("百分比"+a+" "+b+" "+c);
        List<PieEntry> strings = new ArrayList<>();
        strings.add(new PieEntry(a,"运动"));
        strings.add(new PieEntry(b,"学习"));
        strings.add(new PieEntry(c,"番茄工作法"));

        PieDataSet dataSet = new PieDataSet(strings,"");//正中间文字没啥用
        //设置时间
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(getResources().getColor(R.color.colorAccent));
        colors.add(getResources().getColor(R.color.button3));
        colors.add(getResources().getColor(R.color.button2));
        dataSet.setColors(colors);

        PieData pieData = new PieData(dataSet);
        pieData.setDrawValues(true);
        PieChart picChart = (PieChart)findViewById(R.id.pie_chart) ;
        //输入数据\
        picChart.setCenterTextSize(20);
        picChart.setCenterTextSizePixels(20);
        picChart.setEntryLabelTextSize(20);
        picChart.setData(pieData);
        //饼图的标题
        Description description = new Description();
        description.setText("");
        picChart.setDescription(description);
        pieData.setDrawValues(true);
        pieData.setValueFormatter(new PercentFormatter());//设置饼状图为百分比
        picChart.invalidate();
    }













}
