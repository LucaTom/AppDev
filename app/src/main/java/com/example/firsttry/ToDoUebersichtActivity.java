package com.example.firsttry;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

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
    private Spinner filterDue;


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


        filterDue = findViewById(R.id.filterDue);
        String filter = filterDue.getSelectedItem().toString();
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
        }else if (requestCode == 19 && resultCode == RESULT_OK){
            //adapter.add((Todo) data.getStringExtra("changedtodo"));
            //adapter.notifyDataSetChanged();
            Log.d("WHHY", "WHHY");
        }
        else if (requestCode == 20 && resultCode == RESULT_OK){

                    Integer position = data.getIntExtra("position",0);
                    adapter.remove(adapter.getItem(position));
                    //adapter.remove(adapter.getItem(data.getIntExtra("position",0)));

                    adapter.notifyDataSetChanged();
                    Log.d("Position:", "deleted" + position);
                    Log.i("Todo Übersicht","Todo deleted");
                    Log.d("WHOOPSIE", "WHOOPSIE");
        }
    }


   @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(ToDoUebersichtActivity.this, ToDoEinzelansichtActivity.class);
        startActivity(i);

        Log.i("Todo Übersicht","Todo selected");
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}