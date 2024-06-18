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


public class service {
    private int serviceId;
   private String serviceType ;
   private double servicePrice;
   private database db= new database();
   private vehicle v;
   
   
   


   public service (){}
   
   
    public service(int serviceId) {
        this.serviceId=serviceId;
        this.servicePrice=db.getServicePrice(serviceId);
        this.serviceType=db.getServiceType(serviceId);
        v= new vehicle();
         
        
    }
    
 
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }

    public int getServiceId() {
        return serviceId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public double getServicePrice() {
        return servicePrice;
    }

    public vehicle getV() {
        return v;
    }

    @Override
    public String toString() {
        return "service{" + "serviceId=" + serviceId + ", serviceType=" + serviceType + ", servicePrice=" + servicePrice + ", v=" + v + '}';
    }

   
    
    
   
    
}
