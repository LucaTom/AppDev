package com.example.firsttry.ui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.PreferenceManager;

import com.example.firsttry.NeuesToDoActivity;
import com.example.firsttry.Popupwindow;
import com.example.firsttry.R;
import com.example.firsttry.ToDoEinzelansichtActivity;
import com.example.firsttry.ToDoUebersichtActivity;
import com.example.firsttry.serialize.FileHelper;
import com.example.firsttry.serialize.Todo;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static androidx.core.app.ActivityCompat.startActivityForResult;


public class FileReaderAdapter extends ArrayAdapter<Todo> {

    private static class ViewHolder {
        private CheckBox done;
        private TextView description;
        private TextView due;

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

            //Checkbox:
            viewHolder.done = convertView.findViewById(R.id.chxDone);
            viewHolder.done.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if (viewHolder.done.isChecked()){
                        viewHolder.done.setVisibility(View.INVISIBLE);
                    }
                    notifyDataSetChanged();

                }
            });

            //Todoname:
            viewHolder.description = convertView.findViewById(R.id.txtDescription);

            //Spinner:
            viewHolder.due = convertView.findViewById(R.id.txtDue);

            //Button:
            viewHolder.edit = convertView.findViewById(R.id.btnEdit);
            viewHolder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getContext(), ToDoEinzelansichtActivity.class);
                    i.putExtra("description",viewHolder.description.toString());
                    i.putExtra("due",viewHolder.due.toString());
                    i.putExtra("done",viewHolder.done.toString());
                    getContext().startActivity(i);

                }
            });

            viewHolder.delete = convertView.findViewById(R.id.btnDelete);
            viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getContext().startActivity(new Intent (getContext(), Popupwindow.class));
                    if (Popupwindow.RESULT_OK == RESULT_OK){
                        remove(getItem(position));
                        notifyDataSetChanged();

                        Log.i("Todo Übersicht","Todo deleted");
                    }
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
            viewHolder.due.setText(item.due);
        }
        return convertView;
    }

    private void startActivityForResult(Intent i) {
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