package com.example.hellvox.kappetijnmathijspset4;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TodoAdapter adapter;
    ListView todoList;
    Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.enterButton);
        CheckBox box = (CheckBox) findViewById(R.id.check);
        todoList = (ListView) findViewById(R.id.todoList);


        TodoDatabase db = TodoDatabase.getInstance(getApplicationContext());
        mCursor = db.selectAll();
        adapter = new TodoAdapter(getApplicationContext(), mCursor, 0);
        todoList.setAdapter(adapter);


        button.setOnClickListener(new addItem());
        todoList.setOnItemClickListener(new GoButtonClickListener());
        todoList.setOnItemLongClickListener(new GoButtonLongClickListener());
    }


    private class addItem implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "Added!", Toast.LENGTH_SHORT).show();
        }
    }

    private class GoButtonLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getApplicationContext(), "Deleted!", Toast.LENGTH_SHORT).show();

            return true;
        }
    }

    private class GoButtonClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getApplicationContext(), "Checked!", Toast.LENGTH_SHORT).show();
        }
    }

}
