package com.p.fridge20.review;

import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.p.fridge20.R;
import com.p.fridge20.addition.AddingActivity;
import com.p.fridge20.database.DatabaseHelper;
import com.p.fridge20.shoppingList.AddToShoppingList;

import java.util.ArrayList;

public class ReviewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseHelper database;
    ArrayList<String> productId, productName, productAmount;
    ReviewAdapter reviewAdapter;
    FloatingActionButton btnAddProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        recyclerView = findViewById(R.id.propertiesRecView);

        btnAddProduct = findViewById(R.id.btn_add_product);
        btnAddProduct.setOnClickListener(v -> {
            Intent intent = new Intent(ReviewActivity.this, AddingActivity.class);
            startActivity(intent);
        });

        database = new DatabaseHelper(ReviewActivity.this);
        productId = new ArrayList<>();
        productName = new ArrayList<>();
        productAmount = new ArrayList<>();

        storeDataInArrays();
        reviewAdapter = new ReviewAdapter(ReviewActivity.this, this, productId, productName, productAmount);
        recyclerView.setAdapter(reviewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ReviewActivity.this));
    }

    void storeDataInArrays() {
        Cursor cursor = database.readAllDataFromFridge();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Brak danych", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                productId.add(cursor.getString(0));
                productName.add(cursor.getString(1));
                productAmount.add(cursor.getString(2));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_review, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(ReviewActivity.this, AddToShoppingList.class);
        startActivity(intent);
        finish();
        return super.onOptionsItemSelected(item);
    }
}
