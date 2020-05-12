package com.example.firsttry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Popupwindow extends Activity {

    private Button yes;
    private Button no;

    //How to create a Popup Window: https://www.youtube.com/watch?v=fn5OlqQuOCk

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_popupwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.7),(int)(height*.5));



        yes = findViewById(R.id.btnYes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //remove(getItem(position));
              //notifyDataSetChanged();
              //Log.i("Todo Übersicht","Todo deleted");
            }
        });


        // No-Button notwendig? --> man kann auch einfach außerhalb des Fensters drücken
        no = findViewById(R.id.btnNo);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Popupwindow.this, ToDoUebersichtActivity.class);
                startActivity(i);
            }
        });



    }
}
