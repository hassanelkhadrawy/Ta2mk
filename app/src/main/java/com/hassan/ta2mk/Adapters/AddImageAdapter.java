package com.hassan.ta2mk.Adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hassan.ta2mk.R;

import java.util.ArrayList;

public class AddImageAdapter extends RecyclerView.Adapter<AddImageAdapter.adapter> {


    class adapter extends RecyclerView.ViewHolder {
        ImageView addImage,deleteImage;

        public adapter(@NonNull View itemView) {
            super(itemView);

            addImage=itemView.findViewById(R.id.imageitem);
            deleteImage=itemView.findViewById(R.id.deleteimageitem);

        }


    }

    Context context;
    ArrayList<Uri> uriArrayList=new ArrayList<>();

    public AddImageAdapter(Context context, ArrayList<Uri> uriArrayList) {
        this.context = context;
        this.uriArrayList = uriArrayList;
    }

    @NonNull
    @Override
    public adapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.addimageitem,parent,false);
        return new adapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter holder, final int position) {

        holder.addImage.setImageURI(uriArrayList.get(position));
        holder.deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uriArrayList.remove(position);
                notifyDataSetChanged();

            }
        });

    }

    @Override
    public int getItemCount() {
        return uriArrayList.size();
    }


}
