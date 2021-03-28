package com.p.fridge20.addition;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.p.fridge20.MainActivity;
import com.p.fridge20.R;
import com.p.fridge20.database.DatabaseHelper;

public class AddingActivity extends AppCompatActivity {

    EditText nameOfProductEdit, amountsOfProductsEdit;
    Button addButton, nextAddButton, endAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_activity);

        nameOfProductEdit = findViewById(R.id.add_name);
        amountsOfProductsEdit = findViewById(R.id.add_amount);

        addButton = findViewById(R.id.add_button);
        nextAddButton = findViewById(R.id.add_next_btn);
        endAdd = findViewById(R.id.add_end);

        addButton.setOnClickListener(v -> {
            String nameOfProduct = nameOfProductEdit.getText().toString().trim();
            String amount = amountsOfProductsEdit.getText().toString().trim();
            try {
                if (!nameOfProduct.isEmpty()) {
                    int dbAmount = Integer.parseInt(amount);
                    DatabaseHelper myDB = new DatabaseHelper(AddingActivity.this);
                    myDB.addProductToFridge(nameOfProduct, dbAmount);
                } else {
                    Toast.makeText(AddingActivity.this, "Podaj nazwę produktu", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(AddingActivity.this, "Podaj ilość produktów", Toast.LENGTH_SHORT).show();
            }
        });

        nextAddButton.setOnClickListener(v -> {
            Intent intent = new Intent(AddingActivity.this, AddingActivity.class);
            startActivity(intent);
            finish();
        });

        endAdd.setOnClickListener(v -> {
            Intent intent = new Intent(AddingActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
