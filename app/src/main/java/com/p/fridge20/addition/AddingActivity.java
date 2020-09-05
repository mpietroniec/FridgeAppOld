package com.p.fridge20.addition;

import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.p.fridge20.R;

public class AddingActivity extends AppCompatActivity {

    EditText nameOfProduct, amountsOfProducts, priceOfProduct;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_activity);

        nameOfProduct = findViewById(R.id.edtTxtName);
        amountsOfProducts = findViewById(R.id.addAmount);
        priceOfProduct = findViewById(R.id.addPrice);
    }
}