package com.example.msy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import database.User;
import database.UserService;

public class RegisterActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Password;
    private EditText Age;
    private RadioGroup Sex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button Register=(Button)findViewById(R.id.Register);
        Register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setTitle("注册成功");
                builder.setMessage(getInfoUser().getUsername());
                builder.show();

                System.out.println(getInfoUser().getUsername());
                UserService uService=new UserService(RegisterActivity.this);
                boolean i=uService.register(getInfoUser());
                System.out.println("注册"+i);

                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
    public User getInfoUser(){
        User user=new User();
        Username=(EditText)findViewById(R.id.usernameRegister);
        Password=(EditText)findViewById(R.id.passwordRegister);
        Age=(EditText)findViewById(R.id.ageRegister);
        Sex=(RadioGroup) findViewById(R.id.sexRegister);
        user.setUsername(Username.getText().toString());
        user.setPassword(Password.getText().toString());
        user.setAge(Age.getText().toString());

        RadioButton sex = (RadioButton)findViewById(Sex.getCheckedRadioButtonId());
        user.setSex(sex.getText().toString());
        System.out.println("测试"+Username.getText().toString());
    return user;
    }
    //验证账户信息





}
