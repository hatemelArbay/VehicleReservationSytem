/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclereservationsytem;

import java.util.Date;

/**
 *
 * @author hatem
 */

public class vehicle {

    private String vehiclename;
    private String vehiclesubname;
    private int vehicleyear;
    private double pricePerday;
    private boolean isRented;
    private Date rentedUntil; 
    database db;
  
    public vehicle(){
          db= new database();
    // this.vehicleID=db.getLatesVehicleId();
     isRented=false;
   
    }
    
    public vehicle( String vehiclename, String vehiclesubname, int vehicleyear,double pricePerDay) {
         db= new database();
      //  vehicleID=db.getLatesVehicleId();
        this.vehiclename = vehiclename;
        this.vehiclesubname = vehiclesubname;
        this.vehicleyear = vehicleyear;
        this.pricePerday=pricePerDay;
        isRented=false;
       
    }
    
    public vehicle( String vehiclename, String vehiclesubname, int vehicleyear) {
         db= new database();
       // vehicleID=db.getLatesVehicleId();
        this.vehiclename = vehiclename;
        this.vehiclesubname = vehiclesubname;
        this.vehicleyear = vehicleyear;
        this.pricePerday=0;
        isRented=false;
       
    }
    
    public vehicle( int vehicleId ,String vehiclename, String vehiclesubname, int vehicleyear,double pricePerDay) {
       // this.vehicleID=vehicleId;
        this.pricePerday=pricePerDay;
        this.vehiclename = vehiclename;
        this.vehiclesubname = vehiclesubname;
        this.vehicleyear = vehicleyear;
        pricePerday=0;
        isRented=true;
        db= new database();
    }

    // Getters and setters for the fields
    public String getVehicleName() {
        return vehiclename;
    }

    public void setVehicleName(String vehiclename) {
        this.vehiclename = vehiclename;
    }

    public String getVehicleSubName() {
        return vehiclesubname;
    }

    public void setVehicleSubName(String vehiclesubname) {
        this.vehiclesubname = vehiclesubname;
    }

    public int getVehicleYear() {
        return vehicleyear;
    }

    public void setVehicleyear(int vehicleyear) {
        this.vehicleyear = vehicleyear;
    }

//    public int getVehicleID() {
//        return vehicleID;
//    }
//
//    
    
    public double getPricePerday() {
        return pricePerday;
    }

//    public void setVehicleID(int vehicleID) {
//        this.vehicleID = vehicleID;
//    }

  

    public void setVehiclesubname(String vehiclesubname) {
        this.vehiclesubname = vehiclesubname;
    }

    public void setPricePerday(double pricePerday) {
        this.pricePerday = pricePerday;
    }

    public void setIsRented(boolean isRented) {
        this.isRented = isRented;
    }

    public boolean isIsRented() {
        return isRented;
    }

    @Override
    public String toString() {
        return "vehicle{" + "vehiclename=" + vehiclename + ", vehiclesubname=" + vehiclesubname + ", vehicleyear=" + vehicleyear + ", pricePerday=" + pricePerday + ", isRented=" + isRented + '}';
    }


 
  
    

}