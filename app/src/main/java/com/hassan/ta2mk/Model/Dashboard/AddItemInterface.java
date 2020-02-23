package com.hassan.ta2mk.Model.Dashboard;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;

import com.hassan.ta2mk.Model.CategoryModel;

import java.util.ArrayList;

public interface AddItemInterface {
    interface View{
        void ShowCategories(ArrayList<String> list);
        void ShowSize(ArrayList<String> list);
        void ShowTypes(ArrayList<String> list);

        void onSuccess();
        void Failure(String Message);
    }
    interface presenter{
        void Categories();
        void Size();
        void Types(String Category);
        void SendItemData(ArrayList<Uri> uriArrayList,String catedory,String color,String size);
        void SelectImage(Activity activity);
    }
    interface onListner{
       void onListneCategoryrSuccess(ArrayList<String> listCategory);
        void onListnerSizeSuccess(ArrayList<String> listSize);
        void onListnerTypesSuccess(ArrayList<String> listColor);

        void onListnerSuccess();
        void onListnerFaild(String Message);

    }
    interface AddItem{
        void GetCategories();
        void GetSize();
        void GetTypes(String Category);
        void UploadItemData(ArrayList<Uri> uriArrayList,String catedory,String color,String size);
    }
}
