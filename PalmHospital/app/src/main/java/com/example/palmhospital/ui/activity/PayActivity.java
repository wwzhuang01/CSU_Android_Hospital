package com.example.palmhospital.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.palmhospital.Config;
import com.example.palmhospital.R;
import com.example.palmhospital.bean.Doctor;
import com.example.palmhospital.bean.Schedule;
import com.example.palmhospital.bean.User;
import com.example.palmhospital.utils.NetUtil;
import com.google.gson.Gson;

public class PayActivity extends AppCompatActivity {
    private String baseurl= Config.baseUrl + "PayServlet";
    private User user;
    private Schedule schedule;
    private String result;
    private Doctor doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        // 获取参数
        String doctorJson = getIntent().getStringExtra("doctor");
        doctor =  new Gson().fromJson(doctorJson, Doctor.class);

        String scheduleJson = getIntent().getStringExtra("schedule");
        schedule =  new Gson().fromJson(scheduleJson, Schedule.class);

        //显示挂号信息
        TextView uname = findViewById(R.id.uname);
        TextView dname = findViewById(R.id.dname);
        TextView departname = findViewById(R.id.departname);
        TextView time = findViewById(R.id.time);
        TextView price = findViewById(R.id.price);
//
        user = IndexActivity.user;
        uname.setText(user.getUname());
        dname.setText(doctor.getDname());
        departname.setText(ChooseDoctorActivity.depart.getDepartname());
        time.setText(schedule.getTime());
        price.setText("￥"+schedule.getPrice());

        // 按钮
        Button pay_yes = findViewById(R.id.pay_yes);
        Button pay_no  = findViewById(R.id.pay_no);

        pay_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseurl += "?sid="+schedule.getSid()+"&uid="+user.getUid();
                Toast.makeText(getBaseContext(),"预约挂号成功！",Toast.LENGTH_SHORT).show();
                new PayActivity.PayAsyncTask().execute();

            }
        });

        pay_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(),"预约挂号取消成功！",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PayActivity.this,ChooseTimeActivity.class);
                intent.putExtra("doctor",new Gson().toJson(doctor));
                startActivity(intent);
            }
        });


    }

    public class PayAsyncTask extends AsyncTask<Void,Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            result = NetUtil.requestDataByGet(baseurl);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            Intent intent = new Intent(PayActivity.this,ChooseTimeActivity.class);
            intent.putExtra("doctor",new Gson().toJson(doctor));
            startActivity(intent);
            // 服务器返回

        }
    }


}