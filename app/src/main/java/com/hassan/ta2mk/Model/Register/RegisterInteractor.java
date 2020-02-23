package com.hassan.ta2mk.Model.Register;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hassan.ta2mk.Common.Commens;
import com.hassan.ta2mk.Model.RegisterModel;

public class RegisterInteractor implements RigisterViewInterface.Interactor {

    RigisterViewInterface.onListner onListner;

    public RegisterInteractor(RigisterViewInterface.onListner onListner) {
        this.onListner = onListner;
    }

    @Override
    public void InsertData(String name, String phone, final String email, String password) {


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        final RegisterModel registerModel=new RegisterModel(name,phone,email,password);

        databaseReference.child(email.replace(".","Dot")).setValue(registerModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Commens.curent_user=registerModel;
                onListner.onListnerSuccess("Success");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                onListner.onListnerFaild("Faild");
            }
        });
    }
}
