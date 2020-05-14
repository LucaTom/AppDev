package com.example.firsttry;

import android.content.Intent;
import android.os.Bundle;

import com.example.firsttry.serialize.FileHelper;
import com.example.firsttry.serialize.Todo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ToDoEinzelansichtActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editName;
    private EditText editDetail;
    private Spinner editDue;
    private Button btnSave;

    private FileHelper<Todo> fileHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_einzelansicht);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.logo);


        editName =findViewById(R.id.editName);
        editDetail = findViewById(R.id.editDetail);
        editDue =findViewById(R.id.editDue);
        btnSave = findViewById(R.id.btnSave);

        fileHelper = new FileHelper<>(this, Todo.class);

        btnSave.setOnClickListener(this);

        Intent i = getIntent();
        String name = i.getStringExtra("description");
        String due  = i.getStringExtra("due");

        editName.setText(name);
        editDue.setSelection(due.indexOf(due));


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    switch(view.getId()){
                        case R.id.fab:
                    }

                Snackbar.make(view, "Funktion: Speichern", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnSave:
                String todoEdit = editName.getText().toString();
                String detailEdit = editDetail.getText().toString();
                String duedateEdit = editDue.getSelectedItem().toString();

                Todo todo = new Todo();
                todo.description = todoEdit;
                //todo.detail = detailEdit;
                //todo.due = duedateEdit;

                //editDetail.setText("");

                Intent i = new Intent();
                i.putExtra("changedtodo", todo);
                setResult(RESULT_OK, i);
                finish();
        }
    }
}
