package com.rajendra.onlinedailygroceries;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DBHelperAddSellerProd extends SQLiteOpenHelper {

    public DBHelperAddSellerProd(Context context, String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public  void queryData(String sql){
        SQLiteDatabase database=getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData(String name, double price, byte[] image,String description){
        SQLiteDatabase database=getWritableDatabase();
        String sql = "INSERT INTO FOOD VALUES (NULL, ?, ?, ?,?)";

        SQLiteStatement statement= database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindDouble(2, price);
        statement.bindBlob(3, image);
        statement.bindString(4,description);
        statement.executeInsert();
    }

    public void UpdateData(String name, double price, byte[] image,String description, int id){
        SQLiteDatabase database=getWritableDatabase();

        String  sql= "UPDATE FOOD SET name = ?, price = ?, image = ?, description = ? WHERE id = ?";
        SQLiteStatement statement=database.compileStatement(sql);

        statement.bindString(1, name);
        statement.bindDouble(2, price);
        statement.bindBlob(3, image);
        statement.bindString(4,description);
        statement.bindDouble(5,(double)id);

        statement.execute();
        database.close();
    }
    public  void deleteData(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM FOOD WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database=getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
