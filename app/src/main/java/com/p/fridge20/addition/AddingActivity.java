package com.p.fridge20.addition;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.p.fridge20.MainActivity;
import com.p.fridge20.R;
import com.p.fridge20.database.DatabaseHelper;

public class AddingActivity extends AppCompatActivity {

    EditText nameOfProduct, amountsOfProducts, priceOfProduct;
    Button addButton, nextAddButton, endAdd;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_activity);

        nameOfProduct = findViewById(R.id.add_name);
        amountsOfProducts = findViewById(R.id.add_amount);
        priceOfProduct = findViewById(R.id.add_price);
        addButton = findViewById(R.id.add_button);
        nextAddButton = findViewById(R.id.add_next_btn);
        endAdd = findViewById(R.id.add_end);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DatabaseHelper myDB = new DatabaseHelper(AddingActivity.this);
                    myDB.addProductToFridge(nameOfProduct.getText().toString().trim(),
                            Integer.parseInt(amountsOfProducts.getText().toString().trim())/*,
                            Float.parseFloat(priceOfProduct.getText().toString().trim())*/);
                } catch (NumberFormatException | NullPointerException e) {
                    Toast.makeText(context, "Podaj dane produktu", Toast.LENGTH_SHORT).show();
                }
            }
        });

        nextAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddingActivity.this, AddingActivity.class);
                startActivity(intent);
                finish();
            }
        });

        endAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
