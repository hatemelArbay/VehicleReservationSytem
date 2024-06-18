/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclereservationsytem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hatem
 */
public class supply implements ROI {
    int supplyId;
    private String supplyname ; 
    private double supplyPrice ; 
    private database db;
    
public supply(){ db= new database ();}
    public supply(String supplyname, double supplyPrice) {
        this.supplyname = supplyname;
        this.supplyPrice = supplyPrice;
        db= new database ();
     
    }

    public void setSupplyname(String supplyname) {
        this.supplyname = supplyname;
    }

    public void setSupplyPrice(double supplyPrice) {
        this.supplyPrice = supplyPrice;
    }
    
      public Map<String, Double> getSuppList(){
        Map<String,Double>suppList = new HashMap<>();
        suppList=db.getSuppFromDB();
        
      return suppList;
      }

    @Override
    public String getSupplyname() {
        return supplyname;
    }

    @Override
    public double getSupplyPrice() {
        return supplyPrice;
    }

    @Override
    public int getSupplyId() {
        return supplyId;
    }
    
    
    

    @Override
    public String toString() {
        return "supply{" + "supplyId=" + supplyId + ", supplyname=" + supplyname + ", supplyPrice=" + supplyPrice + '}';
    }
    
    
    
    
}
