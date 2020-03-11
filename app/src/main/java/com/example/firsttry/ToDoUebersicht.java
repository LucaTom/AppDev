package com.example.firsttry;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class ToDoUebersicht extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_uebersicht);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.logo);

        Button button_neuestodo = findViewById(R.id.button_neuestodo);
        button_neuestodo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ToDoUebersicht.this, NeuesToDo.class);
                startActivity(i);
            }
        });

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
