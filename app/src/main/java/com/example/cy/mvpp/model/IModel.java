package com.example.cy.mvpp.model;

import javax.security.auth.callback.Callback;

public interface IModel {
    //请求数据接口，并返回数据,接口回调方法
    void requestDat(String userName, String pwd, Callback callback);
    //这个接口是用来保存数据一个链接的桥梁
    interface Callback{
        void setData(String info);
    }
}
