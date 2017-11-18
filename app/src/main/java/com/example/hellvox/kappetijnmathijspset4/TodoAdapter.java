package com.example.hellvox.kappetijnmathijspset4;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class TodoAdapter extends CursorAdapter {

    private LayoutInflater cursorInflater;


    public TodoAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
        cursorInflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView name = view.findViewById(R.id.textView5);
        String title = cursor.getString( cursor.getColumnIndex( "todos.title"));
        name.setText(title);
        //CheckBox box = (CheckBox) view.findViewById(R.id.check);
    }

    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return cursorInflater.inflate(R.layout.row_todo, parent, false);
    }
}
