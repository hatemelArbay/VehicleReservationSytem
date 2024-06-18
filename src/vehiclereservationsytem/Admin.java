/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vehiclereservationsytem;

/**
 *
 * @author yousifabulnaga
 */
public class Admin extends User{
    static private Admin Adminstrator;
    ROI s ; 
    database db;

    private Admin() {
        s= new supply();
        this.Adminstrator = Adminstrator;
    }
   public static Admin  getInstance( ) {
if ( Adminstrator == null )
{
 Adminstrator = new Admin(); 
}
   return Adminstrator;
   }

    public ROI getS() {
        return s;
    }
   
   
   
   
   
}

