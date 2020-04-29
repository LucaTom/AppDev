package com.example.firsttry;

import android.os.Bundle;

import com.example.firsttry.serialize.FileHelper;
import com.example.firsttry.serialize.Todo;
import com.example.firsttry.ui.FileReaderAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NeuesToDo extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private EditText neuesToDo;
    private Button btn;
    private ListView itemsList;

    private ArrayList<Todo> items;
    private FileReaderAdapter adapter;
    private FileHelper<Todo> fileHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neues_to_do);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.logo);

        neuesToDo = findViewById(R.id.neues_todo);
        btn = findViewById(R.id.add_btn);
        itemsList = findViewById(R.id.itemsList);
        fileHelper = new FileHelper<>(this, Todo.class);

        items = fileHelper.readData();

        adapter = new FileReaderAdapter(this, android.R.layout.simple_list_item_1, items);
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

                Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        items.remove(position);
        adapter.notifyDataSetChanged();

        Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
    }



}
