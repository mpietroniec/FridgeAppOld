package com.p.fridge20.shoppingList;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.p.fridge20.R;
import com.p.fridge20.database.DatabaseHelper;

import java.util.ArrayList;

public class ShoppingListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ShoppingListAdapter shoppingListAdapter;
    FloatingActionButton add_button_shopping_list;

    DatabaseHelper database;
    ArrayList<String> shopping_list_product_id, shopping_list_product_name, shopping_list_product_amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        recyclerView = findViewById(R.id.shoppingListRecyclerView);
        add_button_shopping_list = findViewById(R.id.add_button_shopping_list);
        add_button_shopping_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingListActivity.this, AddToShoppingList.class);
                startActivityForResult(intent, 1);
            }
        });

        database = new DatabaseHelper(ShoppingListActivity.this);
        shopping_list_product_id = new ArrayList<>();
        shopping_list_product_name = new ArrayList<>();
        shopping_list_product_amount = new ArrayList<>();

        shoppingListDataInArrays();
        shoppingListAdapter = new ShoppingListAdapter(ShoppingListActivity.this, this, shopping_list_product_id, shopping_list_product_name, shopping_list_product_amount);
        recyclerView.setAdapter(shoppingListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ShoppingListActivity.this));
    }

    void shoppingListDataInArrays() {
        Cursor cursor = database.readAllDataFromShoppingList();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                shopping_list_product_id.add(cursor.getString(0));
                shopping_list_product_name.add(cursor.getString(1));
                shopping_list_product_amount.add(cursor.getString(2));
            }
        }
    }

    //Make menu in layout
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_delete, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Make menu clickable and add method to this
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete_all) {
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

     void confirmDialog(){
         AlertDialog.Builder builder = new AlertDialog.Builder(this);
         builder.setTitle("Delete all?");
         builder.setMessage("Are you sure you want to delete all Data?");
         builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 DatabaseHelper myDB = new DatabaseHelper(ShoppingListActivity.this);
                 myDB.deleteAllDataFromShoppingList();
                 //Refresh Activity
                 Intent intent = new Intent(ShoppingListActivity.this, ShoppingListActivity.class);
                 startActivity(intent);
                 finish();
             }
         });
         builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {

             }
         });
         builder.create().show();
     }

}
