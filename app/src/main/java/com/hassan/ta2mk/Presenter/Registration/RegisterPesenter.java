package com.hassan.ta2mk.Presenter.Registration;

import com.hassan.ta2mk.Model.Register.RegisterInteractor;
import com.hassan.ta2mk.Model.Register.RigisterViewInterface;

public class RegisterPesenter implements RigisterViewInterface.presenter,RigisterViewInterface.onListner {
    RigisterViewInterface.View R_View;
    RegisterInteractor registerInteractor;

    public RegisterPesenter(RigisterViewInterface.View r_View) {
        R_View = r_View;
        registerInteractor=new RegisterInteractor(this);
    }

    @Override
    public void register(String name, String phone, String email, String password) {

        registerInteractor.InsertData(name,phone,email,password);
    }

    @Override
    public void onListnerSuccess(String Message) {

        R_View.onSuccess(Message);
    }

    @Override
    public void onListnerFaild(String Message) {

        R_View.onFaild(Message);
    }
}
