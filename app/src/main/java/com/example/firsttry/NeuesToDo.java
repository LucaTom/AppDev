package com.example.firsttry;

import android.content.Intent;
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
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class NeuesToDo extends AppCompatActivity implements View.OnClickListener {

    private EditText neuesToDo;
    private Spinner newDue;
    private Button btn;

    private FileHelper<Todo> fileHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neues_to_do);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.logo);

        neuesToDo = findViewById(R.id.newDesc);
        newDue = findViewById(R.id.newDue);
        btn = findViewById(R.id.btnAdd);
        fileHelper = new FileHelper<>(this, Todo.class);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnAdd:
                String todoEntered = neuesToDo.getText().toString();
                String duedateEntered = newDue.getSelectedItem().toString();

                Todo todo = new Todo();
                todo.done = false;
                todo.description = todoEntered;
                todo.due = duedateEntered;

                neuesToDo.setText("");

                Intent i = new Intent();
                i.putExtra("newtodo", todo);
                setResult(RESULT_OK, i);
                finish();
        }
    }

    /*@Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        items.remove(position);
        adapter.notifyDataSetChanged();

        Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
    }*/



}
