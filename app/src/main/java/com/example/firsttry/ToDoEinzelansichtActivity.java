package com.example.firsttry;

import android.content.Intent;
import android.os.Bundle;

import com.example.firsttry.serialize.Todo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ToDoEinzelansichtActivity extends AppCompatActivity {

    private EditText editName;
    private Spinner editDue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_einzelansicht);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.logo);


        editName =findViewById(R.id.editName);
        editDue=findViewById(R.id.editDue);

        Intent i = getIntent();
        String name = i.getStringExtra("description");
        String due  = i.getStringExtra("due");

        editName.setText(name);
        //editDue.setText(due);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    switch(view.getId()){
                        case R.id.fab:

                            String edittodoEntered = editName.getText().toString();
                            String editduedateEntered = editDue.getSelectedItem().toString();

                            Todo todo = new Todo();
                            todo.description = edittodoEntered;
                            todo.due = editduedateEntered;

                            Intent i = new Intent();
                            i.putExtra("changes", todo);
                            setResult(RESULT_OK, i);
                            finish();
                    }


                Snackbar.make(view, "Funktion: Speichern", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
