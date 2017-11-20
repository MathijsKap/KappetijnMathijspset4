package com.example.hellvox.kappetijnmathijspset4;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
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

    TodoAdapter adapter;
    ListView todoList;
    Cursor mCursor;
    TodoDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    private void updateData() {
        adapter.swapCursor(db.selectAll());
    }


    private class add implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            EditText enterbox = (EditText) findViewById(R.id.enterText);
            String title = enterbox.getText().toString();
            addItem(title, 0);
            enterbox.setText("");
            Toast.makeText(getApplicationContext(), "Added!", Toast.LENGTH_SHORT).show();
            updateData();
            hideSoftKeyboard(MainActivity.this);
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

    private class GoButtonClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            LinearLayout item = (LinearLayout) view;
            TextView idHidden = (TextView) item.getChildAt(0);
            CheckBox box = (CheckBox) item.getChildAt(1);
            if (!box.isChecked()) {
                db.update(Long.parseLong(idHidden.getText().toString()),1);
                box.setChecked(true);
                Toast.makeText(getApplicationContext(), "Checked!", Toast.LENGTH_SHORT).show();
                updateData();
            } else if (box.isChecked()) {
                db.update(Long.parseLong(idHidden.getText().toString()),0);
                box.setChecked(false);
                Toast.makeText(getApplicationContext(), "Unchecked!", Toast.LENGTH_SHORT).show();
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
}
