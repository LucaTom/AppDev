package com.example.firsttry.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.example.firsttry.R;
import com.example.firsttry.ToDoEinzelansicht;
import com.example.firsttry.ToDoUebersichtActivity;
import com.example.firsttry.WochentageUebersichtActivity;
import com.example.firsttry.serialize.FileHelper;
import com.example.firsttry.serialize.Todo;

import java.util.ArrayList;
import java.util.List;



public class FileReaderAdapter extends ArrayAdapter<Todo> {

    private static class ViewHolder {
        private CheckBox done;
        private TextView description;
        private Spinner due;
       // private TextView duedate;

        private ImageButton edit;
        private ImageButton delete;
    }

    private FileHelper<Todo> fileHelper;

    public FileReaderAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Todo> todos) {
        super(context, resource, 0, todos);
        fileHelper = new FileHelper<>(context, Todo.class);
    }


    //https://www.youtube.com/watch?v=ZEEYYvVwJGY

    public View getView(final int position, View convertView, ViewGroup parent){
        final ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.content_lvi_todoliste, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.done = convertView.findViewById(R.id.chxDone);
            viewHolder.description = convertView.findViewById(R.id.txtDescription);
            viewHolder.due = convertView.findViewById(R.id.newDue);
           // viewHolder.duedate = convertView.findViewById(R.id.newDue);

            viewHolder.edit = convertView.findViewById(R.id.btnEdit);
            viewHolder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getContext(), ToDoEinzelansicht.class);
                    getContext().startActivity(i);
                }
            });

            viewHolder.delete = convertView.findViewById(R.id.btnDelete);
            viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    remove(getItem(position));
                    notifyDataSetChanged();

                    Log.i("Todo Übersicht","Todo deleted");
                }
            });

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Todo item = getItem(position);

        if(item != null) {
            viewHolder.done.setChecked(item.done);
            viewHolder.description.setText(item.description);
            viewHolder.due.getSelectedItem();
           // String date = (String) viewHolder.due.getSelectedItem();
           // viewHolder.duedate.setText(date);
        }

        return convertView;
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
