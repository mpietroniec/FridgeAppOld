package com.p.fridge20.shoppingList;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.p.fridge20.R;
import com.p.fridge20.database.DatabaseHelper;

public class AddToShoppingList extends AppCompatActivity {

    EditText shoppingListProductName, shoppingListProductAmount, shoppingListShopName;
    Button shoppingListAddButton, shoppingListAddNextButton, shoppingListAddEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_shopping_list);

        shoppingListProductName = findViewById(R.id.shopping_list_add_name);
        shoppingListProductAmount = findViewById(R.id.shopping_list_add_amount);
        shoppingListShopName = findViewById(R.id.shopping_list_add_shop_name);

        shoppingListAddButton = findViewById(R.id.shopping_list_add_button);
        shoppingListAddNextButton = findViewById(R.id.shopping_list_add_next_btn);
        shoppingListAddEnd = findViewById(R.id.shopping_list_add_end);

        shoppingListAddButton.setOnClickListener(v -> {
            String name = shoppingListProductName.getText().toString().trim();
            String amount = shoppingListProductAmount.getText().toString().trim();
            String shopName = shoppingListShopName.getText().toString().trim();
            try {
                if (!name.isEmpty() || !shopName.isEmpty()) {
                    int dbAmount = Integer.parseInt(amount);
                    DatabaseHelper myDB = new DatabaseHelper(AddToShoppingList.this);
                    myDB.addProductToShoppingList(name, dbAmount, shopName);

                } else {
                    Toast.makeText(AddToShoppingList.this, "Podaj pozostałe dane", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(AddToShoppingList.this, "Podaj pozostałe dane", Toast.LENGTH_SHORT).show();
            }
        });

        shoppingListAddNextButton.setOnClickListener(v -> {
            Intent intent = new Intent(AddToShoppingList.this, AddToShoppingList.class);
            startActivity(intent);
            finish();
        });

        shoppingListAddEnd.setOnClickListener(v -> {
            Intent intent = new Intent(AddToShoppingList.this, ShoppingListActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
