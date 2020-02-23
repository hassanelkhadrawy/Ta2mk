package com.hassan.ta2mk.Presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.hassan.ta2mk.Model.CategoryModel;
import com.hassan.ta2mk.Model.Dashboard.AddItemInterface;
import com.hassan.ta2mk.Model.Dashboard.Additem;

import java.util.ArrayList;

public class AddItemPresenter implements AddItemInterface.presenter,AddItemInterface.onListner {
    AddItemInterface.View view;
    Additem additem;
    Context context;


    public AddItemPresenter(AddItemInterface.View view) {
        this.view = view;
        additem=new Additem(this);
    }

    @Override
    public void Categories() {

        additem.GetCategories();
    }

    @Override
    public void Size() {

        additem.GetSize();
    }

    @Override
    public void Types(String Category) {

        additem.GetTypes(Category);
    }

    @Override
    public void SendItemData(ArrayList<Uri> uriArrayList, String catedory, String color, String size) {
        additem.UploadItemData(uriArrayList,catedory,color,size);
    }


    @Override
    public void SelectImage(Activity activity) {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
       activity. startActivityForResult(Intent.createChooser(intent, "select picture"), 1);

    }



    @Override
    public void onListneCategoryrSuccess(ArrayList<String> list) {
        view.ShowCategories(list);
    }

    @Override
    public void onListnerSizeSuccess(ArrayList<String> listSize) {

        view.ShowSize(listSize);
    }

    @Override
    public void onListnerTypesSuccess(ArrayList<String> listColor) {

        view.ShowTypes(listColor);
    }

    @Override
    public void onListnerSuccess() {
        view.onSuccess();
    }

    @Override
    public void onListnerFaild(String Message) {
        view.Failure(Message);
    }



}
