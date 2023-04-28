package com.example.thuchanh03;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class ViewDetailActivity extends AppCompatActivity {
    Button btnDelete1, btnBack1;
    TextView area,rent,electricprice,waterprice,zone,roomIDTV;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_detail);
        btnDelete1 = (Button) findViewById(R.id.btnDelete);
        btnBack1 = (Button) findViewById(R.id.btnBack);
        btnBack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewDetailActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        Intent intent = this.getIntent();
        area = (TextView) findViewById(R.id.idTVArea);
        rent = (TextView) findViewById(R.id.idTVRent);
        electricprice = (TextView) findViewById(R.id.idTVElectric);
        waterprice = (TextView) findViewById(R.id.idTVWater);
        zone = (TextView) findViewById(R.id.idTVZone);
        roomIDTV = (TextView) findViewById(R.id.TVViewDetailRoom);
        if(intent!=null){
            System.out.println("Intent: "+intent);
            int roomID = intent.getIntExtra("id",0);
            System.out.println("RoomID "+roomID);
            DatabaseHandle databaseHandle = new DatabaseHandle(ViewDetailActivity.this);
            Room room = databaseHandle.getRoomById(roomID);
            area.setText("Area: "+room.getArea()+"m3");
            rent.setText("Rent Price: "+room.getRent()+"đ");
            electricprice.setText("Electric Price/Kwh: "+room.getElectricprice()+"đ");
            waterprice.setText("Water Price/M3: "+room.getWaterprice()+"đ");
            zone.setText("Zone: "+room.getZone());
            roomIDTV.setText("DETAIL ROOM "+roomID);

        }
        btnDelete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int roomID = intent.getIntExtra("id",0);
                DatabaseHandle databaseHandle = new DatabaseHandle(ViewDetailActivity.this);
                databaseHandle.deleteRoom(roomID);
                Toast.makeText(ViewDetailActivity.this,"Delete suceessfully",Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(ViewDetailActivity.this,MainActivity.class);
                startActivity(intent1);
                // Toast.makeText(ViewDetailActivity.this,"Delete suceessfully",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
