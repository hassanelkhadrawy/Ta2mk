package com.hassan.ta2mk.Model.Register;

public interface RigisterViewInterface {
    interface View{
        void onSuccess(String Message);
        void onFaild(String Message);

    }
    interface presenter{

        void register(String name,String phone ,String email ,String password);
    }
    interface Interactor{
        void InsertData(String name,String phone ,String email ,String password);
    }
    interface onListner{
        void onListnerSuccess(String Message);
        void onListnerFaild(String Message);

    }

}
