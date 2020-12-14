package com.p.fridge20;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView propertiesRecView = findViewById(R.id.propertiesRecView);

        ArrayList<Property> properties = new ArrayList<>();
        properties.add(new Property("Dodawanie produktów", R.drawable.plus, 1));
        properties.add(new Property("Lista zakupów", R.drawable.list, 2));
        properties.add(new Property("Wyświetl produkty", R.drawable.read, 3));

        PropertiesAdapter adapter = new PropertiesAdapter(this, properties);
        adapter.setProperties(properties);

        propertiesRecView.setAdapter(adapter);
        propertiesRecView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }
}
