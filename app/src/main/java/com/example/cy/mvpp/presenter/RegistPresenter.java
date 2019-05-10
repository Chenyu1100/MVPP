package com.example.cy.mvpp.presenter;

import com.example.cy.mvpp.model.IModel;
import com.example.cy.mvpp.model.RegisterImpl;
import com.example.cy.mvpp.view.IView;

public class RegistPresenter implements IPresenter{
 private IView view;
 private IModel model;
    public RegistPresenter(IView iView) {
        //多态  父类引用指向子类实例
        model = new RegisterImpl();
        this.view = iView;
    }
    @Override
    public void onAttch(IView iView) {
       this.view=iView;
    }

    @Override
    public void startRequest(String userName, String pwd) {
          model.requestDat(userName, pwd, new IModel.Callback() {
              @Override
              public void setData(String info) {
                  view.getResponse(info);
              }
          });
    }

    @Override
    public void onDeAttch() {
        if (model != null) {
            model = null;
        }
        if (view != null) {
            view = null;
        }
    }
}
