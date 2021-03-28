package com.p.fridge20.productDetails;

import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.p.fridge20.R;
import com.p.fridge20.database.DatabaseHelper;

public class ProductDetails extends AppCompatActivity {

    TextView detailsProductNameOutput;
    EditText detailsAmountInput;
    Button detailsUpdateButton, detailsDeleteButton;

    String id;
    String product_name, product_amount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        detailsProductNameOutput = findViewById(R.id.name_input_update);
        detailsAmountInput = findViewById(R.id.amount_input_update);
        detailsUpdateButton = findViewById(R.id.update_button);
        detailsDeleteButton = findViewById(R.id.delete_button);

        getAndSetIntentData();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Zmień lub usuń produkt");
        }

        detailsUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(ProductDetails.this);
                product_amount = detailsAmountInput.getText().toString().trim();
                db.updateData(id, product_name, product_amount);
            }
        });
        detailsDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmDialog();
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
            detailsProductNameOutput.setText(product_name);
            detailsAmountInput.setText(product_amount);
        } else {
            Toast.makeText(this, "Brak danych", Toast.LENGTH_SHORT).show();
        }
    }

    void ConfirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Usunąć " + product_name + " ?");
        builder.setMessage("Czy na pewno usunąć " + product_name + " ?");
        builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                DatabaseHelper databaseHelper = new DatabaseHelper(ProductDetails.this);
                databaseHelper.deleteOneRowFromFridge(id);
            }
        });
        builder.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
            }
        });
        builder.create().show();
    }
}
