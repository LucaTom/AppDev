package com.example.firsttry;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.firsttry.ui.FileHelper;
import com.example.firsttry.ui.FileReaderAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ToDoUebersicht extends AppCompatActivity {

    private ListView itemsList;

    private ArrayList<String> items;
    private FileReaderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_uebersicht);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.logo);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ToDoUebersicht.this, NeuesToDo.class);
                startActivity(i);
            }
        });

        itemsList = findViewById(R.id.itemsList);

        items = FileHelper.readData(this);

        adapter = new FileReaderAdapter (this, android.R.layout.simple_list_item_1, items);
        itemsList.setAdapter(adapter);

        Button button_wochentageuebersicht = findViewById(R.id.button_wochentageuebersicht);
        button_wochentageuebersicht.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ToDoUebersicht.this, WochentageUebersicht.class);
                startActivity(i);
            }
        });

        Button button_todoeinzelansicht = findViewById(R.id.button_todoeinzelansicht);
        button_todoeinzelansicht.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ToDoUebersicht.this, ToDoEinzelansicht.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        items = FileHelper.readData(this);
        adapter = new FileReaderAdapter (this, android.R.layout.simple_list_item_1, items);
        itemsList.setAdapter(adapter);
    }
}
