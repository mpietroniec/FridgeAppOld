package com.p.fridge20.productDetails;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.p.fridge20.R;
import com.p.fridge20.database.DatabaseHelper;

public class ProductDetails extends AppCompatActivity {

    TextView name_input;
    EditText amount_input;
    Button update_button, delete_button;

    String id;
    String product_name, product_amount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        name_input = findViewById(R.id.name_input_update);
        amount_input = findViewById(R.id.amount_input_update);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentData();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Zmień lub usuń produkt");
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(ProductDetails.this);
                product_amount = amount_input.getText().toString().trim();
                db.updateData(id,product_name, product_amount);
            }
        });
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id")
                && getIntent().hasExtra("product_name")
                && getIntent().hasExtra("product_amount")) {

            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            product_name = getIntent().getStringExtra("product_name");
            product_amount = getIntent().getStringExtra("product_amount");

            //Setting Intent Data
            name_input.setText(product_name);
            amount_input.setText(product_amount);
        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
}