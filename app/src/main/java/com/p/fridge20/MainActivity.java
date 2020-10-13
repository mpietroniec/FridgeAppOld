package com.p.fridge20;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView propertiesRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        propertiesRecView = findViewById(R.id.propertiesRecView);

        ArrayList<Property> properties = new ArrayList<>();
        properties.add(new Property("Dodawanie produktów", R.drawable.plus,1));
        properties.add(new Property("Miesieczny bilans", R.drawable.money,2));
        properties.add(new Property("Lista zakupów", R.drawable.list,3));
        properties.add(new Property("Wyświetl produkty", R.drawable.read,4));

        PropertiesAdapter adapter = new PropertiesAdapter(this, properties);
        adapter.setProperties(properties);

        propertiesRecView.setAdapter(adapter);
        propertiesRecView.setLayoutManager(new GridLayoutManager(this,2));

    }
}
