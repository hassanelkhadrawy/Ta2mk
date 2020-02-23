package com.hassan.ta2mk.Model.Dashboard;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.hassan.ta2mk.Common.Commens;
import com.hassan.ta2mk.Model.CategoryModel;
import com.hassan.ta2mk.Model.RegisterModel;
import com.hassan.ta2mk.R;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class Additem implements AddItemInterface.AddItem {
    AddItemInterface.onListner onListner;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    FirebaseAuth firebaseAuth;
    ArrayList<String> listCategory = new ArrayList<>();
    ArrayList<String> listSize = new ArrayList<>();
    ArrayList<String> listTypes = new ArrayList<>();


    public Additem(AddItemInterface.onListner onListner) {
        this.onListner = onListner;
    }

    @Override
    public void GetCategories() {

        databaseReference = FirebaseDatabase.getInstance().getReference("Categories");
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    CategoryModel categoryModel = postSnapshot.getValue(CategoryModel.class);
                    listCategory.add(categoryModel.getName());
                }
                onListner.onListneCategoryrSuccess(listCategory);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                onListner.onListnerFaild(databaseError.getMessage());
            }
        });


    }

    @Override
    public void GetSize() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Size");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshotSize : dataSnapshot.getChildren()) {
                    CategoryModel categoryModel = postSnapshotSize.getValue(CategoryModel.class);
                    listSize.add(categoryModel.getName());
                }
                onListner.onListnerSizeSuccess(listSize);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                onListner.onListnerFaild(databaseError.getMessage());
            }
        });

    }

    @Override
    public void GetTypes(String Category) {
        listTypes.clear();
        databaseReference = FirebaseDatabase.getInstance().getReference("Type").child(Category);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshotColors : dataSnapshot.getChildren()) {
                    CategoryModel categoryModel = postSnapshotColors.getValue(CategoryModel.class);
                    listTypes.add(categoryModel.getName());
                }
                onListner.onListnerTypesSuccess(listTypes);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                onListner.onListnerFaild(databaseError.getMessage());
            }
        });


    }

    @Override
    public void UploadItemData(final ArrayList<Uri> uriArrayList, String catedory, final String color, final String size) {
        Random rand = new Random();
        String txt = String.valueOf(rand.nextInt(10000 - 0));
        databaseReference = FirebaseDatabase.getInstance().getReference("Shops").child(Commens.curent_user.getEmail().replace(".", "Dot")).child(catedory);
        storageReference = FirebaseStorage.getInstance().getReference("images/");
        firebaseAuth = FirebaseAuth.getInstance();

        if (!uriArrayList.isEmpty()) {


            for (int i = 0; i < uriArrayList.size(); i++) {


                String imgName = UUID.randomUUID().toString();
                final StorageReference imageFolder = storageReference.child("images/" + imgName);
                final int  finalI = i;
                imageFolder.putFile(uriArrayList.get(i))
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                imageFolder.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        AddItemModel addItemModel = new AddItemModel();

                                        if (TextUtils.isEmpty(uri.toString())) {

                                            addItemModel = new AddItemModel(uri.toString(), color, size);
                                        } else {
                                            addItemModel = new AddItemModel(uri.toString(), color, size);


                                        }
                                        databaseReference.child(String.valueOf(System.currentTimeMillis())).setValue(addItemModel);


                                        if (finalI ==uriArrayList.size() -1){

                                            onListner.onListnerSuccess();

                                        }
                                    }
                                });

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                onListner.onListnerFaild(e.getMessage());
                            }
                        });

            }

        }
    }
}
