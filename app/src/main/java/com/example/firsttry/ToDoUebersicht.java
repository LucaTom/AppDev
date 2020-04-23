package com.example.firsttry;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ToDoUebersicht extends AppCompatActivity {


    private ListView itemsList;

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

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


        //Button button_neuestodo = findViewById(R.id.button_neuestodo);
        //button_neuestodo.setOnClickListener(new View.OnClickListener(){


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

}
