package com.hassan.ta2mk.Model.Login;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hassan.ta2mk.Common.Commens;
import com.hassan.ta2mk.Model.RegisterModel;

/**
 * Created by Ashish on 27-09-2017.
 */

public class LoginInteractor implements LoginContract.Intractor {

    private LoginContract.onLoginListener mOnLoginListener;
    DatabaseReference databaseReference;

    public LoginInteractor(LoginContract.onLoginListener onLoginListener){
        this.mOnLoginListener = onLoginListener;
    }
    @Override
    public void performFirebaseLogin(Activity activity, final String email, final String password) {

        databaseReference= FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.child(email.replace(".","Dot")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    RegisterModel registerModel = dataSnapshot.getValue(RegisterModel.class);


                    if (email.equals(registerModel.getEmail()) && password.equals(registerModel.getPassword())) {

                        Commens.curent_user=registerModel;
                        mOnLoginListener.onSuccess("success");

                    } else {
                        mOnLoginListener.onFailure("faild");

                    }
                }else {
                    mOnLoginListener.onFailure("faild");

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
