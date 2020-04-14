package com.hyungilee.mvvmrecyclerviewjava.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hyungilee.mvvmrecyclerviewjava.R;
import com.hyungilee.mvvmrecyclerviewjava.models.NicePlace;

import java.util.ArrayList;
import java.util.List;

public class NicePlaceRecycleAdapter extends RecyclerView.Adapter<NicePlaceRecycleAdapter.Holder> {

    private Context mContext;
    private List<NicePlace> mNicePlaces = new ArrayList<>();

    public NicePlaceRecycleAdapter(Context context, List<NicePlace> nicePlaces){
        this.mContext = context;
        this.mNicePlaces = nicePlaces;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_listitem, null);
        Holder vh = new Holder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.mTitle.setText(mNicePlaces.get(position).getTitle());

        // Set the image
        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);

        Glide.with(mContext)
                .setDefaultRequestOptions(defaultOptions)
                .load(mNicePlaces.get(position).getImageUrl())
                .into(((Holder)holder).mImage);
    }

    @Override
    public int getItemCount() {
        return mNicePlaces.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private ImageView mImage;
        private TextView mTitle;

        public Holder(@NonNull View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image);
            mTitle = itemView.findViewById(R.id.image_name);
        }
    }

}
