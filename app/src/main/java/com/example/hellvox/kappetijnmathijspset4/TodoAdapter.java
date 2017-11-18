package com.example.hellvox.kappetijnmathijspset4;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

/**
 * Created by HellVox on 18-11-2017.
 */

public class TodoAdapter extends ResourceCursorAdapter {

    private Context mContext;
    int mResource;

    public TodoAdapter(Context context, int resource, Cursor cursor) {
        super(context, resource, cursor);
        mContext = context;
        mResource = resource;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        int temp = Cursor.getColumnIndex("title");
        int checked = Cursor.getInt(temp);


        TextView name = (TextView) view.findViewById(R.id.textView5);
        CheckBox box = (CheckBox) view.findViewById(R.id.check);

    }
}
