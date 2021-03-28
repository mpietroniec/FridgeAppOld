package com.p.fridge20.shoppingList;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.p.fridge20.R;
import com.p.fridge20.database.DatabaseHelper;

import java.util.ArrayList;

public class ShoppingListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ShoppingListAdapter shoppingListAdapter;
    FloatingActionButton btnAddToShoppingList;

    DatabaseHelper database;
    ArrayList<String> shoppingListProductId, shoppingListProductName, shoppingListProductAmount, shoppingListShopName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        recyclerView = findViewById(R.id.shoppingListRecyclerView);
        btnAddToShoppingList = findViewById(R.id.add_button_shopping_list);
        btnAddToShoppingList.setOnClickListener(v -> {
            Intent intent = new Intent(ShoppingListActivity.this, AddToShoppingList.class);
            startActivityForResult(intent, 1);
        });

        database = new DatabaseHelper(ShoppingListActivity.this);
        shoppingListProductId = new ArrayList<>();
        shoppingListProductName = new ArrayList<>();
        shoppingListProductAmount = new ArrayList<>();
        shoppingListShopName = new ArrayList<>();

        shoppingListDataInArrays();
        shoppingListAdapter = new ShoppingListAdapter(ShoppingListActivity.this, this, shoppingListProductId, shoppingListProductName, shoppingListProductAmount, shoppingListShopName);
        recyclerView.setAdapter(shoppingListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ShoppingListActivity.this));
    }

    void shoppingListDataInArrays() {
        Cursor cursor = database.readAllDataFromShoppingList();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Brak produktów na liście zakupów", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                shoppingListProductId.add(cursor.getString(0));
                shoppingListProductName.add(cursor.getString(1));
                shoppingListProductAmount.add(cursor.getString(2));
                shoppingListShopName.add(cursor.getString(3));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_delete, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete_all) {
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Usunąć wszystko?");
        builder.setMessage("Czy na pewno usunąć całą liste zakupów?");
        builder.setPositiveButton("Tak", (dialog, which) -> {
            DatabaseHelper myDB = new DatabaseHelper(ShoppingListActivity.this);
            myDB.deleteAllDataFromShoppingList();
            //Refresh Activity
            Intent intent = new Intent(ShoppingListActivity.this, ShoppingListActivity.class);
            startActivity(intent);
            finish();
        });
        builder.setNegativeButton("Nie", (dialog, which) -> {
        });
        builder.create().show();
    }
}