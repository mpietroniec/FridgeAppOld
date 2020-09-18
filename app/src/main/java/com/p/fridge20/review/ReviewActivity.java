package com.p.fridge20.review;

import android.database.Cursor;
import android.view.MenuInflater;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.p.fridge20.R;
import com.p.fridge20.database.DatabaseHelper;

import java.util.ArrayList;

public class ReviewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseHelper database;
    ArrayList<String> product_id, product_name,  product_amount;
    ReviewAdapter reviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        recyclerView = findViewById(R.id.propertiesRecView);

        database = new DatabaseHelper(ReviewActivity.this);
        product_id = new ArrayList<>();
        product_name = new ArrayList<>();
        product_amount = new ArrayList<>();

        storeDataInArrays();
        reviewAdapter = new ReviewAdapter(this,  product_id, product_name, product_amount);
        recyclerView.setAdapter(reviewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ReviewActivity.this));

    }

    void storeDataInArrays() {
        Cursor cursor = database.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                product_id.add(cursor.getString(0));
                product_name.add(cursor.getString(1));
                product_amount.add(cursor.getString(2));
            }
        }
    }


}