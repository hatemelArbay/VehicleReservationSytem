/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclereservationsytem;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author hatem
 */
public interface ROI {
    public String getSupplyname();
      public double getSupplyPrice();
      public int getSupplyId();
      public Map<String, Double> getSuppList();
    
}
