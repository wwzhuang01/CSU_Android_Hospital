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

public class LoginActivity extends AppCompatActivity {
    private EditText mEtUid;
    private EditText mEtUpsw;
    private Button mBtnLogin;
    private TextView mTvRegister;
    private String baseurl= Config.baseUrl + "LoginServlet";
    public static String uid;
    private String upsw;
    private String mResult;
    public User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = null;
        initView();
        initEvent();
    }

    private void initEvent() {

        // 点击登录按钮
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uid = mEtUid.getText().toString();
                upsw = mEtUpsw.getText().toString();
                Log.e("LoginActivity",""+uid);
                Log.e("LoginActivity",""+upsw);
                if(TextUtils.isEmpty(uid)||TextUtils.isEmpty(upsw)){
                    Log.i("LoginActivity","账号或密码不能为空");
                    Toast.makeText(getBaseContext(),"账号或密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }

                else{       // 提交参数给服务器端，进行登录逻辑验证
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            requestDataByPost(baseurl);
                            if(user!=null){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast ts = Toast.makeText(getBaseContext(),"登录成功！",Toast.LENGTH_SHORT);
                                        ts.show();

                                    }
                                });
                                Intent intent = new Intent(LoginActivity.this,IndexActivity.class);
                                intent.putExtra("user",new Gson().toJson(user));
                                startActivity(intent);

                            }
                            else{
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast ts = Toast.makeText(getBaseContext(),"账号或密码错误！",Toast.LENGTH_SHORT);
                                        ts.show();
                                    }
                                });
                            }
                        }
                    }).start();
                }
            }
        });

        // 点击注册按钮，跳转到注册界面
        mTvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toRegisterActivity();
            }
        });
    }

    private void toRegisterActivity() {
        startActivity(new Intent(this,RegisterActivity.class));
    }

    private void initView() {
        mEtUid = findViewById(R.id.id_et_uid);
        mEtUpsw = findViewById(R.id.id_et_upsw);
        mBtnLogin = findViewById(R.id.id_btn_login);
        mTvRegister = findViewById(R.id.id_tv_register);
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
            String data = "uid=" + URLEncoder.encode(uid, "UTF-8")
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

                user = new Gson().fromJson(result,User.class);


            } else {
                String responseMessage = connection.getResponseMessage();

            }


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