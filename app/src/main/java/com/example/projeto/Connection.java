package com.example.projeto;
// Criado por Júlia Martins
// Data: 20/09/2022
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Connection extends SQLiteOpenHelper {

    private static final String name = "banco.db";
    private static final int version = 1;
    public Connection(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate (SQLiteDatabase db) {
        db.execSQL("create table client (id integer primary key autoincrement," +
                "name varchar(50), description varchar(70), date varchar(30), amount varchar(90))");
    }
    @Override
    public void onUpgrade (SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
