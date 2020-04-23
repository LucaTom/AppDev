package com.example.firsttry.ui;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileReaderAdapter extends ArrayAdapter<String> {

    public FileReaderAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<String> objects) {
        super(context, resource, 0, objects);

    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();

        ArrayList<String> items = new ArrayList<>();
        for (int i=0; i < super.getCount(); i ++) {
            items.add(getItem(i));
        }
        FileHelper.writeData(items, getContext());
    }

}
