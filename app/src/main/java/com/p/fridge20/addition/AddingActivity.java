package com.p.fridge20.addition;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.p.fridge20.R;
import com.p.fridge20.database.DatabaseHelper;

public class AddingActivity extends AppCompatActivity {

    EditText nameOfProduct, amountsOfProducts, priceOfProduct;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_activity);

        nameOfProduct = findViewById(R.id.add_name);
        amountsOfProducts = findViewById(R.id.add_amount);
        priceOfProduct = findViewById(R.id.add_price);
        addButton = findViewById(R.id.add_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper myDB = new DatabaseHelper(AddingActivity.this);
                myDB.addProduct(nameOfProduct.getText().toString().trim(),
                        Integer.parseInt(amountsOfProducts.getText().toString().trim())/*,
                        Float.parseFloat(priceOfProduct.getText().toString().trim())*/);
            }
        });
    }
}