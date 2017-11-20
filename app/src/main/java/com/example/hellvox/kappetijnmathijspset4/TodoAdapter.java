package com.example.hellvox.kappetijnmathijspset4;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class TodoAdapter extends ResourceCursorAdapter {

    public TodoAdapter(Context context, Cursor cursor, int flags) {
        super(context, R.layout.row_todo, cursor, flags);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView name = view.findViewById(R.id.textView5);
        TextView idd = view.findViewById(R.id.id);
        CheckBox box = (CheckBox) view.findViewById(R.id.check);



        String title = cursor.getString(cursor.getColumnIndex( "title"));
        String id  = cursor.getString(cursor.getColumnIndex( "_id"));
        int boxValue = cursor.getInt(cursor.getColumnIndex( "complete"));
        name.setTextColor(Color.BLACK);
        name.setTextSize(16);
        name.setText(title);
        idd.setText(id);
        if (boxValue == 1) {
            box.setChecked(true);
            name.setTextColor(Color.GRAY);
            name.setTextSize(14);
        }
    }
}
