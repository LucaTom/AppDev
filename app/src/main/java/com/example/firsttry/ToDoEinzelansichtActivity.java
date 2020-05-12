package com.example.firsttry;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class ToDoEinzelansichtActivity extends AppCompatActivity {

    private String editName;
    private String editDetail;
    private Spinner editDue;
    private CheckBox editDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_einzelansicht);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.logo);

        /*
        editName = findViewById(R.id.txtDescription).toString();
        editDetail = findViewById(R.id.txtDescription).toString();
        editDue = findViewById(R.id.txtDue);
        editDone = findViewById(R.id.txtStatus);

         */

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Funktion: Speichern", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
