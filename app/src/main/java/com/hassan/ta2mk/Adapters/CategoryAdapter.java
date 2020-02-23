package com.hassan.ta2mk.Adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.hassan.ta2mk.R;

public class CategoryAdapter extends RecyclerView.ViewHolder {
   public ImageView categoryImage;
//   public TextView categoryName;
    public Chip chip;

    public CategoryAdapter(@NonNull View itemView) {
        super(itemView);
        categoryImage=itemView.findViewById(R.id.category_image);
//        categoryName=itemView.findViewById(R.id.category_name);
        chip=itemView.findViewById(R.id.chip);


    }
}
