package com.hassan.ta2mk.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hassan.ta2mk.Common.Commens;
import com.hassan.ta2mk.Model.Login.LoginContract;
import com.hassan.ta2mk.Presenter.Login.LoginPresenter;
import com.hassan.ta2mk.R;

public class LoginActivity extends AppCompatActivity  implements View.OnClickListener,LoginContract.View{

    EditText Email,Password;

    private LoginPresenter mLoginPresenter;
   // ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initi();
    }


    public void CreatAccount(View view) {
        startActivity(new Intent(this,Registration.class));
    }

    private void initi(){
        Email=findViewById(R.id.loginEmail);
        Password=findViewById(R.id.loginPassword);
        mLoginPresenter = new LoginPresenter(this);

        Commens.Prograss(this,getString(R.string.login));

    }
    private void Action(){

    if (TextUtils.isEmpty(Email.getText().toString())){
            Email.setError(getString(R.string.Enter_Email));

        }else if (TextUtils.isEmpty(Password.getText().toString())){
            Password.setError(getString(R.string.Enter_Password));

        }else if (!Commens.isConnectToInternet(this)){
            Toast.makeText(this, R.string.Check_Internet, Toast.LENGTH_SHORT).show();

        }else {

        Commens.mProgressDialog.show();
//        mProgressDialog.show();
        mLoginPresenter.login(this, Email.getText().toString(), Password.getText().toString());


        }
    }
    public void Login(View view) {
        Action();

    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onLoginSuccess(String message) {
       Commens. mProgressDialog.dismiss();
       startActivity(new Intent(this,Home.class));

    }

    @Override
    public void onLoginFailure(String message) {
        Commens. mProgressDialog.dismiss();
        Toast.makeText(getApplicationContext(),message , Toast.LENGTH_SHORT).show();
    }
}
