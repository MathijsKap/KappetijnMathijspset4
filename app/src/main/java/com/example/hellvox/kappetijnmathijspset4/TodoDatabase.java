package com.example.hellvox.kappetijnmathijspset4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HellVox on 18-11-2017.
 */

public class TodoDatabase extends SQLiteOpenHelper {

    private static TodoDatabase instance;


    private TodoDatabase(Context context) {
        super(context, "todo", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
          "create table if not exists todos" +
                  "( _id integer primary key autoincrement, title text, complete integer )"
        );
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", "nummer 1");
        contentValues.put("complete", 0);
        db.insert("todos", null, contentValues);

        contentValues = new ContentValues();
        contentValues.put("title", "nummer 2");
        contentValues.put("complete", 0);
        db.insert("todos", null, contentValues);

        contentValues = new ContentValues();
        contentValues.put("title", "nummer 3");
        contentValues.put("complete", 0);
        db.insert("todos", null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS todos");
        onCreate(db);

    }

    private void insertToDo (String title, int complete) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("complete", complete);
        db.insert("todos", null, contentValues);
    }

    public Cursor selectAll() {
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery("select * from todos", null);
    }

    public static TodoDatabase getInstance(Context context) {
        if (instance != null)
            return instance;
        else {
            instance = new TodoDatabase(context);
            return instance;
        }
    }
}
