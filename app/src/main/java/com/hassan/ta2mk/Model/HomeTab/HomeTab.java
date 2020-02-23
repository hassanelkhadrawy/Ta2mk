package com.hassan.ta2mk.Model.HomeTab;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hassan.ta2mk.Adapters.CategoryAdapter;
import com.hassan.ta2mk.Model.CategoryModel;
import com.hassan.ta2mk.R;
import com.squareup.picasso.Picasso;

public class HomeTab implements HomeTabInterface.Model {

    DatabaseReference databaseReference;
    FirebaseRecyclerAdapter<CategoryModel, CategoryAdapter>adapter;

    HomeTabInterface.onListner onListner;

    public HomeTab(HomeTabInterface.onListner onListner) {
        this.onListner = onListner;
    }


    @Override
    public void GetCategory(final Context context, FirebaseRecyclerAdapter<CategoryModel, CategoryAdapter> adapter) {
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Categories");


        adapter=new FirebaseRecyclerAdapter<CategoryModel, CategoryAdapter>(CategoryModel.class, R.layout.category_item,CategoryAdapter.class,databaseReference) {
            @Override
            protected void populateViewHolder(CategoryAdapter categoryAdapter, CategoryModel categoryModel, int i) {

                Picasso.with(context).load(categoryModel.getImage()).placeholder(R.drawable.ic_add_circle_outline_black_24dp).into(categoryAdapter.categoryImage);
                categoryAdapter.categoryName.setText(categoryModel.getName());





            }
        };
        onListner.onListnerSuccess(adapter);
        adapter.notifyDataSetChanged();


        if (adapter.equals(null)){
            onListner.onListnerfaild(context.getString(R.string.faild));
        }

    }
}
