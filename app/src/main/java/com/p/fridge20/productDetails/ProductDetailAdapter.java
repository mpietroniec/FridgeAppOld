package com.p.fridge20.productDetails;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.p.fridge20.R;

import java.util.ArrayList;

public class ProductDetailAdapter extends RecyclerView.Adapter<ProductDetailAdapter.UpdateViewHolder> {
    Context context;
    Activity  activity;
    ArrayList product_id, product_name, product_amount;

    @Override
    public UpdateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductDetailAdapter.UpdateViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class UpdateViewHolder extends RecyclerView.ViewHolder{
        TextView product_id_txt, product_name_txt, product_amount_txt;
        CardView detailLayout;
        public UpdateViewHolder(@NonNull View itemView) {
            super(itemView);
            //R.id pobierane z ReviewRowActivity
            product_id_txt = itemView.findViewById(R.id.product_id_txt);
            product_name_txt = itemView.findViewById(R.id.product_name_txt);
            product_amount_txt = itemView.findViewById(R.id.product_amount_txt);
            detailLayout = itemView.findViewById(R.id.activity_update);
        }
    }
}

