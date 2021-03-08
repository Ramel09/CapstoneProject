package com.rajendra.onlinedailygroceries;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelperSellerLog extends SQLiteOpenHelper {
    public static final String DBNAME = "SellerLogin1.db";

    public DBHelperSellerLog(Context context) {
        super(context, "SellerLogin1.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDBSeller) {
        MyDBSeller.execSQL("create Table sellerusers(email TEXT primary key,password TEXT,fullname TEXT,ContactNumber TEXT,CompleteAddress TEXT,ShopName TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDBSeller, int i, int i1) {
        MyDBSeller.execSQL("drop Table if exists sellerusers");
    }

    public boolean insertData(String email, String password, String fullname, String ContactNumber, String CompleteAddress,String ShopName) {
        SQLiteDatabase MyDBSeller = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("fullname", fullname);
        contentValues.put("ContactNumber", ContactNumber);
        contentValues.put("CompleteAddress", CompleteAddress);
        contentValues.put("ShopNAme",ShopName);
        long result = MyDBSeller.insert("sellerusers", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    public Boolean checksellerusername(String email) {
        SQLiteDatabase MyDBSeller = this.getWritableDatabase();
        Cursor cursor = MyDBSeller.rawQuery("Select * from sellerusers where email = ?", new String[]{email});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }

    public Boolean checkselleremailpassword(String email, String password) {
        SQLiteDatabase MyDBSeller = this.getWritableDatabase();
        Cursor cursor = MyDBSeller.rawQuery("Select * from sellerusers where email = ? and password = ?", new String[]{email, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}