package com.p.fridge20.shoppingList;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.p.fridge20.R;
import com.p.fridge20.database.DatabaseHelper;

public class AddToShoppingList extends AppCompatActivity {

    EditText shoppingListProductName, shoppingListProductAmount;
    Button shoppingListAddButton, shoppingListAddNextButton, shoppingListAddEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_shopping_list);

        shoppingListProductName = findViewById(R.id.shopping_list_add_name);
        shoppingListProductAmount = findViewById(R.id.shopping_list_add_amount);
        shoppingListAddButton = findViewById(R.id.shopping_list_add_button);
        shoppingListAddNextButton = findViewById(R.id.shopping_list_add_next_btn);
        shoppingListAddEnd = findViewById(R.id.shopping_list_add_end);

        shoppingListAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper myDB = new DatabaseHelper(AddToShoppingList.this);
                myDB.addProductToShoppingList(shoppingListProductName.getText().toString().trim(),
                        Integer.parseInt(shoppingListProductAmount.getText().toString().trim()));
            }
        });

        shoppingListAddNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddToShoppingList.this, AddToShoppingList.class);
                startActivity(intent);
                finish();
            }
        });

        shoppingListAddEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddToShoppingList.this, ShoppingListActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
