package com.example.btth_2322021;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {
    //khai bao ten csdl,bang,ten truong
    public static final String DBName="mydb.db";
    public static final int VERSION=1;
    public static final String Table_Name="Sinhvien";
    public static final String ID="_id";
    public static final String NAME="name";
    public static final String NAMSINH="namsinh";
    public SQLiteDatabase myDB;


    public MyDBHelper(@Nullable Context context) {
        super(context,DBName, null, VERSION); //truyen du lieu vao
    }

    public static String getId(){
        return ID;
    }
    public static String getName(){
        return NAME;
    }
    public static String getNamsinh(){
        return NAMSINH;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryTable = "CREATE TABLE " + Table_Name + "(" + ID + " INTEGER PRIMARY KEY, " + NAME + " TEXT NOT NULL, " + NAMSINH + " INTEGER NOT NULL"+")";
    db.execSQL(queryTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    //open DB
    public void openDB(){
        myDB=getWritableDatabase();
    }
    public void closeDB(){
        if(myDB!=null && myDB.isOpen())
            myDB.close();
    }

    //viet cac phuong thuc insert update delete
    public long Insert(int id,String name,int namsinh){
        //nhap qua ContentValues
        ContentValues values=new ContentValues();
        values.put(ID,id);
        values.put(NAME,name);
        values.put(NAMSINH,namsinh);
        return myDB.insert(Table_Name,null,values);
    }
    public long Update(int id,String name,int namsinh){
        //nhap qua ContentValues
        ContentValues values=new ContentValues();
        values.put(ID,id);
        values.put(NAME,name);
        values.put(NAMSINH,namsinh);
        String where = ID + " = " + id;
        return myDB.update(Table_Name,values,where,null);
    }
    public long Delete(int id){
        String where = ID + " = " + id;
        return myDB.delete(Table_Name,where,null);
    }
    public Cursor LoadAllRecord(){
        String query="SELECT * FROM " + Table_Name;
        return myDB.rawQuery(query,null);
    }
}
