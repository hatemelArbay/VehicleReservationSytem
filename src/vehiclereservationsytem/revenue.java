/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclereservationsytem;

/**
 *
 * @author hatem
 */
public class revenue {
    private int totalRevenenue;
    private database db;

    public revenue(int totalRevenenue) {
        this.totalRevenenue = totalRevenenue;
        db= new database();
    }

    public void setTotalRevenenue(int totalRevenenue) {
        this.totalRevenenue = totalRevenenue;
    }

    public int getTotalRevenenue() {
        return totalRevenenue;
    }

    @Override
    public String toString() {
        return "revenue{" + "totalRevenenue=" + totalRevenenue + '}';
    }
    
    public void updateRevenue(double bill){
        
        db.addRevenueToDb(bill);
    
    
    
    }
    
}
