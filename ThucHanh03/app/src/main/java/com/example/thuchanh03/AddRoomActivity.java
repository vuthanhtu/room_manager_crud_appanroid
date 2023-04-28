package com.example.thuchanh03;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddRoomActivity  extends AppCompatActivity {
    EditText area,rent,electricprice,waterprice,zone;
    Button btnAddNewRoom;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_room);
        area = (EditText) findViewById(R.id.edtArea);
        rent = (EditText) findViewById(R.id.edtRent);
        electricprice = (EditText) findViewById(R.id.edtElectric);
        waterprice = (EditText) findViewById(R.id.edtWater);
        zone = (EditText) findViewById(R.id.edtZone);
        btnAddNewRoom = (Button) findViewById(R.id.btnAdd);
        btnAddNewRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double area1 = Double.parseDouble(area.getText().toString());
                int rentprice1 = Integer.parseInt(rent.getText().toString());
                int electricprice1 = Integer.parseInt(electricprice.getText().toString());
                int waterprice1 = Integer.parseInt(waterprice.getText().toString());
                int zone1 = Integer.parseInt(zone.getText().toString());
                Room room = new Room();
                room.setArea(area1);
                room.setRent(rentprice1);
                room.setElectricprice(electricprice1);
                room.setWaterprice(waterprice1);
                room.setZone(zone1);
                //DatabaseHandle databaseHandle = null;
                System.out.println(area1);
                System.out.println(rentprice1);
                System.out.println(electricprice1);
                System.out.println(waterprice1);
                System.out.println(zone1);
                DatabaseHandle databaseHandle = new DatabaseHandle(AddRoomActivity.this);
                databaseHandle.addRoom(room);
            }
        });
    }
}
