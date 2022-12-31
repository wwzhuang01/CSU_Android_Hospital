package com.example.palmhospital.utils;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetUtil {
    public static String requestDataByGet(String urlString) {
        String result = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(30000);        // 设置超时时间
            connection.setRequestMethod("GET");  // 请求的方法类型：GET POST
            connection.setRequestProperty("Content-Type", "application/json");      // 获取到的数据格式
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.connect();       // 发起连接
            int responseCode = connection.getResponseCode();    // 获取请求的返回码
            if (responseCode == HttpURLConnection.HTTP_OK) {        // 返回码是200，成功
                InputStream inputStream = connection.getInputStream();
                result = streamToString(inputStream);
            } else {
                String responseMessage = connection.getResponseMessage();
                //Log.e(TAG, "requestDataByPost: " + responseMessage);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 将输入流转换成字符串
     *
     * @param is 从网络获取的输入流
     * @return 字符串
     */
    public static String streamToString(InputStream is) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.close();
            is.close();
            byte[] byteArray = baos.toByteArray();
            return new String(byteArray);
        } catch (Exception e) {
            Log.e("LoginActivity", e.toString());
            return null;
        }
    }
}
