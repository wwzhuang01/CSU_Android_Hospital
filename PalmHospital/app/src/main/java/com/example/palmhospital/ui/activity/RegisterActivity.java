package com.example.palmhospital.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.palmhospital.Config;
import com.example.palmhospital.R;
import com.example.palmhospital.bean.User;
import com.example.palmhospital.utils.NetUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class RegisterActivity extends AppCompatActivity {
    private EditText mEtUname;
    private EditText mEtUid;
    private EditText mEtUpsw;
    private EditText mEtReUpsw;
    private Button mBtnRegister;
    private TextView mTvLogin;

    private String uname;
    private String uid;
    private String upsw;
    private String reupsw;
    private String TAG = "RegisterActivity";

    private User user = null;
    private String baseurl= Config.baseUrl + "RegisterServlet";
    private String mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
        initEvent();
    }

    private void initEvent() {
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 检验内容是否都填上了，如果是，进行注册
                uname = mEtUname.getText().toString();
                uid = mEtUid.getText().toString();
                upsw = mEtUpsw.getText().toString();
                reupsw = mEtReUpsw.getText().toString();

                if(TextUtils.isEmpty(uid)||TextUtils.isEmpty(upsw)||TextUtils.isEmpty(uname)||TextUtils.isEmpty(reupsw)){
                    Toast.makeText(getBaseContext(),"所填信息不能为空！",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(!upsw.equals(reupsw)){
                    Toast.makeText(getBaseContext(),"两次输入的密码不相同！",Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            mResult = requestDataByPost(baseurl);

                            if(user!=null){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast ts = Toast.makeText(getBaseContext(),"注册成功！",Toast.LENGTH_SHORT);
                                        ts.show();
                                        toLoginActivity();

                                    }
                                });
                            }
                            else{
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        Toast ts = Toast.makeText(getBaseContext(),"账号已存在！",Toast.LENGTH_SHORT);
                                        ts.show();
                                    }
                                });
                            }

                        }
                    }).start();
                }
            }
        });
        mTvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toLoginActivity();
            }
        });
    }

    private void toLoginActivity() {
        startActivity(new Intent(this,LoginActivity.class));
    }

    private void initView() {
        mEtUname = findViewById(R.id.id_et_register_uname);
        mEtUid = findViewById(R.id.id_et_register_uid);
        mEtUpsw = findViewById(R.id.id_et_register_upsw);
        mEtReUpsw = findViewById(R.id.id_et_register_reupsw);
        mBtnRegister = findViewById(R.id.id_btn_register);
        mTvLogin = findViewById(R.id.id_tv_login);


    }
    public String requestDataByPost(String urlString) {
        String result = null;
        try {

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(30000);
            connection.setRequestMethod("POST");
            // 设置运行输入,输出:
            connection.setDoOutput(true);
            connection.setDoInput(true);
            // Post方式不能缓存,需手动设置为false
            connection.setUseCaches(false);
            connection.connect();

            // 我们请求的数据:
            String data ="uname=" + URLEncoder.encode(uname, "UTF-8") +  "&uid=" + URLEncoder.encode(uid, "UTF-8")
                    + "&upsw=" + URLEncoder.encode(upsw, "UTF-8");

            // 获取输出流
            OutputStream out = connection.getOutputStream();
            out.write(data.getBytes());
            out.flush();
            out.close();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                result = NetUtil.streamToString(inputStream);
                Gson gson = new Gson();
                user = gson.fromJson(result, User.class);

            } else {
                String responseMessage = connection.getResponseMessage();
            }
            final String finalResult = result;

            connection.disconnect();
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}