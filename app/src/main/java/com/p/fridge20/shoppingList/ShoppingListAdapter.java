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
    ArrayList shopping_list_product_id, shopping_list_product_name, shopping_list_product_amount, shopping_list_shop_name;

    public ShoppingListAdapter(Context context, Activity shoppingListActivity, ArrayList shopping_list_product_id, ArrayList shopping_list_product_name, ArrayList shopping_list_product_amount, ArrayList shopping_list_shop_name) {
        this.context = context;
        this.shoppingListActivity = shoppingListActivity;
        this.shopping_list_product_id = shopping_list_product_id;
        this.shopping_list_product_name = shopping_list_product_name;
        this.shopping_list_product_amount = shopping_list_product_amount;
        this.shopping_list_shop_name = shopping_list_shop_name;
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
        holder.shoppingListNameTxt.setText(String.valueOf(shopping_list_product_name.get(position)));
        holder.shoppingListAmountTxt.setText(String.valueOf(shopping_list_product_amount.get(position)));
        holder.shoppingListShopNameTxt.setText(String.valueOf(shopping_list_shop_name.get(position)));
    }

    @Override
    public int getItemCount() {
        return shopping_list_product_name.size();
    }

    public class ShoppingListViewHolder extends RecyclerView.ViewHolder {

        TextView shoppingListNameTxt, shoppingListAmountTxt, shoppingListShopNameTxt;
        CardView shoppingListLayout;

        public ShoppingListViewHolder(@NonNull View itemView) {
            super(itemView);
            shoppingListNameTxt = itemView.findViewById(R.id.shopping_list_name_txt);
            shoppingListAmountTxt = itemView.findViewById(R.id.shopping_list_amount_txt);
            shoppingListShopNameTxt = itemView.findViewById(R.id.shopping_list_shop_name_txt);
            shoppingListLayout = itemView.findViewById(R.id.shopping_list_layout);
        }
    }
}
