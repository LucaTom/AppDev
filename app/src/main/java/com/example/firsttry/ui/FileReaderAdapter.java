package com.example.firsttry.ui;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.example.firsttry.serialize.FileHelper;
import com.example.firsttry.serialize.Jsonable;
import com.example.firsttry.serialize.Todo;

import java.util.ArrayList;
import java.util.List;



public class FileReaderAdapter<Todo extends Jsonable> extends ArrayAdapter<Todo> {


    private ArrayList<Todo> items;
    private FileHelper<Todo> fileHelper;

    public FileReaderAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Todo> Todo) {
        super(context, resource, 0, Todo);
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();

        ArrayList<Todo> items = new ArrayList<>();

        for (int i=0; i < super.getCount(); i ++) {
            items.add(getItem(i));
        }
        fileHelper.writeData(items);
    }

}
