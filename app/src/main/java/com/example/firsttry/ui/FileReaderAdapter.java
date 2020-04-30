package com.example.firsttry.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.example.firsttry.R;
import com.example.firsttry.serialize.FileHelper;
import com.example.firsttry.serialize.Todo;

import java.util.ArrayList;
import java.util.List;



public class FileReaderAdapter extends ArrayAdapter<Todo> {


    private static class ViewHolder {
        private CheckBox done;
        private TextView description;
        private Spinner due;
    }

    private FileHelper<Todo> fileHelper;



    public FileReaderAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Todo> todos) {
        super(context, resource, 0, todos);
        fileHelper = new FileHelper<>(context, Todo.class);
    }


    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.content_lvi_todoliste, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.done = convertView.findViewById(R.id.chxDone);
            viewHolder.description = convertView.findViewById(R.id.txtDescription);
            viewHolder.due = convertView.findViewById(R.id.newDue);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Todo item = getItem(position);

        if(item != null) {
            viewHolder.done.setChecked(item.done);
            viewHolder.description.setText(item.description);
            viewHolder.due.getSelectedItem();
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
