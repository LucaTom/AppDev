package com.example.firsttry;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.firsttry.serialize.FileHelper;
import com.example.firsttry.serialize.Todo;
import com.example.firsttry.ui.FileReaderAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ToDoUebersicht extends AppCompatActivity {

    private ListView itemsList;
    private ArrayList<Todo> items;
    private FileReaderAdapter adapter;
    private FileHelper<Todo> fileHelper;

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

        fileHelper = new FileHelper<>(this, Todo.class);
        items = fileHelper.readData();

        adapter = new FileReaderAdapter (this, R.layout.content_lvi_todoliste, items);
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
        items = fileHelper.readData();
        adapter = new FileReaderAdapter (this, R.layout.content_lvi_todoliste, items);
        itemsList.setAdapter(adapter);
    }
}
