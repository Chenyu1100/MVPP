package com.example.cy.mvpp.presenter;

import com.example.cy.mvpp.view.IView;

public interface IPresenter {
    //传入V层对象

    void onAttch(IView iView);
    void startRequest(String userName,String pwd);
    void onDeAttch();
}
