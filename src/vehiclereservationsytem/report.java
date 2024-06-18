/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclereservationsytem;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author hatem
 */
public class report {
    private int reportId;
    private double totalBill; 
 private ArrayList<String>supplyName= new ArrayList<>();
 private ArrayList<Double>supplyPrice= new ArrayList<>();
 

 
 database db= new database();

    public report() {
        this.totalBill = 0;
         getSupplyFromDB();
         
       
    }
    
      public report(double rentPrice, int rentedDays) {
        this.totalBill = rentPrice*rentedDays;   
    }
    
   public void calculateTotalBill(ArrayList<supply> suppList){
   double total = 0 ;
       for(int i =0 ;i<suppList.size();i++){
       total+=suppList.get(i).getSupplyPrice();
   
   }
       totalBill=total;
   
   }
   
   public void calculateBillForMechanic(){
     
       totalBill*=-1 ;
   }
  
    void calculateTotalBill(int serviceID){
    this.totalBill=db.getServicePrice(serviceID);
    }
    
    double calculateTotalBill(double suppPrice1,double suppPrice2){
        
    return suppPrice1+suppPrice2;
    }
    
    public void consumeSupply(String suppName1,String suppName2){
       db.deleteSupply(suppName1);
       db.deleteSupply(suppName2);
    
    }
    
    

    public int randomSupplyConsumed(){
        
      Random random = new Random();
   int randomNumber = random.nextInt(supplyName.size());
     
      return randomNumber;
    
    }
 
    public double getTotalBill() {
        return totalBill;
    }

 
    
    public void getSupplyFromDB(){
       supplyName=db.retrieveSupplyname();
       // System.out.println(supplyName.size());
       supplyPrice=db.retrieveSupplyPrice();
  
    }
    
    public ArrayList<String> getsupplyName(){
        return supplyName;
    }
    
    public ArrayList<Double> getsupplyPrice(){
        return supplyPrice;
    }

//    public Map<String, Double> getSupply() {
//        getSupplyFromDB();
//        return supply;
//    }

 public void displaySupplyName(){
 getSupplyFromDB();
 
 for(int i =0 ;i<supplyName.size();i++){
     System.out.println(supplyName.get(i));
 }
 
 }

    public int getReportId() {
        return reportId;
    }

    @Override
    public String toString() {
        return "report{" + "reportId=" + reportId + ", totalBill=" + totalBill + '}';
    }


    
    
    
    
}
