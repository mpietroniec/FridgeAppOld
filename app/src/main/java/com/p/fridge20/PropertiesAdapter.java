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
import com.p.fridge20.review.ReviewActivity;
import com.p.fridge20.shoppingList.ShoppingListActivity;

import java.util.ArrayList;

//1. Need to create inner class ViewHolder
public class PropertiesAdapter extends RecyclerView.Adapter<PropertiesAdapter.PropertiesAdapterViewHolder> {

    private ArrayList<Property> properties;
    private final Context context;

    public PropertiesAdapter(Context context, ArrayList<Property> properties) {
        this.context = context;
        this.properties = properties;
    }

    @NonNull
    @Override
    public PropertiesAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.property_row, parent, false);
        return new PropertiesAdapterViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull PropertiesAdapterViewHolder holder, int position) {
        holder.getPropertyName().setText(properties.get(position).getPropertyName());
        Glide.with(context)
                .asBitmap()
                .load(properties.get(position).getImageSrc())
                .into(holder.getImage());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                switch (position) {
                    case 0:
                        Intent i0 = new Intent(context, AddingActivity.class);
                        context.startActivity(i0);
                        break;
                    case 1:
                        Intent i1 = new Intent(context, ShoppingListActivity.class);
                        context.startActivity(i1);
                        break;
                    case 2:
                        Intent i2 = new Intent(context, ReviewActivity.class);
                        context.startActivity(i2);
                        break;
                }
                Toast.makeText(context, properties.get(position).getPropertyName(), Toast.LENGTH_SHORT).show();
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

    public static class PropertiesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView propertyName;
        private CardView property;
        private ImageView image;
        ItemClickListener itemClickListener;

        public PropertiesAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            propertyName = itemView.findViewById(R.id.property_name);
            property = itemView.findViewById(R.id.property);
            image = itemView.findViewById(R.id.main_property_image);
            itemView.setOnClickListener(this);
        }

        public TextView getPropertyName() {
            return propertyName;
        }

        public void setPropertyName(TextView propertyName) {
            this.propertyName = propertyName;
        }

        public CardView getProperty() {
            return property;
        }

        public void setProperty(CardView property) {
            this.property = property;
        }

        public ImageView getImage() {
            return image;
        }

        public void setImage(ImageView image) {
            this.image = image;
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
