package com.hassan.ta2mk.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.hassan.ta2mk.R;

public class StartHome extends AppCompatActivity {

    private ImageView addUser;
    private ImageView search;
    private RecyclerView categoriesRecycler;
    private RecyclerView shopsRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starthome);
        initView();
        Action();
    }

    private void initView() {
        addUser = (ImageView) findViewById(R.id.addUser);
        search = (ImageView) findViewById(R.id.search);
        categoriesRecycler = (RecyclerView) findViewById(R.id.categories_recycler);
        shopsRecycler = (RecyclerView) findViewById(R.id.shops_recycler);
    }

    private void Action(){
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartHome.this,LoginActivity.class));
            }
        });
    }

}
