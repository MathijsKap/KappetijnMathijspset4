package com.example.hellvox.kappetijnmathijspset4;

import android.content.Context;
import android.database.Cursor;
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
        CheckBox box = (CheckBox) view.findViewById(R.id.check);



        String title = cursor.getString(cursor.getColumnIndex( "title"));
        name.setText(title);

    }
}
