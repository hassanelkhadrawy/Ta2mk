package com.hassan.ta2mk.Model.HomeTab;

import android.content.Context;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.hassan.ta2mk.Adapters.CategoryAdapter;
import com.hassan.ta2mk.Model.CategoryModel;

import java.util.ArrayList;

public interface HomeTabInterface {
    interface View{

        void CategoryDataِ(FirebaseRecyclerAdapter<CategoryModel, CategoryAdapter> adapter);
        void onFail(String Meessage);


    }
    interface Presenter{
        void Categories(Context context,FirebaseRecyclerAdapter<CategoryModel, CategoryAdapter> adapter);
    }
    interface onListner{
        void onListnerSuccess(FirebaseRecyclerAdapter<CategoryModel, CategoryAdapter> adapter);
        void onListnerfaild(String Meessage);


    }
    interface Model{
        void GetCategory(Context context,FirebaseRecyclerAdapter<CategoryModel, CategoryAdapter> adapter);


    }
}
