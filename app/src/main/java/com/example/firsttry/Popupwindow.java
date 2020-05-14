package com.example.firsttry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.firsttry.serialize.Todo;
import com.example.firsttry.ui.FileReaderAdapter;

import java.io.File;

public class Popupwindow extends Activity {

    private Button yes;
    private Button no;

    //How to create a Popup Window: https://www.youtube.com/watch?v=fn5OlqQuOCk

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

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
            public void onClick(View v){

                Intent i = new Intent();
                Integer position = i.getIntExtra("index",0);
                i.putExtra("position", position);
                setResult(RESULT_OK, i);
                finish();
            }
        });

        no = findViewById(R.id.btnNo);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent();
                setResult(RESULT_CANCELED, i);
                finish();
            }
        });

    }







}

