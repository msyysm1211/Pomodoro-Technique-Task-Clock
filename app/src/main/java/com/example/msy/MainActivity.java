package com.example.msy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import database.UserService;

public class MainActivity extends AppCompatActivity {
    private Button Reg;
    private Button Login;
    private EditText InputUsername;
    private EditText InputPassword;
    private Intent intent2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //注册按钮 页面跳转
        Reg=(Button)findViewById(R.id.register);
        //Reg.setTag(mediaPlayer);
        Reg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        Login=(Button)findViewById(R.id.login);
       // 登录按钮的跳转
        Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            if(ClickUser()==true){//如果验证成功得话  跳转到新的界面
                Intent intent = new Intent(MainActivity.this,NavigationTest.class);
                 EditText InputUsername2=(EditText)findViewById(R.id.username);
                intent.putExtra("username",InputUsername2.getText().toString());//将账号名称传递给跳转得页面
                startActivity(intent);
            }
            else {//验证失败得情况 弹出失败
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("密码错误");
                builder.show();
            }
            }
        });
    }

    public boolean ClickUser(){
        InputUsername=(EditText)findViewById(R.id.username);
        InputPassword=(EditText)findViewById(R.id.password);
        UserService uService=new UserService(MainActivity.this);
        //验证输入账号密码是否准确
        boolean i=uService.login(InputUsername.getText().toString(),InputPassword.getText().toString());
        return i;
    }


}
