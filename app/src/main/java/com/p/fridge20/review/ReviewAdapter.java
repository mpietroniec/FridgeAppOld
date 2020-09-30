package com.p.fridge20.review;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.p.fridge20.R;
import com.p.fridge20.productDetails.ProductDetails;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    Context context;
    Activity activity;
    ArrayList product_id, product_name, product_amount;

    public ReviewAdapter(Context context, Activity activity, ArrayList product_id, ArrayList product_name, ArrayList product_amount) {
        this.context = context;
        this.activity = activity;
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_amount = product_amount;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_review_row, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ReviewViewHolder holder, final int position) {
        holder.product_id_txt.setText(String.valueOf(product_id.get(position)));
        holder.product_name_txt.setText(String.valueOf(product_name.get(position)));
        holder.product_amount_txt.setText(String.valueOf(product_amount.get(position)));
        //UpdateActivity
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetails.class);
                intent.putExtra("id", String.valueOf(product_id.get(position)));
                intent.putExtra("product_name", String.valueOf(product_name.get(position)));
                intent.putExtra("product_amount", String.valueOf(product_amount.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return product_id.size();
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {

        TextView product_id_txt, product_name_txt, product_amount_txt;
        CardView mainLayout;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            product_id_txt = itemView.findViewById(R.id.product_id_txt);
            product_name_txt = itemView.findViewById(R.id.product_name_txt);
            product_amount_txt = itemView.findViewById(R.id.product_amount_txt);
            mainLayout = itemView.findViewById(R.id.reviewLayout);
        }
    }
}
