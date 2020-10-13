package com.p.fridge20.shoppingList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.p.fridge20.R;

import java.util.ArrayList;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ShoppingListViewHolder> {

    Context context;
    Activity shoppingListActivity;
    ArrayList shopping_list_product_id, shopping_list_product_name, shopping_list_product_amount;

    public ShoppingListAdapter(Context context, Activity shoppingListActivity, ArrayList shopping_list_product_id, ArrayList shopping_list_product_name, ArrayList shopping_list_product_amount) {
        this.context = context;
        this.shoppingListActivity = shoppingListActivity;
        this.shopping_list_product_id = shopping_list_product_id;
        this.shopping_list_product_name = shopping_list_product_name;
        this.shopping_list_product_amount = shopping_list_product_amount;
    }

    @NonNull
    @Override
    public ShoppingListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_shopping_list_row, parent, false);
        return new ShoppingListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingListAdapter.ShoppingListViewHolder holder, final int position) {
        holder.shopping_list_name_txt.setText(String.valueOf(shopping_list_product_name.get(position)));
        holder.shopping_list_amount_txt.setText(String.valueOf(shopping_list_product_amount.get(position)));
    }

    @Override
    public int getItemCount() {
        return shopping_list_product_name.size();
    }

    public class ShoppingListViewHolder extends RecyclerView.ViewHolder {

        TextView shopping_list_name_txt, shopping_list_amount_txt;
        CardView shopping_list_layout;

        public ShoppingListViewHolder(@NonNull View itemView) {
            super(itemView);
            shopping_list_name_txt = itemView.findViewById(R.id.shopping_list_name_txt);
            shopping_list_amount_txt = itemView.findViewById(R.id.shopping_list_amount_txt);
            shopping_list_layout = itemView.findViewById(R.id.shopping_list_layout);
        }
    }
}
