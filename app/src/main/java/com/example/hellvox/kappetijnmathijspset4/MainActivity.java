package com.example.hellvox.kappetijnmathijspset4;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static Context context;
    static TodoAdapter adapter;
    ListView todoList;
    Cursor mCursor;
    static TodoDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity.context = getApplicationContext();

        Button button = (Button) findViewById(R.id.enterButton);
        CheckBox box = (CheckBox) findViewById(R.id.check);
        todoList = (ListView) findViewById(R.id.todoList);


        db = TodoDatabase.getInstance(getApplicationContext());
        mCursor = db.selectAll();
        adapter = new TodoAdapter(getApplicationContext(), mCursor, 0);
        todoList.setAdapter(adapter);


        button.setOnClickListener(new add());
        todoList.setOnItemClickListener(new GoButtonClickListener());
        todoList.setOnItemLongClickListener(new GoButtonLongClickListener());
    }

    public void addItem(String title, int complete) {
        db.insertToDo(title, complete);
    }

    private static void updateData() {
        adapter.swapCursor(db.selectAll());
    }


    private class add implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            EditText enterbox = (EditText) findViewById(R.id.enterText);
            String title = enterbox.getText().toString();
            if (!title.equals("")) {
                addItem(title, 0);
                enterbox.setText("");
                Toast.makeText(getApplicationContext(), "Added!", Toast.LENGTH_SHORT).show();
                updateData();
                hideSoftKeyboard(MainActivity.this);
            } else Toast.makeText(getApplicationContext(), "Nothing to add!", Toast.LENGTH_SHORT).show();
        }
    }

    private class GoButtonLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            LinearLayout item = (LinearLayout) view;
            TextView idHidden = (TextView) item.getChildAt(0);
            db.delete(Long.parseLong(idHidden.getText().toString()));
            Toast.makeText(getApplicationContext(), "Deleted!", Toast.LENGTH_SHORT).show();
            updateData();
            return true;
        }
    }

    private static class GoButtonClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            LinearLayout item = (LinearLayout) view;
            TextView idHidden = (TextView) item.getChildAt(0);
            CheckBox box = (CheckBox) item.getChildAt(1);
            if (!box.isChecked()) {
                db.update(Long.parseLong(idHidden.getText().toString()),1);
                box.setChecked(true);
                Toast.makeText(MainActivity.getAppContext(), "Checked!", Toast.LENGTH_SHORT).show();
                updateData();
            } else if (box.isChecked()) {
                db.update(Long.parseLong(idHidden.getText().toString()),0);
                box.setChecked(false);
                Toast.makeText(MainActivity.getAppContext(), "Unchecked!", Toast.LENGTH_SHORT).show();
                updateData();
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static class buttonlistener implements View.OnClickListener {
        public void onClick(View v) {
            LinearLayout items = (LinearLayout) v.getParent();
            TextView Hidden = (TextView) items.getChildAt(0);
            CheckBox boxed = (CheckBox) items.getChildAt(1);
            if (boxed.isChecked()) {
                db.update(Long.parseLong(Hidden.getText().toString()),1);
                boxed.setChecked(true);
                Toast.makeText(MainActivity.getAppContext(), "Checked!", Toast.LENGTH_SHORT).show();
                updateData();
            } else if (!boxed.isChecked()) {
                db.update(Long.parseLong(Hidden.getText().toString()),0);
                boxed.setChecked(false);
                Toast.makeText(MainActivity.getAppContext(), "Unchecked!", Toast.LENGTH_SHORT).show();
                updateData();
            }
        }
    }

    public static Context getAppContext() {
        return MainActivity.context;
    }
}
