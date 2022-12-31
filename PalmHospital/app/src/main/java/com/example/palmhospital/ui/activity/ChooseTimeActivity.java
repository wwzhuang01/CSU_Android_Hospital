package com.example.palmhospital.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.palmhospital.Config;
import com.example.palmhospital.R;
import com.example.palmhospital.bean.Doctor;
import com.example.palmhospital.bean.Schedule;
import com.example.palmhospital.utils.NetUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class ChooseTimeActivity extends AppCompatActivity {
    private String baseurl= Config.baseUrl + "ChooseTimeServlet";
    private String result;
    private Doctor doctor;
    private List<Schedule> schedules;

    private ListView timeListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_time);
        timeListView = findViewById(R.id.time_list_view);

        if(getIntent().hasExtra("doctor")){
            String doctorJson = getIntent().getStringExtra("doctor");
            doctor =  new Gson().fromJson(doctorJson, Doctor.class);
        }


        baseurl= baseurl + "?did="+doctor.getDid();
        new ChooseTimeActivity.TimeAsyncTask().execute();


        ImageView dimg = findViewById(R.id.dimg);
        TextView dname = findViewById(R.id.dname);
        TextView dlevel = findViewById(R.id.dlevel);
        TextView dinfo = findViewById(R.id.dinfo);


        if(doctor.getSex()==1){
            dimg.setImageResource(R.mipmap.doctor_male);
        }
        else{
            dimg.setImageResource(R.mipmap.doctor_female);
        }
        dname.setText(doctor.getDname());
        dlevel.setText(doctor.getDlevel());
        dinfo.setText(doctor.getDinfo());



        ImageView btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseTimeActivity.this,ChooseDoctorActivity.class);
                intent.putExtra("departid",doctor.getDdepartid());
                startActivity(intent);
            }
        });


    }

    public class TimeListAdapter extends BaseAdapter {
        private List<Schedule> mschedules;
        TimeListAdapter(List<Schedule> schedules){
            mschedules = schedules;
        }
        @Override
        public int getCount() {
            return mschedules.size();
        }

        @Override
        public Object getItem(int i) {
            return mschedules.get(i);
        }

        @Override
        public long getItemId(int i) {
            return mschedules.get(i).getDid();
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = layoutInflater.inflate(R.layout.item_time_list_view, null);
            }

            TextView time = view.findViewById(R.id.time);
            TextView price = view.findViewById(R.id.price);

            time.setText(mschedules.get(i).getTime());
            float fprice = mschedules.get(i).getPrice();
            price.setText("￥" + fprice);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 进入支付界面
                    Intent intent = new Intent(ChooseTimeActivity.this,PayActivity.class);
                    intent.putExtra("schedule",new Gson().toJson(mschedules.get(i)));
                    intent.putExtra("doctor",new Gson().toJson(doctor));
                    startActivity(intent);
                }
            });
            return view;
        }

    }



    public class TimeAsyncTask extends AsyncTask<Void,Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            result = NetUtil.requestDataByGet(baseurl);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Gson gson = new Gson();
            schedules = gson.fromJson(result, new TypeToken<List<Schedule>>() {}.getType());

            timeListView.setAdapter(new ChooseTimeActivity.TimeListAdapter( schedules));
        }
    }


}