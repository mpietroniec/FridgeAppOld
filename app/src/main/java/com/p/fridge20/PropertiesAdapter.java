package com.p.fridge20;


import android.content.Context;
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

import java.util.ArrayList;

//1. Need to create insert class ViewHolder
public class PropertiesAdapter extends RecyclerView.Adapter<PropertiesAdapter.ViewHolder> {

    private ArrayList<Property> properties = new ArrayList<>();
    private Context context;

    public PropertiesAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.property_row, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.propertyName.setText(properties.get(position).getPropertyName());
        holder.property.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, properties.get(position).getPropertyName() + " Selected", Toast.LENGTH_SHORT).show();
            }
        });
        Glide.with(context)
                .asBitmap()
                .load(properties.get(position).getImageSrc())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return properties.size();
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView propertyName;
        private CardView property;
        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            propertyName = itemView.findViewById(R.id.property_name);
            property = itemView.findViewById(R.id.property);
            image = itemView.findViewById(R.id.image);
        }
    }
}
