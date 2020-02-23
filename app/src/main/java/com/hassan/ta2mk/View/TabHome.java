package com.hassan.ta2mk.View;


import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.hassan.ta2mk.Adapters.CategoryAdapter;
import com.hassan.ta2mk.Model.CategoryModel;
import com.hassan.ta2mk.Model.HomeTab.HomeTabInterface;
import com.hassan.ta2mk.Presenter.HomeTabPresenter;
import com.hassan.ta2mk.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabHome extends Fragment implements HomeTabInterface.View {


  //  private Toolbar toolbar;
    private ImageView cart;
    private ImageView search;
    private RecyclerView categoryrecycler;
    private RecyclerView shoprecycler;
    private HomeTabPresenter homeTabPresenter;
    FirebaseRecyclerAdapter<CategoryModel, CategoryAdapter> adapter;
    public TabHome() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab_home, container, false);

        initView(view);
        return view;

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initView(View view) {
       // toolbar = (Toolbar)view. findViewById(R.id.toolbar);
        //cart = (ImageView)view. findViewById(R.id.cart);
       // search = (ImageView)view. findViewById(R.id.search);
        categoryrecycler = (RecyclerView)view. findViewById(R.id.categoryrecycler);
        shoprecycler = (RecyclerView) view.findViewById(R.id.shoprecycler);
        homeTabPresenter=new HomeTabPresenter(this);
        homeTabPresenter.Categories(getContext(),adapter);
    }


    @Override
    public void CategoryDataِ(FirebaseRecyclerAdapter<CategoryModel, CategoryAdapter> adapter) {


        categoryrecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        categoryrecycler.setAdapter(adapter);
    }

    @Override
    public void onFail(String Meessage) {

        Toast.makeText(getActivity(), Meessage, Toast.LENGTH_SHORT).show();
    }
}
