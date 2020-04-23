package com.example.firsttry;

import android.os.Bundle;

import com.example.firsttry.ui.FileHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NeuesToDo extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private EditText neuesToDo;
    private Button btn;
    private ListView itemsList;

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neues_to_do);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.logo);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Funktion: Speichern", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        neuesToDo = findViewById(R.id.neues_todo);
        btn = findViewById(R.id.add_btn);
        itemsList = findViewById(R.id.itemsList);

        items = FileHelper.readData(this);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        itemsList.setAdapter(adapter);

        btn.setOnClickListener(this);
        itemsList.setOnItemClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.add_btn:
                String itemEntered = neuesToDo.getText().toString();
                adapter.add(itemEntered);
                neuesToDo.setText("");

                FileHelper.writeData(items, this);


                Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        items.remove(position);
        adapter.notifyDataSetChanged();

        Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
    }
}
