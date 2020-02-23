package com.hassan.ta2mk.View;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.hassan.ta2mk.R;

public class DeleteItems extends AppCompatActivity {

    private RecyclerView recyclerDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_items);
        initView();
    }

    private void initView() {
        recyclerDelete = (RecyclerView) findViewById(R.id.recyclerDelete);
    }
}
