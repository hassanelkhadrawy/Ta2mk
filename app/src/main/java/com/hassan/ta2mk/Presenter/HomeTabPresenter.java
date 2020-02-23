package com.hassan.ta2mk.Presenter;

import android.content.Context;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.hassan.ta2mk.Adapters.CategoryAdapter;
import com.hassan.ta2mk.Model.CategoryModel;
import com.hassan.ta2mk.Model.HomeTab.HomeTab;
import com.hassan.ta2mk.Model.HomeTab.HomeTabInterface;

import java.util.ArrayList;

public class HomeTabPresenter implements HomeTabInterface.Presenter,HomeTabInterface.onListner {
    private HomeTabInterface.View view;
    HomeTab homeTab;

    public HomeTabPresenter(HomeTabInterface.View view) {
        this.view = view;
        homeTab=new HomeTab(this);
    }



    @Override
    public void onListnerSuccess(FirebaseRecyclerAdapter<CategoryModel, CategoryAdapter> adapter) {

        view.CategoryDataِ(adapter);
    }

    @Override
    public void onListnerfaild(String Meessage) {

        view.onFail(Meessage);
    }

    @Override
    public void Categories(Context context, FirebaseRecyclerAdapter<CategoryModel, CategoryAdapter> adapter) {
        homeTab.GetCategory(context,adapter);

    }
}
