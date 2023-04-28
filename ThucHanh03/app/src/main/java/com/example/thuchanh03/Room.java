package com.example.thuchanh03;

public class Room {
    private int id;
    private double area;
    private int rent;
    private int electricprice;
    private int waterprice;
    private int zone;

    public Room(){

    }

    public Room(double area, int rent, int electricprice, int waterprice, int zone){
        this.area = area;
        this.rent = rent;
        this.electricprice = electricprice;
        this.waterprice = waterprice;
        this.zone = zone;
    }

    public Room(int id, double area, int rent, int electricprice, int waterprice, int zone) {
        this.id = id;
        this.area = area;
        this.rent = rent;
        this.electricprice = electricprice;
        this.waterprice = waterprice;
        this.zone = zone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public int getElectricprice() {
        return electricprice;
    }

    public void setElectricprice(int electricprice) {
        this.electricprice = electricprice;
    }

    public int getWaterprice() {
        return waterprice;
    }

    public void setWaterprice(int waterprice) {
        this.waterprice = waterprice;
    }

    public int getZone() {
        return zone;
    }

    public void setZone(int zone) {
        this.zone = zone;
    }
    @Override
    public String toString(){
        return "Room "+id+"\nArea: "+area+"m3"+"\nRent price: "+rent+"đ"+"\nElectric price: "+electricprice
                +"đ/kwh"+"\nWaterPrice: "+waterprice+"đ/m3";
    }
}
