package ir.futureshow.restaurantfinder.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import ir.futureshow.restaurantfinder.Login.User;
import ir.futureshow.restaurantfinder.adapter.RestaurantModel;
import ir.futureshow.restaurantfinder.database.DbSchema.UserTable;
import ir.futureshow.restaurantfinder.database.DbSchema.RestaurantTable;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "restaurant.db";

    public DbHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table  "+
                UserTable.NAME +
                "("+UserTable.Cols.USER_ID+", "+
                UserTable.Cols.USER_NAME+"," +
                UserTable.Cols.USER_EMAIL+", "+
                UserTable.Cols.USER_PASSWORD+")");

        db.execSQL("create table  "+
                RestaurantTable.NAME +
                "("+RestaurantTable.Cols.ID+", "+
                RestaurantTable.Cols.NAME+"," +
                RestaurantTable.Cols.RATE+", "+
                RestaurantTable.Cols.TEL+", "+
                RestaurantTable.Cols.ADDRESS+", "+
                RestaurantTable.Cols.WEBSITE+", "+
                RestaurantTable.Cols.FAV_STATUS+", "+
                RestaurantTable.Cols.TYPE+", "+
                RestaurantTable.Cols.IMAGE1+", "+
                RestaurantTable.Cols.IMAGE2+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    private static ContentValues getUserContentValues(User user){
        ContentValues values = new ContentValues();
        values.put(UserTable.Cols.USER_NAME,user.getName());
        values.put(UserTable.Cols.USER_EMAIL,user.getEmail());
        values.put(UserTable.Cols.USER_PASSWORD,user.getPassword());
        return values;
    }


    private static ContentValues getRestaurantContentValues(RestaurantModel restaurant){
        ContentValues values = new ContentValues();
        values.put( RestaurantTable.Cols.ID,restaurant.getId());
        values.put( RestaurantTable.Cols.NAME,restaurant.getName());
        values.put( RestaurantTable.Cols.RATE,restaurant.getRate());
        values.put( RestaurantTable.Cols.FAV_STATUS,restaurant.getFav_status());
        values.put( RestaurantTable.Cols.TYPE,restaurant.getType());
        values.put( RestaurantTable.Cols.IMAGE1,restaurant.getImage1());
        values.put( RestaurantTable.Cols.IMAGE2,restaurant.getImage2());
        values.put( RestaurantTable.Cols.TEL,restaurant.getTel());
        values.put( RestaurantTable.Cols.ADDRESS,restaurant.getAddress());
        values.put( RestaurantTable.Cols.WEBSITE,restaurant.getWebsite());
        return values;
    }


    public void addNewUser(User user){
        //Writing Data into database
        SQLiteDatabase db = this.getWritableDatabase();

        //Creating values of ContentValues
        ContentValues values = getUserContentValues(user);
        values.put(UserTable.Cols.USER_ID,readUsers().size()+1);

        //Insert values into table raw
        db.insert(UserTable.NAME,null,values);

        //close database
        db.close();
    }

    public void addNewRestaurant(RestaurantModel restaurant){
        //Writing Data into database
        SQLiteDatabase db = this.getWritableDatabase();

        //Creating values of ContentValues
        ContentValues values = getRestaurantContentValues(restaurant);

        //Insert values into table raw
        db.insert(RestaurantTable.NAME,null,values);

        //close database
        db.close();
    }

    public void updateRestaurant(RestaurantModel restaurant){
        int id = restaurant.getId();
        ContentValues values = getRestaurantContentValues(restaurant);
        SQLiteDatabase db = getWritableDatabase();
        int idd = db.update(RestaurantTable.NAME,values,DbSchema.RestaurantTable.Cols.ID + " LIKE '" + id + "'",null);
        Log.i("SFFSDFSDF",""+idd);
    }


    @SuppressLint("Range")
    public ArrayList<User> readUsers(){
        SQLiteDatabase db = this.getReadableDatabase();

        //Create Cursor Variable
        Cursor cursor = db.rawQuery("SELECT * FROM "+UserTable.NAME,null);

        //Create ArrayList courseModelArrayList
        ArrayList<User> ModelArrayList = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                User user =new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(UserTable.Cols.USER_ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(UserTable.Cols.USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(UserTable.Cols.USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(UserTable.Cols.USER_PASSWORD)));
                ModelArrayList.add(user);
            }while (cursor.moveToNext());
        }
        //close the cursor and return arraylist;
        cursor.close();
        return ModelArrayList;

    }

    @SuppressLint("Range")
    public User getUser(String selection) {
        SQLiteDatabase db = getReadableDatabase();
        String[] AllSectionUsers = {
                UserTable.Cols.USER_ID,UserTable.Cols.USER_NAME,
                UserTable.Cols.USER_EMAIL, UserTable.Cols.USER_PASSWORD};
        User user = new User();
        Cursor cursor = db.query(UserTable.NAME, AllSectionUsers,selection,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                user.setId(cursor.getInt(cursor.getColumnIndex(UserTable.Cols.USER_ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(UserTable.Cols.USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(UserTable.Cols.USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(UserTable.Cols.USER_PASSWORD)));
            } while (cursor.moveToNext());
        }else{
            user = null;
        }
        cursor.close();
        if (db.isOpen()) db.close();
        return user;
    }

    @SuppressLint("Range")
    public ArrayList<RestaurantModel> readRestaurants(String selection){
        SQLiteDatabase db = this.getReadableDatabase();

        //Create Cursor Variable
        Cursor cursor = db.rawQuery("SELECT * FROM "+RestaurantTable.NAME+" WHERE "+selection,null);

        //Create ArrayList courseModelArrayList
        ArrayList<RestaurantModel> restaurants = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                RestaurantModel restaurant =new RestaurantModel();
                restaurant.setId(cursor.getInt(cursor.getColumnIndex(RestaurantTable.Cols.ID)));
                restaurant.setName(cursor.getString(cursor.getColumnIndex(RestaurantTable.Cols.NAME)));
                restaurant.setRate(cursor.getString(cursor.getColumnIndex(RestaurantTable.Cols.RATE)));
                restaurant.setFav_status(cursor.getString(cursor.getColumnIndex(RestaurantTable.Cols.FAV_STATUS)));
                restaurant.setType(cursor.getString(cursor.getColumnIndex(RestaurantTable.Cols.TYPE)));
                restaurant.setImage1(cursor.getInt(cursor.getColumnIndex(RestaurantTable.Cols.IMAGE1)));
                restaurant.setImage2(cursor.getInt(cursor.getColumnIndex(RestaurantTable.Cols.IMAGE2)));
                restaurant.setTel(cursor.getString(cursor.getColumnIndex(RestaurantTable.Cols.TEL)));
                restaurant.setAddress(cursor.getString(cursor.getColumnIndex(RestaurantTable.Cols.ADDRESS)));
                restaurant.setWebsite(cursor.getString(cursor.getColumnIndex(RestaurantTable.Cols.WEBSITE)));
                restaurants.add(restaurant);
            }while (cursor.moveToNext());
        }
        //close the cursor and return arraylist;
        cursor.close();
        return restaurants;

    }
    @SuppressLint("Range")
    public RestaurantModel getRestaurant(String selection){
        SQLiteDatabase db = this.getReadableDatabase();

        //Create Cursor Variable
        Cursor cursor = db.rawQuery("SELECT * FROM "+RestaurantTable.NAME+" WHERE "+selection,null);

        //Create ArrayList courseModelArrayList
        RestaurantModel restaurant =new RestaurantModel();

        if(cursor.moveToFirst()){
            do{
                restaurant.setId(cursor.getInt(cursor.getColumnIndex(RestaurantTable.Cols.ID)));
                restaurant.setName(cursor.getString(cursor.getColumnIndex(RestaurantTable.Cols.NAME)));
                restaurant.setRate(cursor.getString(cursor.getColumnIndex(RestaurantTable.Cols.RATE)));
                restaurant.setFav_status(cursor.getString(cursor.getColumnIndex(RestaurantTable.Cols.FAV_STATUS)));
                restaurant.setType(cursor.getString(cursor.getColumnIndex(RestaurantTable.Cols.TYPE)));
                restaurant.setImage1(cursor.getInt(cursor.getColumnIndex(RestaurantTable.Cols.IMAGE1)));
                restaurant.setImage2(cursor.getInt(cursor.getColumnIndex(RestaurantTable.Cols.IMAGE2)));
                restaurant.setTel(cursor.getString(cursor.getColumnIndex(RestaurantTable.Cols.TEL)));
                restaurant.setAddress(cursor.getString(cursor.getColumnIndex(RestaurantTable.Cols.ADDRESS)));
                restaurant.setWebsite(cursor.getString(cursor.getColumnIndex(RestaurantTable.Cols.WEBSITE)));
            }while (cursor.moveToNext());
        }
        //close the cursor and return arraylist;
        cursor.close();
        return restaurant;

    }




}
