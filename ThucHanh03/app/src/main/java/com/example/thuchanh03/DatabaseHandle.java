package com.example.thuchanh03;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandle extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "roomManager";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "room";

    private static final String KEY_ID = "id";
    private static final String KEY_AREA = "area";
    private static final String KEY_RENT = "rent";
    private static final String KEY_ELECTRIC_PRICE = "electricprice";
    private static final String KEY_WATER_PRICE = "waterprice";
    private static final String KEY_ZONE = "zone";
    private Context context;
    public DatabaseHandle(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT,%s REAL,%s INTEGER,%s INTEGER,%s INTEGER,%s INTEGER)",TABLE_NAME,KEY_ID,KEY_AREA,KEY_RENT,KEY_ELECTRIC_PRICE,KEY_WATER_PRICE,KEY_ZONE);
        sqLiteDatabase.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String drop_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        sqLiteDatabase.execSQL(drop_table);
        onCreate(sqLiteDatabase);
    }

    public void addRoom(Room room){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_AREA,room.getArea());
        cv.put(KEY_RENT,room.getRent());
        cv.put(KEY_ELECTRIC_PRICE,room.getElectricprice());
        cv.put(KEY_WATER_PRICE,room.getWaterprice());
        cv.put(KEY_ZONE,room.getZone());
        long result = db.insert(TABLE_NAME,null,cv);
        System.out.println(result);
        if(result==-1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Add Successfully",Toast.LENGTH_SHORT).show();
        }
    }
    public ArrayList<Room> getAllRooms(){
        ArrayList<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            Room room = new Room(cursor.getInt(0),cursor.getDouble(1),cursor.getInt(2),cursor.getInt(3),cursor.getInt(4),cursor.getInt(5));
            roomList.add(room);
            cursor.moveToNext();
        }
        return roomList;
    }
    public ArrayList<Room> getRoomsByArea(double area){
        ArrayList<Room> listRoomByArea = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME+" WHERE area<="+Double.toString(area);
        Cursor cursor =db.rawQuery(query,null);
        cursor.moveToFirst();
        while(cursor.isAfterLast()==false){
            Room room = new Room(cursor.getInt(0),cursor.getDouble(1),cursor.getInt(2),cursor.getInt(3),cursor.getInt(4),cursor.getInt(5));
            listRoomByArea.add(room);
            cursor.moveToNext();
        }
        return listRoomByArea;
    }
    public ArrayList<Room> getRoomsByElectric(int electricprice){
        ArrayList<Room> listRoomByElectricprice = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME+" WHERE electricprice<="+Integer.toString(electricprice);
        Cursor cursor =db.rawQuery(query,null);
        cursor.moveToFirst();
        while(cursor.isAfterLast()==false){
            Room room = new Room(cursor.getInt(0),cursor.getDouble(1),cursor.getInt(2),cursor.getInt(3),cursor.getInt(4),cursor.getInt(5));
            listRoomByElectricprice.add(room);
            cursor.moveToNext();
        }
        return listRoomByElectricprice;
    }
    public ArrayList<Room> getRoomsByRentPrice(int rentprice){
        ArrayList<Room> listRoomByRentPrice = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME+" WHERE rent<="+Integer.toString(rentprice);
        Cursor cursor =db.rawQuery(query,null);
        cursor.moveToFirst();
        while(cursor.isAfterLast()==false){
            Room room = new Room(cursor.getInt(0),cursor.getDouble(1),cursor.getInt(2),cursor.getInt(3),cursor.getInt(4),cursor.getInt(5));
            listRoomByRentPrice.add(room);
            cursor.moveToNext();
        }
        return listRoomByRentPrice;
    }
    public Room getRoomById(int roomID){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,null,KEY_ID + " = ?",new String[]{String.valueOf(roomID)},null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        Room room = new Room(cursor.getInt(0),cursor.getDouble(1),cursor.getInt(2),cursor.getInt(3),cursor.getInt(4),cursor.getInt(5));
        return room;
    }
    public void deleteRoom(int roomID){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_NAME,KEY_ID + " = ?",new String[]{String.valueOf(roomID)});
        db.close();
    }

}
