package com.p.fridge20;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView propertyName;



    private CardView property;
    private ImageView image;
    ItemClickListener itemClickListener;

    public ViewHolder(@NonNull View itemView) {
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
