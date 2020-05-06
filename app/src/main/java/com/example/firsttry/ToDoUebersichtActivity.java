package com.example.firsttry;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.firsttry.serialize.FileHelper;
import com.example.firsttry.serialize.Todo;
import com.example.firsttry.ui.FileReaderAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ToDoUebersichtActivity extends AppCompatActivity implements android.widget.AdapterView.OnItemSelectedListener {

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
                Intent i = new Intent(ToDoUebersichtActivity.this, NeuesToDoActivity.class);
                startActivityForResult(i, 21);
            }
        });

        itemsList = findViewById(R.id.itemsList);
        itemsList.setOnItemSelectedListener(this);

        fileHelper = new FileHelper<>(this, Todo.class);
        items = fileHelper.readData();

        adapter = new FileReaderAdapter (this, R.layout.content_lvi_todoliste, items);
        itemsList.setAdapter(adapter);

        Button btnFilter = findViewById(R.id.btnFilter);
        btnFilter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ToDoUebersichtActivity.this, WochentageUebersichtActivity.class);
                startActivity(i);
            }

        });



        /*Button btnEdit = findViewById(R.id.btnEdit);
        BtnEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ToDoUebersichtActivity.this, ToDoEinzelansicht.class);
                startActivity(i);
            }
        });

         */
    }

    @Override
    protected void onResume() {
        super.onResume();
        items = fileHelper.readData();
        adapter = new FileReaderAdapter (this, R.layout.content_lvi_todoliste, items);
        itemsList.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 21 && resultCode == RESULT_OK) {
            adapter.add((Todo) data.getParcelableExtra("newtodo"));
            adapter.notifyDataSetChanged();
        }
    }

    /*@Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        items.remove(position);
        adapter.notifyDataSetChanged();

        Log.i("Todo Übersicht","Todo deleted");
    }
    */

   @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(ToDoUebersichtActivity.this, ToDoEinzelansicht.class);
        startActivity(i);


        Log.i("Todo Übersicht","Todo selected");
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}