package com.hassan.ta2mk.View;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hassan.ta2mk.Common.Commens;
import com.hassan.ta2mk.Model.Register.RigisterViewInterface;
import com.hassan.ta2mk.Presenter.Fonts.Comfortaa_Regular;
import com.hassan.ta2mk.Presenter.Registration.RegisterPesenter;
import com.hassan.ta2mk.R;

public class Registration extends AppCompatActivity implements RigisterViewInterface.View {

    private Comfortaa_Regular name;
    private Comfortaa_Regular phone;
    private Comfortaa_Regular email;
    private Comfortaa_Regular password;
    RegisterPesenter registerPesenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initView();

    }

    private void initView() {
        name = (Comfortaa_Regular) findViewById(R.id.name);
        phone = (Comfortaa_Regular) findViewById(R.id.phone);
        email = (Comfortaa_Regular) findViewById(R.id.email);
        password = (Comfortaa_Regular) findViewById(R.id.password);
        registerPesenter=new RegisterPesenter(this);
        Commens.Prograss(this,getString(R.string.wait));


    }
    public void Register(View view) {

        if (TextUtils.isEmpty(name.getText().toString())){
            name.setError(getString(R.string.Enter_Name));

        }else if (TextUtils.isEmpty(phone.getText().toString())){
            phone.setError(getString(R.string.Enter_Phone));

        }else if (TextUtils.isEmpty(email.getText().toString())){
            email.setError(getString(R.string.Enter_Email));

        }else if (TextUtils.isEmpty(password.getText().toString())){
            password.setError(getString(R.string.Enter_Password));

        }else if (!Commens.isConnectToInternet(this)){
            Toast.makeText(this, R.string.Check_Internet, Toast.LENGTH_SHORT).show();

        }else {

            Commens.mProgressDialog.show();
            registerPesenter.register(name.getText().toString(),phone.getText().toString(),email.getText().toString(),password.getText().toString());
//            iRegister_presenter.RegisterData(name.getText().toString(),phone.getText().toString(),email.getText().toString(),password.getText().toString());

        }
    }



    @Override
    public void onSuccess(String Message) {
        Commens.mProgressDialog.dismiss();

        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFaild(String Message) {
        Commens.mProgressDialog.dismiss();
        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();


    }
}
