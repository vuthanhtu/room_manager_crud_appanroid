package com.example.thuchanh03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvRoom;
    ArrayList<Room> listRoom = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnAddRoom = (Button)findViewById(R.id.btn_addroom);
        btnAddRoom.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddRoomActivity.class);
                startActivity(intent);
            }
        });
        DatabaseHandle databaseHandle = new DatabaseHandle(MainActivity.this);
        lvRoom = (ListView) findViewById(R.id.list_room);
        listRoom = databaseHandle.getAllRooms();
        ArrayList<String> listRoomId = new ArrayList<>();
        for(Room room:listRoom){
            listRoomId.add(room.toString());
        }
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,listRoomId);
        lvRoom.setAdapter(adapter);
        lvRoom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,ViewDetailActivity.class);
                intent.putExtra("id",i+1);
                startActivity(intent);
            }
        });
        Spinner mySpinner = (Spinner)findViewById(R.id.idSpinner);
        ArrayAdapter<String> listSearch = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));
        listSearch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(listSearch);
        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        EditText txtSearch = (EditText) findViewById(R.id.idtxtSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textSpinner = mySpinner.getSelectedItem().toString();
                String searchText = txtSearch.getText().toString();
                switch (textSpinner){
                    case "Area":
                        double areaSearch = Double.parseDouble(searchText);
                        ArrayList<Room> listRoomByArea = new ArrayList<>();
                        listRoomByArea = databaseHandle.getRoomsByArea(areaSearch);
                        ArrayList<String> listRoomByAreaString = new ArrayList<>();
                        for(Room room:listRoomByArea){
                            listRoomByAreaString.add(room.toString());
                        }
                        ArrayAdapter adapter2 = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,listRoomByAreaString);
                        lvRoom.setAdapter(adapter2);
                        break;
                    case "Rent Price":
                        int rentPriceSearch = Integer.parseInt(searchText);
                        ArrayList<Room> listRoomByRentPrice = new ArrayList<>();
                        listRoomByRentPrice = databaseHandle.getRoomsByRentPrice(rentPriceSearch);
                        ArrayList<String> listRoomByRentPriceString = new ArrayList<>();
                        for(Room room:listRoomByRentPrice){
                            listRoomByRentPriceString.add(room.toString());
                        }
                        ArrayAdapter adapter3 = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,listRoomByRentPriceString);
                        lvRoom.setAdapter(adapter3);
                        break;
                    case "Electric":
                        int electricPriceSearch = Integer.parseInt(searchText);
                        ArrayList<Room> listRoomByElectricPrice = new ArrayList<>();
                        listRoomByElectricPrice = databaseHandle.getRoomsByElectric(electricPriceSearch);
                        ArrayList<String> listRoomByElectricPriceString = new ArrayList<>();
                        for(Room room:listRoomByElectricPrice){
                            listRoomByElectricPriceString.add(room.toString());
                        }
                        ArrayAdapter adapter4 = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,listRoomByElectricPriceString);
                        lvRoom.setAdapter(adapter4);
                        break;
                }
            }
        });
    }
}