package com.example.msy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;

import java.util.ArrayList;
import java.util.List;

import database.Task;
import database.TaskService;

public class AddTaskActivity extends AppCompatActivity {
    private Button addfinish;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Intent intent = getIntent();

        username = intent.getStringExtra("username");//得到传递得用户名称

        //滚动时钟计时的添加
        WheelView wheelView = findViewById(R.id.wheelview);
        wheelView.setCyclic(false);

        final List<String> mOptionsItems = new ArrayList<>();//添加60分钟 以内的选项
        for(int i=0;i<61;i++)
        mOptionsItems.add(""+i);

        wheelView.setAdapter(new ArrayWheelAdapter(mOptionsItems));
        wheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {//滚动wheel时候出发的事件
              //  Toast.makeText(AddTaskActivity.this, "" + mOptionsItems.get(index), Toast.LENGTH_SHORT).show();
            }
        });
        System.out.println(wheelView.getCurrentItem());

/////////////////////////////
        RadioButton clocktype = (RadioButton)findViewById(R.id.fanqie);//番茄隐藏
        clocktype.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                WheelView wheelView = findViewById(R.id.wheelview);
                wheelView.setVisibility(wheelView.INVISIBLE);
            }
        });
        RadioButton clocktype2 = (RadioButton)findViewById(R.id.daojishi);
        clocktype2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                WheelView wheelView = findViewById(R.id.wheelview);
                wheelView.setVisibility(wheelView.VISIBLE);
            }
        });
        RadioButton clocktype3 = (RadioButton)findViewById(R.id.leiji);
        clocktype3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                WheelView wheelView = findViewById(R.id.wheelview);
                wheelView.setVisibility(wheelView.VISIBLE);
            }
        });
////////////////////////////////
        addfinish=(Button)findViewById(R.id.addTaskFinish);//
        addfinish.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Task task=new Task();
                task=getTaskInfo();
                System.out.println(getTaskInfo().toString());
                //将 添加得任务参数写入到数据库当中
                TaskService tService=new TaskService(AddTaskActivity.this);
                boolean i=tService.register(task);
                //跳回主页面
                Intent intent = new Intent(AddTaskActivity.this,NavigationTest.class);
                intent.putExtra("username",username);
                startActivity(intent);

            }
        });


    }
    public Task getTaskInfo(){
        EditText title=(EditText)findViewById(R.id.tasktitle);
        RadioGroup type=(RadioGroup)findViewById(R.id.clocktype);
        WheelView time=(WheelView)findViewById(R.id.wheelview);
        Task task=new Task();
        task.setTitle(title.getText().toString());
        task.setTime(""+time.getCurrentItem());

        RadioButton clocktype = (RadioButton)findViewById(type.getCheckedRadioButtonId());
        task.setClockType(clocktype.getText().toString());
        RadioButton fanqie=(RadioButton)findViewById(R.id.fanqie);
        if(fanqie.isChecked())
        {
            task.setTime("25");
        }
        task.setUsername(username);
        //随机设置任务的背景颜色
        String[] colorlist={"#923F51B5","#66673AB7","#999C27B0","#C1CDDC39","#95009688","#948BC34A"};
        int index=(int)(Math.random()*colorlist.length);
        String rand = colorlist[index];

        task.setBackgroundColor(rand);
        return task;
    }
}
