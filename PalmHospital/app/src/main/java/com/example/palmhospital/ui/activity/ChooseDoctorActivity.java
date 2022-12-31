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
import com.example.palmhospital.bean.Depart;
import com.example.palmhospital.bean.Doctor;
import com.example.palmhospital.bean.User;
import com.example.palmhospital.utils.NetUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class ChooseDoctorActivity extends AppCompatActivity {
    private String baseurl= Config.baseUrl + "ChooseDoctorServlet";
    public static Depart depart = null;
    private String result;
    private List<Doctor> doctors;
    private ListView doctorListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_doctor);

        int departid = 1;
        if(getIntent().hasExtra("depart")){
            String departJson = getIntent().getStringExtra("depart");
            depart =  new Gson().fromJson(departJson, Depart.class);
            departid = depart.getDepartid();
        }
        else if(getIntent().hasExtra("departid")){
            departid = getIntent().getIntExtra("departid",1);
        }


        baseurl = baseurl + "?departid=" + departid;

        // 向服务器请求医生的数据 —— 传参数：科室id

        doctorListView = (ListView)findViewById(R.id.doctor_list_view);
        new ChooseDoctorActivity.DoctorAsyncTask().execute();

        ImageView btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseDoctorActivity.this,ChooseDepartActivity.class);
                startActivity(intent);
            }
        });



    }

    public class DoctorListAdapter extends BaseAdapter {
        private List<Doctor> mdoctors;
        DoctorListAdapter(List<Doctor> doctors){
            mdoctors = doctors;
        }
        @Override
        public int getCount() {
            return mdoctors.size();
        }

        @Override
        public Object getItem(int i) {
            return mdoctors.get(i);
        }

        @Override
        public long getItemId(int i) {
            return mdoctors.get(i).getDid();
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            if(view == null){
                LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = layoutInflater.inflate(R.layout.item_doctor_list_view,null);
            }
            ImageView dimg = view.findViewById(R.id.dimg);
            TextView dname = view.findViewById(R.id.dname);
            TextView dlevel = view.findViewById(R.id.dlevel);
            TextView dinfo = view.findViewById(R.id.dinfo);

            LinearLayout btn_ddetail = view.findViewById(R.id.btn_ddetail);
            Button btn_reverse = view.findViewById(R.id.btn_reverse);


            if(mdoctors.get(i).getSex()==1){
                dimg.setImageResource(R.mipmap.doctor_male);
            }
            else{
                dimg.setImageResource(R.mipmap.doctor_female);
            }
            dname.setText(mdoctors.get(i).getDname());
            dlevel.setText(mdoctors.get(i).getDlevel());
            dinfo.setText(mdoctors.get(i).getDinfo());


            btn_ddetail.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ChooseDoctorActivity.this,DoctorDetailActivity.class);
                    intent.putExtra("doctor",new Gson().toJson(mdoctors.get(i)));
                    startActivity(intent);
                }
            });

            btn_reverse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ChooseDoctorActivity.this,ChooseTimeActivity.class);
                    intent.putExtra("doctor",new Gson().toJson(mdoctors.get(i)));
                    startActivity(intent);
                }
            });

            return view;
        }
    }


    public class DoctorAsyncTask extends AsyncTask<Void,Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            result = NetUtil.requestDataByGet(baseurl);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Gson gson = new Gson();
            doctors = gson.fromJson(result, new TypeToken<List<Doctor>>() {}.getType());
            doctorListView.setAdapter(new ChooseDoctorActivity.DoctorListAdapter( doctors));
        }
    }
}