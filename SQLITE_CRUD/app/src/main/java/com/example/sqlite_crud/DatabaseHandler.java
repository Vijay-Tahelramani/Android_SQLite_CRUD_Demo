package com.example.sqlite_crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "UserManager";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_USERS = "user";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    long inserted;
    int deleted;
    int updated;


    String CREATE_USERS_TABLE = "CREATE TABLE "+TABLE_USERS+"("+KEY_ID+" INTEGER PRIMARY KEY,"+KEY_NAME+" TEXT,"+KEY_EMAIL+" TEXT,"+KEY_PASSWORD+" TEXT"+")";

    String DROP_USERS_TABLE = "DROP TABLE IF EXISTS "+TABLE_USERS;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DROP_USERS_TABLE);
        onCreate(db);
    }
    public long addUser(String param1,String param2,String param3){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,param1);
        values.put(KEY_EMAIL,param2);
        values.put(KEY_PASSWORD,param3);
        inserted = db.insert(TABLE_USERS,null,values);
        db.close();
        //Here db.insert returns the long value of the row ID of the newly inserted row OR returns -1 when error occured
        return inserted;
    }

    public int deleteUser(String param1){

        SQLiteDatabase db = this.getWritableDatabase();
        deleted = db.delete(TABLE_USERS,KEY_EMAIL+"=?", new String[]{param1});
        db.close();
        //Here db.delete returns 1 when the effective row is deleted otherwise it returns 0
        return deleted;
    }

    public int updateUserData(String email,String param1,String param2){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,param1);
        values.put(KEY_PASSWORD,param2);
        updated = db.update(TABLE_USERS,values,KEY_EMAIL+"=?", new String[]{email});
        db.close();
        //Here db.update return the number of rows affected, if no rows have been updated it returns 0
        return updated;
    }

    public boolean checkUserExist(String param1){
        //Check if user exist or not if yes return true else return false
        String selectQuery = "SELECT * FROM "+TABLE_USERS+" WHERE "+KEY_EMAIL+" = '" + param1 + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;

    }

    public List<Users> getSpecificUser(String param1){
        List<Users> usersList = new ArrayList<Users>();
        //Select all query
        String selectQuery = "SELECT * FROM "+TABLE_USERS+" WHERE "+KEY_EMAIL+" = '" + param1 + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        //looping through all rows add adding to list
        if(cursor.moveToFirst()){
            do{
                Users users = new Users();
                users.setId(Integer.parseInt(cursor.getString(0)));
                users.setName(cursor.getString(1));
                users.setEmail(cursor.getString(2));
                users.setPassword(cursor.getString(3));
                usersList.add(users);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return usersList;
    }

    public List<Users> getAllUsers(){
        List<Users> usersList = new ArrayList<Users>();
        //Select all query
        String selectQuery = "SELECT * FROM "+TABLE_USERS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        //looping through all rows add adding to list
        if(cursor.moveToFirst()){
            do{
                Users users = new Users();
                users.setId(Integer.parseInt(cursor.getString(0)));
                users.setName(cursor.getString(1));
                users.setEmail(cursor.getString(2));
                users.setPassword(cursor.getString(3));
                usersList.add(users);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return usersList;
    }
}
