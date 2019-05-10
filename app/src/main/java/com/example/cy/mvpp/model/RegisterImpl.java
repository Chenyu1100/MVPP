package com.example.cy.mvpp.model;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RegisterImpl implements IModel {
    public static final String REQUEST_URL = "http://172.17.8.100/small/user/v1/register";
    @Override
    public void requestDat(String userName, String pwd, Callback callback) {
           //耗时操作
        new MyTask(userName,pwd,callback).execute(REQUEST_URL);
    }
    class MyTask extends AsyncTask<String,Void,String>{
        private String mUserName;
        private String mPwd;
        private Callback callback;
        public MyTask(String userName,String pwd,Callback callback){
             this.mUserName=userName;
             this.mPwd=pwd;
             this.callback=callback;
        }
        //耗时操作
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url=new URL(strings[0]);
                HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                OutputStream outputStream = connection.getOutputStream();
                String params="phone="+mUserName+"&pwd="+mPwd;
                outputStream.write(params.getBytes());
                outputStream.flush();
                outputStream.close();
                if (connection.getResponseCode()==200){
                    InputStream inputStream=connection.getInputStream();
                    String str =getStr(inputStream);
                    return str;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        //回调

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            callback.setData(s);
        }
    }
    public String getStr(InputStream inputStream) throws IOException {
        StringBuffer stringBuffer=new StringBuffer();
        byte[] bytes=new byte[1024];
        int len=0;
        while ((len=inputStream.read(bytes))!=-1){
            String info=new String(bytes,0,len);
            stringBuffer.append(info);
        }
        return stringBuffer.toString();
    }
}
