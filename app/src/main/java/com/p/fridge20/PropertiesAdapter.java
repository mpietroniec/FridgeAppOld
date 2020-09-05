package com.p.fridge20;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.p.fridge20.addition.AddingActivity;

import java.util.ArrayList;

//1. Need to create inner class ViewHolder
public class PropertiesAdapter extends RecyclerView.Adapter<PropertiesAdapter.ViewHolder> {

    private ArrayList<Property> properties;
    private Context context;

    public PropertiesAdapter(Context context, ArrayList<Property> properties) {
        this.context = context;
        this.properties = properties;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.property_row, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.propertyName.setText(properties.get(position).getPropertyName());

        Glide.with(context)
                .asBitmap()
                .load(properties.get(position).getImageSrc())
                .into(holder.image);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                Intent intent = new Intent(context, AddingActivity.class);
                context.startActivity(intent);
                Toast.makeText(context, properties.get(position).getPropertyName() + " Selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return properties.size();
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView propertyName;
        private CardView property;
        private ImageView image;
        ItemClickListener itemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            propertyName = itemView.findViewById(R.id.property_name);
            property = itemView.findViewById(R.id.property);
            image = itemView.findViewById(R.id.image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClickListener(v, getLayoutPosition());
        }

        public void setItemClickListener(ItemClickListener ic) {
            this.itemClickListener = ic;
        }
    }
}