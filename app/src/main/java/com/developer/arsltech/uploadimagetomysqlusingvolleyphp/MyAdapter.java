package com.developer.arsltech.uploadimagetomysqlusingvolleyphp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<ImageViewHOlder> {

    private Context context;
    private List<ModelImage> imageList;

    public MyAdapter(Context context, List<ModelImage> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public ImageViewHOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout_item,parent,false);
        return new ImageViewHOlder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHOlder holder, int position) {


        Glide.with(context).load(imageList.get(position).getImageurl()).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }
}
class ImageViewHOlder extends RecyclerView.ViewHolder{

    ImageView imageView;
    public ImageViewHOlder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.imageView);
    }
}