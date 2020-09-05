package com.p.fridge20.subtraction;

import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.p.fridge20.R;

public class SubtractActivity extends AppCompatActivity {

    EditText nameOfProduct, amountsOfProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.substract_products);

        nameOfProduct = findViewById(R.id.edtTxtName);
        amountsOfProducts = findViewById(R.id.subtractAmount);
    }
}