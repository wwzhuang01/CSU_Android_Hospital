package com.example.palmhospital.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.palmhospital.R;
import com.example.palmhospital.bean.Doctor;
import com.example.palmhospital.bean.User;
import com.google.gson.Gson;

public class IndexActivity extends AppCompatActivity {
    public static User user = new User("001","李明","123");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        if(getIntent().hasExtra("user")){
            String userJson = getIntent().getStringExtra("user");
            user =  new Gson().fromJson(userJson, User.class);
        }

        TextView uname = findViewById(R.id.welcome_uname);

        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);

        if(user!=null){
            uname.setText(""  + user.getUname() + "，欢迎您！");
        }

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(IndexActivity.this,ChooseDepartActivity.class));
                Log.e("IndexActivity","即将跳转到查询所有部门界面");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndexActivity.this,MyInfoActivity.class);
                intent.putExtra("user",new Gson().toJson(user));
                startActivity(intent);

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IndexActivity.this,LoginActivity.class));
                Toast.makeText(getBaseContext(),"退出账户成功！",Toast.LENGTH_SHORT).show();
            }
        });
    }




}