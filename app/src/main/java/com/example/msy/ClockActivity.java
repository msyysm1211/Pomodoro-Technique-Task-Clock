package com.example.msy;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ClockActivity extends AppCompatActivity {

    private String username;//登录账号
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Button clock=(Button)findViewById(R.id.clock);

/*        MediaPlayer  mediaPlayer;
        mediaPlayer=MediaPlayer.create(test.this,R.raw.music);
        mediaPlayer.setLooping(true);*/

        Intent intent2 = getIntent();
        username = intent2.getStringExtra("username");//得到传递得用户名称
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        String time = intent.getStringExtra("time");
        String task=intent.getStringExtra("task");
        if(type=="番茄钟")
        {//如果是番茄钟 25分钟休息一次
            time="25";
        }
        int i = Integer.parseInt( time );//设置计时器时间 string转int
        TextView title=(TextView)findViewById(R.id.usertasktitle) ;
        title.setText(task+"    "+time);
        //调用计时器
        final PeterTimeCountRefresh timer = new PeterTimeCountRefresh(i*60000, 1000, clock);
        timer.start();
        Button reset=(Button)findViewById(R.id.clock);//退出计时界
        reset.setTag(i);//将时间传递给onclick reset按钮
//        reset.setTag(1,intentmusic);
        reset.addTextChangedListener(new TextWatcher() {//监听按钮时间的变化
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                System.out.println(s.toString());
                MediaPlayer mediaPlayer;
                mediaPlayer=MediaPlayer.create(ClockActivity.this,R.raw.music);
                mediaPlayer.setLooping(true);
                if(s.toString().equals("start"))//如果时间为0播放音乐
                {

                    mediaPlayer.start();
                }
            }
        });


        reset.setOnClickListener(new View.OnClickListener(){//重置时间
            @Override
            public void onClick(View v) {
                int temp=(int)v.getTag();
                Button clock=(Button)findViewById(R.id.clock);
                PeterTimeCountRefresh timer = new PeterTimeCountRefresh(temp*60000, 1000, clock);
                timer.start();
            }
        });

        Button exit=(Button)findViewById(R.id.clockexit);//退出计时界面
        exit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ClockActivity.this,MyIntentService.class);//关闭背景音乐
                stopService(intent2);

                Intent intent = new Intent(ClockActivity.this,NavigationTest.class);
                intent.putExtra("username",username);
                startActivity(intent);

            }
        });

    }

}
