
package vehiclereservationsytem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.Time;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import static  org.mockito.Mockito.verify;
//import static  org.mockito.ArgumentMatchers.anyString;



/**
 *
 * @author hatem
 */
public class database {
    
   private  String connectionURL; 
   private Connection conn ;
  // private String sql;
   
   
 database(){
     
    connectionURL ="jdbc:mysql://localhost:3306/vehicleManagementSystem?";
//  connectionURL =  "jdbc:mysql://localhost:3306/vehiclemanagementsystem?";
    

      try {
         conn = DriverManager.getConnection(connectionURL, "root", "");
        
        } catch (SQLException ex) {
        System.out.println("Connect failed ! " +ex.getMessage());
        }
      
      
}
        
     public ArrayList<String> retrieveSupplyname(){
             
      ArrayList<String>supplyName= new ArrayList<String>();
         
          try {
     Statement st = conn.createStatement();
        ResultSet rs=null; 
        rs=st.executeQuery("SELECT * FROM supply");
        while(rs.next()){

          supplyName.add(rs.getString("supplyName"));

        }
//        rs.close();
//        st.close();
//        conn.close();
            
        }catch(SQLException ex) {
        System.out.println("Connect failed ! " +ex.getMessage());
        }
return supplyName;
    }
     
     
        public ArrayList<Double> retrieveSupplyPrice(){
             
      ArrayList<Double>supplyPrice= new ArrayList<Double>();
         
          try {
     Statement st = conn.createStatement();
        ResultSet rs=null; 
        rs=st.executeQuery("SELECT * FROM supply");
        while(rs.next()){

          supplyPrice.add(rs.getDouble("supplyPrice"));

        }
            
        }catch(SQLException ex) {
        System.out.println("Connect failed ! " +ex.getMessage());
        }
          
return supplyPrice;
    }
        
        
        
        
     
  public void deleteSupply(String suppName) {
     
    try {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM supply WHERE supplyName = ?");
        stmt.setString(1,suppName); 
        int rowsDeleted = stmt.executeUpdate();
        System.out.println("Rows deleted: " + rowsDeleted);
        stmt.close();
    } catch (SQLException ex) {
        System.out.println("Delete failed! " + ex.getMessage());
    
    }
        
}
     
 void retrieveCust(String sql){
        try {
     Statement st = conn.createStatement();
        ResultSet rs=null;
        rs=st.executeQuery(sql);
        while(rs.next()){
        System.out.println(rs.getString("name"));
        }
//        rs.close();
//        st.close();
//        conn.close();
        }catch(SQLException ex) {
        System.out.println("Connect failed ! " +ex.getMessage());
        }
    }

        
     Map<String, Double> retrivesupplyData(){
             
         Map<String,Double> supply=new HashMap<>();
         
          try {
     Statement st = conn.createStatement();
        ResultSet rs=null; 
        rs=st.executeQuery("SELECT * FROM supply");
        while(rs.next()){
supply.put(rs.getString("supplyName"),rs.getDouble("supplyPrice") );
        }
        
        
        rs.close();
        st.close();
        conn.close();
            
        }catch(SQLException ex) {
        System.out.println("Connect failed ! " +ex.getMessage());
        }
return supply;
    }
     
  public void deleteSupply(Map<String,Double> supply) {
        for(Map.Entry<String,Double> entry : supply.entrySet()){
    try {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM supply WHERE supplyName = ?");
        stmt.setString(1,entry.getKey()); 
        int rowsDeleted = stmt.executeUpdate();
        System.out.println("Rows deleted: " + rowsDeleted);
       
    } catch (SQLException ex) {
        System.out.println("Delete failed! " + ex.getMessage());
    
    }
        }
}
public void saveAccount(Account account , int userId) {
    
    try{
    PreparedStatement statement = conn.prepareStatement(
        "INSERT INTO account (accountID, userName, pass, userPID) VALUES (?, ?, ?, ?)"
    );
    statement.setInt(1, account.getUserID());
    statement.setString(2, account.getUsername());
    statement.setString(3, account.getPassword());
     statement.setInt(4,userId);
    
   
    statement.executeUpdate();
    }catch(Exception ex){
        System.out.println("in account");
        System.out.println("failed "+ex.getMessage());
    }
    
}

public void saveUser(int id, String name , String phoneNum, String address , String email ,Account a)  {
   
    try{
    PreparedStatement statement = conn.prepareStatement(
        "INSERT INTO user (PID, name, phoneNumber, address, email) VALUES (?, ?, ?, ?, ?)"
    );
    statement.setInt(1, id);
    statement.setString(2, name);
    statement.setString(3,phoneNum);
    statement.setString(4,address);
    statement.setString(5,email);
    statement.executeUpdate();
    saveAccount(a,id);
    
    }catch(Exception ex){
        System.out.println("in user");
        System.out.println(id);
        System.out.println("failed "+ex.getMessage());
       
    }
    

}



 public int  getLatestuserId(){
     int count=0;
 try {
     String query = "SELECT COUNT(*) FROM user"; 
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            if (rs.next()) {
                 count = rs.getInt(1);
                
            }
            
          
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
 return ++count ;
     
     
 
 }
 
 

public void saveCustomer(Customer customer)  {
     try{
    PreparedStatement statement = conn.prepareStatement(
          
        "INSERT INTO customer (customerPID, userPID) VALUES (?, ?)"
    );
    statement.setInt(1, customer.getCustID());
    statement.setInt(2, customer.getId());
    statement.executeUpdate();
            }catch(Exception e){
                System.out.println("failed "+e.getMessage());
            }
}
//kanet static 
public Customer getCustomerById(int customerId) {
    Customer customer = null;
    try {
  
        String query = "SELECT *FROM customer ,user \n" +
"WHERE user.PID = customer.userPID \n" +
"AND\n" +
"user.PID=?;";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, customerId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String phone = resultSet.getString("phoneNumber");
            String address = resultSet.getString("address");
            String email = resultSet.getString("email");
            String username = resultSet.getString("userName");
            String password = resultSet.getString("pass");
            customer = new Customer(name, phone, address, email, username, password);
            
        }
        resultSet.close();
        statement.close();
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return customer;
}


public void saveMechanic(Mechanic mechanic) {
   
  try {
     
        String query = "INSERT INTO mechanic (userID, Role, Salary) VALUES (?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1,mechanic.getId());
        statement.setString(2,mechanic.getRole());
        statement.setDouble(3,mechanic.getSalary());
        statement.executeUpdate();
       // System.out.println("updated");
   
    } catch (SQLException e) {
        System.out.println("in mechanic");
        e.printStackTrace();
    }
    }

 public Mechanic getMechanicById(int id) throws SQLException {
        String sql = "SELECT * FROM mechanic JOIN user ON mechanic.userID = user.PID WHERE mechanic.mechanicID = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int mechanicId = resultSet.getInt("mechanicID");
                String name = resultSet.getString("name");
                String phoneNumber = resultSet.getString("phoneNumber");
                String address = resultSet.getString("address");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                double salary = resultSet.getDouble("salary");
                return new Mechanic(mechanicId,name, phoneNumber, address, email, password, role, salary);
            } else {
                return null;
            }
        }
    }
public void displayAllMechanics() {
    try {
      conn = DriverManager.getConnection(connectionURL, "root", "");
        Statement stmt = conn.createStatement();
        String query = "SELECT user.name, user.phoneNumber, user.address, user.email, mechanic.role, mechanic.salary " +
                "FROM user " +
                "INNER JOIN mechanic ON user.PID = mechanic.userID";
        ResultSet rs = stmt.executeQuery(query);

        System.out.println("Mechanics:");
        while (rs.next()) {
            String name = rs.getString("name");
            String phoneNumber = rs.getString("phoneNumber");
            String address = rs.getString("address");
            String email = rs.getString("email");
            String role = rs.getString("role");
            double salary = rs.getDouble("salary");

            System.out.println("- Name: " + name);
            System.out.println("  Phone Number: " + phoneNumber);
            System.out.println("  Address: " + address);
            System.out.println("  Email: " + email);
            System.out.println("  Role: " + role);
            System.out.println("  Salary: " + salary);
        }

        rs.close();
        stmt.close();
        conn.close();
    } catch (SQLException ex) {
        System.out.println("Error connecting to database.");
        ex.printStackTrace();
    }
}

  public void deleteMechanic(int id) throws SQLException {
    String sql = "DELETE FROM mechanic WHERE mechanicID = ?";
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    // Delete the mechanic's account and user
    sql = "DELETE account, user FROM account "
            + "JOIN user ON account.userPID = user.PID "
            + "WHERE user.PID IN "
            + "(SELECT userID FROM mechanic WHERE mechanicID = ?)";
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
        statement.setInt(1, id);
        statement.executeUpdate();
    }
}

public void displayOffersLoop() {
    String sql = "SELECT * FROM offer";
    try (PreparedStatement statement = conn.prepareStatement(sql);
         ResultSet resultSet = statement.executeQuery()) {
        while (true) {
            LocalDate currentDate = LocalDate.now(); // Get the current date
            while (resultSet.next()) {
                int offerId = resultSet.getInt("offer_id");
                String offerMessage = resultSet.getString("offer_message");
                LocalDate startDate = resultSet.getDate("start_date").toLocalDate();
                LocalDate endDate = resultSet.getDate("end_date").toLocalDate();
                
                // Check if the offer end date has not passed the current date
                if (!endDate.isBefore(currentDate)) {
                    String formattedStartDate = startDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
                    String formattedEndDate = endDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
 
                    System.out.println("Offer #" + offerId + ": " + offerMessage);
                    System.out.println("Valid from " + formattedStartDate + " to " + formattedEndDate);
                }
            }
            Thread.sleep(5000); // Delay for 5 seconds between each loop iteration
            resultSet.beforeFirst(); // Reset the result set cursor to the beginning
        }
    } catch (SQLException | InterruptedException ex) {
        ex.printStackTrace();
    }
}
public String userType(String username,String password){
    String userType = null; 
    try {
            

            PreparedStatement stmt = conn.prepareStatement("SELECT userPID FROM account WHERE userName=? AND pass=?");
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                int userPID = rs.getInt("userPID");

                
                PreparedStatement userTypeStmt;

                userTypeStmt = conn.prepareStatement("SELECT * FROM customer WHERE userPID=?");
                userTypeStmt.setInt(1, userPID);
                rs = userTypeStmt.executeQuery();
                if (rs.next()) {
                    userType = "customer";
                }

                userTypeStmt = conn.prepareStatement("SELECT * FROM mechanic WHERE userID=?");
                userTypeStmt.setInt(1, userPID);
                rs = userTypeStmt.executeQuery();
                if (rs.next()) {
                    userType = "mechanic";
                   
                }

                userTypeStmt = conn.prepareStatement("SELECT * FROM admin WHERE userPID=?");
                userTypeStmt.setInt(1, userPID);
                rs = userTypeStmt.executeQuery();
                if (rs.next()) {
                    userType = "admin";
                }

               return userType;
            }

      
        } catch (SQLException ex) {
         // Handle SQL exception

        }
       return userType;
}

    
public void addOfferToDatabase(Offer offer) {
  PreparedStatement stmt;
    try {

        // Prepare a statement to insert a new offer into the offer table
         stmt = conn.prepareStatement("INSERT INTO offer (offer_message, start_date, end_date) VALUES (?, ?, ?)");

        // Set the parameters of the statement using the data from the offer object
        stmt.setString(1, offer.getOfferMessage());
        stmt.setDate(2, java.sql.Date.valueOf(offer.getStartDate()));
        stmt.setDate(3, java.sql.Date.valueOf(offer.getEndDate()));

        // Execute the statement to insert the offer into the database
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    } 

}

    public int getVehicleId(vehicle v){
             int vehicleId=0;
                try {
        PreparedStatement ps = conn.prepareStatement("SELECT vehicleID FROM vehicle  \n" +
"WHERE \n" +
"vehicle.vehicleName=?\n" +
"AND\n" +
"vehicle.vehicleSubName=?\n" +
"AND\n" +
"vehicle.vehicleYear=?;");
        ps.setString(1, v.getVehicleName());
        ps.setString(2, v.getVehicleSubName());
        ps.setInt(3, v.getVehicleYear());
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            vehicleId=rs.getInt("vehicleID");
            
        }
        
    } catch (SQLException e) {
        System.err.println("failed" + e.getMessage());
    }
         return vehicleId;
         }
public void addVehicle(vehicle v){
   try {
     
        String query = "INSERT INTO vehicle ( vehicleName, vehicleSubName, vehicleYear) "
                     + "VALUES ( ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(query);
       // statement.setInt(1,v.getVehicleID());
        statement.setString(1,v.getVehicleName());
          statement.setString(2,v.getVehicleSubName());
          statement.setInt(3,v.getVehicleYear());
          
        
        statement.executeUpdate();
     
    } catch (SQLException e) {
        e.printStackTrace();
    }


}
public void saveAppointment(Appointment appointment) {
   
    addVehicle(appointment.getSevice().getV());
    try {
     
        String query = "INSERT INTO appointment (appId, appTime, appDate, customerID, serviceID, vehicleID) "
                     + "VALUES (?, ?, ?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, appointment.getAppID());
        statement.setTime(2, new java.sql.Time(appointment.getAppointmentTime().getTime()));
       statement.setDate(3, new java.sql.Date(appointment.getAppointmentDate().getTime()));
       statement.setInt(4,appointment.getCustomer().getCustID());
        statement.setInt(5, appointment.getSevice().getServiceId());
        statement.setInt(6,getVehicleId (appointment.getSevice().getV()));
        statement.executeUpdate();
   
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public void addAppForRent(Appointment appointment) {
   
 
    try {
     
        String query = "INSERT INTO appointment (appId, appTime, appDate, customerID, serviceID, vehicleID) "
                     + "VALUES (?, ?, ?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, appointment.getAppID());
        statement.setTime(2, new java.sql.Time(appointment.getAppointmentTime().getTime()));
       statement.setDate(3, new java.sql.Date(appointment.getAppointmentDate().getTime()));
       statement.setInt(4,appointment.getCustomer().getCustID());
        statement.setInt(5, appointment.getSevice().getServiceId());
        statement.setInt(6,getVehicleId( appointment.getSevice().getV()));
        statement.executeUpdate();
   
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
public void deleteAppointment(int appId) {
    try {

        String query = "DELETE FROM appointment WHERE appId = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, appId);
        statement.executeUpdate();
        statement.close();
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
public void updateAppointment(Appointment appointment) {
    try {
        String query = "UPDATE appointment SET appTime = ?, appDate = ?, customerID = ?, serviceID = ?, vehicleID = ? WHERE appId = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setTime(1, (Time) appointment.getAppointmentTime());
        statement.setDate(2, (java.sql.Date) appointment.getAppointmentDate());
        statement.setInt(3, appointment.getCustomer().getId());
        // statement.setInt(4, appointment.getService().getServiceId());
        // statement.setInt(5, appointment.getVehicle().getVehicleId());
        statement.setInt(6, appointment.getAppID());
        statement.executeUpdate();
        statement.close();
      
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

         
  public String getServiceType(int serviceId) {
   String serviceType="";
    
    try {
        PreparedStatement ps = conn.prepareStatement("SELECT serviceType FROM service WHERE serviceId = ?");
        ps.setInt(1, serviceId);
        
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            serviceType=rs.getString("serviceType");
          
        }
        
    } catch (SQLException e) {
        System.err.println("failed" + e.getMessage());
    }
    
    return serviceType ;
}
  
  
    public double getServicePrice(int serviceId) {
   double servicePrice=0;
    
    try {
        PreparedStatement ps = conn.prepareStatement("SELECT servicePrice FROM service WHERE serviceId = ?");
        ps.setInt(1, serviceId);
        
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            servicePrice=rs.getDouble("servicePrice");
          
        }
        
    } catch (SQLException e) {
        System.err.println("failed" + e.getMessage());
    }
    
    return servicePrice ;
}

        
        public Boolean checkAccount(Account a){
      Boolean isRegister = true;
      String user="";
      String pass="";
       try {
        PreparedStatement ps = conn.prepareStatement("SELECT userName,pass FROM Account WHERE userName = ? AND pass= ?");
        ps.setString(1,a.getUsername());
        ps.setString(2,a.getPassword());
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            user=rs.getString("userName");
            pass=rs.getString("pass");
          
        }
        if(user.compareTo("")==0 || pass.compareTo("")==0){
            isRegister=false;
        }
        
    } catch (SQLException e) {
        System.err.println("failed" + e.getMessage());
    }
        
        return isRegister;
        }
        
        public Mechanic getMechanic(String userName, String pass) {
    String sql = "SELECT m.mechanicID, m.role, m.salary, u.PID, u.name, u.phoneNumber, u.address, u.email " +
                 "FROM mechanic m " +
                 "JOIN user u ON m.userID = u.PID " +
                 "JOIN account a ON u.PID = a.userPID " +
                 "WHERE a.userName = ? AND a.pass = ?";
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
        statement.setString(1, userName);
        statement.setString(2, pass);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                int mechanicID = resultSet.getInt("mechanicID");
                String role = resultSet.getString("role");
                double salary = resultSet.getDouble("salary");
                  int userId = resultSet.getInt("PID");
                String name = resultSet.getString("name");
                String phoneNumber = resultSet.getString("phoneNumber");
                String address = resultSet.getString("address");
                String email = resultSet.getString("email");
                
Mechanic mechanic = new Mechanic(mechanicID, name, phoneNumber, address, email, pass, role, salary);
            mechanic.setId(userId);
                System.out.println(mechanic.toString());
                    System.out.println("user id "+mechanic.getId());
                //System.out.println(mechanic.);
                return mechanic;
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return null; // Mechanic not found
}

        
        public Customer getCustObject(String userName, String pass){
        Customer c =null;
            try {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM `user`,`customer`,`account` WHERE user.PID=customer.userPID AND user.PID=account.userPID AND account.userName = ? AND account.pass=?;");
        ps.setString(1,userName);
        ps.setString(2,pass);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            c=new Customer(rs.getString("name"),rs.getString("phoneNumber"),rs.getString("address") ,rs.getString("email"),rs.getString("userName"),rs.getString("pass"));
           c.setCustID(rs.getInt("customerPID"));
           c.setId(rs.getInt("PID"));
        }  
    } catch (SQLException e) {
        System.err.println("failed" + e.getMessage());
    }
            return c ;
        
        }
        
        public int getSeviceIdByCustID(Customer c){
            int serviceId=0;
             try {
        PreparedStatement ps = conn.prepareStatement("SELECT service.serviceId FROM appointment,service,customer WHERE service.serviceId=appointment.serviceID AND customer.customerPID=appointment.customerID AND customer.customerPID=?;");
        ps.setInt(1,c.getCustID());
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            serviceId=rs.getInt("serviceId");
        }
          
     
        
    } catch (SQLException e) {
        System.err.println("failed" + e.getMessage());
    }
            
            
        return serviceId;
        }
        
        
        
      public void savepayment(int paymentId, String cardNum,String cardHolderName, int userId){
             try {
     
        String query = "INSERT INTO payment (paymentID, cardNumber, holderName, userId) "
                     + "VALUES (?, ?, ?,?)";
        
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1,paymentId);
        statement.setString(2,cardNum);
        statement.setString(3,cardHolderName);
        statement.setInt(4,userId);
     
        statement.executeUpdate();
          
    
    } catch (SQLException e) {
        e.printStackTrace();
      
    }
      }
        
        public void savepayment(payment p ){
             try {
     
        String query = "INSERT INTO payment (paymentID, cardNumber, holderName, userId) "
                     + "VALUES (?, ?, ?,?)";
        
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1,p.getPaymentID());
        statement.setString(2,p.getCardHolderNum());
        statement.setString(3,p.getCardholderName());
        statement.setInt(4,p.getC().getId());
     
        statement.executeUpdate();
          
    
    } catch (SQLException e) {
        e.printStackTrace();
      
    }
      }

      
      
      
       public void saveReport(double totalBill,int paymentId){
             
                 try {
                  
     
        String query = "INSERT INTO report ( totalBill, paymentID) "
                     + "VALUES (?, ?)";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setDouble(1,totalBill);
        statement.setInt(2,paymentId);
    
     
        statement.executeUpdate();
    
    } catch (SQLException e) {
        e.printStackTrace();
    }
           
       }

       
             public int getLatestPaymentId(){
            int count=0;
 try {
     String query = "SELECT COUNT(*) FROM payment"; 
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            if (rs.next()) {
                 count = rs.getInt(1);
                
            }
            
          
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
 return ++count ;
     
     
 
 }
             
             public void addSupplyToDb(ArrayList<supply> s){
                 try {
     
                     for(int i =0 ;i<s.size();i++){
                     
        String query = "INSERT INTO supply (supplyName, supplyPrice) "
                     + "VALUES (?,?)";
        PreparedStatement statement = conn.prepareStatement(query);
    
        statement.setString(1,s.get(i).getSupplyname());
        statement.setDouble(2,s.get(i).getSupplyPrice());
        statement.executeUpdate();
                     }
    
    } catch (SQLException e) {
        e.printStackTrace();
    }
             
             }
   
             public void addRevenueToDb(double totalBill){
                 try {
      PreparedStatement stmt = conn.prepareStatement("UPDATE revenue SET totalRevenue= totalRevenue+? WHERE revenueID=1;");
      stmt.setDouble(1,totalBill);
      int rowsUpdated = stmt.executeUpdate();
      if (rowsUpdated > 0) {
        System.out.println("Record updated successfully");
      } else {
        System.out.println("Record not found");
      }
      
    } catch (SQLException e) {
      e.getMessage();
    }
             }
             
             
         public void addCommentToDb(Customer c ,String commentBody,String commentType){
             
              try {
        String query = "INSERT INTO comment (commentType, commentBody, responseToComment, customerID) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, commentType);
        statement.setString(2, commentBody);
        statement.setString(3, "");
        statement.setInt(4, c.getCustID());
        int rowsInserted = statement.executeUpdate();
     
    } catch (SQLException e) {
        System.out.println("Failed to insert comment: " + e.getMessage());
    }
         }
         
         public List<String> getComplaintByCustID(int custID){
             complaint c = new complaint();
             int temp;
          List<String> myList = new ArrayList<>();
                 try {
  
        String query = "SELECT * FROM `comment`,`customer` WHERE comment.customerID=customer.customerPID AND customer.customerPID= ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, custID);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            myList.add(resultSet.getString("commentBody"));
            myList.add(resultSet.getString("commentType"));
             myList.add(resultSet.getString("responseToComment"));
            temp=resultSet.getInt("customerID");
            myList.add(temp+"");
            
            
        }
        resultSet.close();
        statement.close();
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
             
         
         return myList ;
         }
         
     
         
         
         
       
         public void addCompanyVehicleToDb(vehicle v){
             int vehicleId;
         addVehicle(v);
        vehicleId= getVehicleId(v);
              try {
        String query = "INSERT INTO companyvehicle (vehicleID , pricePerDay, isRented) VALUES (?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1,vehicleId);
        statement.setDouble(2,v.getPricePerday());
        statement.setBoolean(3,false);
        int rowsInserted = statement.executeUpdate();
     
    } catch (SQLException e) {
        System.out.println("Failed : " + e.getMessage());
    }
         
         }
         
         
       public ArrayList<vehicle> getVechileListFromDB() {
    ArrayList<vehicle> vehicleList = new ArrayList<>();
             
    try {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM `companyvehicle`,`vehicle` WHERE vehicle.vehicleID=companyvehicle.vehicleID");

        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            vehicle v = new vehicle();
            v.setVehicleName(rs.getString("Vehiclename"));
            v.setVehicleSubName(rs.getString("vehicleSubName"));
            v.setVehicleyear(rs.getInt("vehicleYear"));
            v.setPricePerday(rs.getDouble("pricePerDay"));
            v.setIsRented(rs.getBoolean("isRented"));
            vehicleList.add(v);
        }              
         
    } catch(SQLException ex) {
        System.out.println("failed "+ex.getMessage());
    }
         
    return vehicleList;   
}
public void deleteVechile(String vehicleName, String vechileSubName, int vechileYear){
 try {
            // Create a PreparedStatement to delete the vehicle with the given ID
            PreparedStatement statement = conn.prepareStatement("DELETE FROM vehicle WHERE vehicleName = ? AND vehicleSubName =? AND vehicleYear =?");
            statement.setString(1,vehicleName);
            statement.setString(2,vechileSubName);
            statement.setInt(3,vechileYear);
         
            
            // Execute the statement
            int rowsDeleted = statement.executeUpdate();
           
        } catch (SQLException e) {
            System.out.println("deleteVechile");
            e.printStackTrace();
            
        }
}

public void deletecompanyVehicle(String vehicleName, String vechileSubName, int vechileYear){
   int vehicleId= getVehicleIdByName(vehicleName,vechileSubName,vechileYear);
    
     try {
            // Create a PreparedStatement to delete the vehicle with the given ID
            PreparedStatement statement = conn.prepareStatement("DELETE FROM companyvehicle WHERE vehicleID =?");
            statement.setInt(1,vehicleId);
            
            // Execute the statement
            int rowsDeleted = statement.executeUpdate();
            deleteVechile(vehicleName,vechileSubName,vechileYear);
           
        } catch (SQLException e) {
              System.out.println("deletecompanyVehicle");
            e.printStackTrace();
          
        }


}

public int getVehicleIdByName(String vehicleName, String vechileSubName, int vechileYear){
int vechicleID=0;
     try {
        PreparedStatement ps = conn.prepareStatement("SELECT companyvehicle.vehicleID FROM companyvehicle, vehicle WHERE vehicle.vehicleID = companyvehicle.vehicleID AND vehicle.vehicleName = ? AND vehicle.vehicleSubName = ? AND vehicle.vehicleYear = ?");
        
        ps.setString(1,vehicleName);
        ps.setString(2,vechileSubName);
        ps.setInt(3, vechileYear);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            vechicleID=rs.getInt("vehicleID");
          
          
        }
       
        
    } catch (SQLException e) {
        
        System.err.println("failed" + e.getMessage());
    }
     return vechicleID ;
}


public ArrayList<vehicle> getAvaibleToRentCompanyvehicleFromDb(){
    ArrayList<vehicle> vehicleList = new ArrayList<>();
             
    try {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM `companyvehicle`,`vehicle` WHERE vehicle.vehicleID=companyvehicle.vehicleID AND isRented =false; ");

        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            vehicle v = new vehicle();
            v.setVehicleName(rs.getString("Vehiclename"));
            v.setVehicleSubName(rs.getString("vehicleSubName"));
            v.setVehicleyear(rs.getInt("vehicleYear"));
            v.setPricePerday(rs.getDouble("pricePerDay"));
            v.setIsRented(rs.getBoolean("isRented"));
            vehicleList.add(v);
        }              
         
    } catch(SQLException ex) {
        System.out.println("failed "+ex.getMessage());
    }
return vehicleList;
}
public void updateCompanyVehicleToRented(Appointment app){
    try  {
        String sql = "UPDATE companyvehicle "
                   + "SET isRented = true, rentedUntil = ? "
                   + "WHERE vehicleID IN ( "
                   + "    SELECT vehicle.vehicleID "
                   + "    FROM vehicle "
                   + "    INNER JOIN appointment ON vehicle.vehicleID = appointment.vehicleID "
                   + "    WHERE appointment.customerID = ? "
                   + ");";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
      
            ps.setDate(1, new java.sql.Date(app.getAppointmentDate().getTime()));
            ps.setInt(2, app.getCustomer().getCustID());
            
            int rowsAffected = ps.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        }
    } catch (SQLException e) {
        System.err.println("Error executing SQL: " + e.getMessage());
    }
}

     public void updateVehicleStatus() {
   
  
             
    try {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM `companyvehicle`,`vehicle` WHERE vehicle.vehicleID=companyvehicle.vehicleID AND companyvehicle.isRented=true;");

        ResultSet rs = ps.executeQuery();
        
     while (rs.next()) {
    vehicle v = new vehicle();
   // v.setVehicleID(rs.getInt("vehicleID "));
    v.setIsRented(rs.getBoolean("isRented"));
    Date rentedUntil = rs.getDate("rentedUntil");
    System.out.println(rentedUntil);

    if (rentedUntil != null) {
        Calendar cal = Calendar.getInstance(); 
        Date currentDate = cal.getTime();

        if (rentedUntil.before(currentDate) || rentedUntil.equals(currentDate)) {
         
            PreparedStatement updatePs = conn.prepareStatement("UPDATE companyvehicle SET isRented = false, rentedUntil = NULL WHERE vehicleID = ?");
            updatePs.setInt(1, rs.getInt("vehicleID"));
            updatePs.executeUpdate();
        }
    }
 
}
           
         
    } catch(SQLException ex) {
        System.out.println("failed "+ex.getMessage());
    }
     }


public Map<String, String> getComaplaintList(){
 
     Map<String, String> complaintlist = new HashMap<>();
 try {
        PreparedStatement ps = conn.prepareStatement("SELECT user.name ,comment.commentBody FROM `comment` ,customer,user\n" +
"WHERE\n" +
"comment.customerID=customer.customerPID\n" +
"AND\n" +
"user.PID=customer.userPID\n" +
"AND \n" +
"commentType='complaint' AND comment.responseToComment='';");

        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
          complaintlist.put(rs.getString("name"), rs.getString("commentBody"));
           
         
        }              
         
    } catch(SQLException ex) {
        System.out.println("failed "+ex.getMessage());
    }
 return complaintlist;
}

public int getComplaintId(String commentBody) {
    int complaintId = 0;
    try {
        PreparedStatement ps = conn.prepareStatement("SELECT comment.commentId FROM comment WHERE commentBody=?;");
        ps.setString(1, commentBody);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            complaintId = rs.getInt("commentId");
        }
    } catch (SQLException ex) {
        System.out.println("failed " + ex.getMessage());
    }
    return complaintId;
}


public void setResponseToComment(int commentId,String responseBody){

 try  {
        String sql = "UPDATE comment SET responseToComment=? WHERE commentId=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
      
            ps.setString(1,responseBody);
            ps.setInt(2,commentId);
            
            int rowsAffected = ps.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        }
    } catch (SQLException e) {
        System.err.println("Error executing SQL: " + e.getMessage());
    }

}

public Map<String, Double> getSuppFromDB() {
    
    Map<String, Double> suppMap = new HashMap<>();

    try {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM supply");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String supplyId = rs.getString("supplyName");
            double suppPrice = rs.getDouble("supplyPrice");
            suppMap.put(supplyId, suppPrice);
        }
    } catch(SQLException ex) {
        System.out.println("failed "+ex.getMessage());
    }

    return suppMap;
}

public ArrayList<vehicle> getRentedVechileListFromDB(){

    ArrayList<vehicle> rentedVehicleList = new ArrayList<>();
             
    try {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM `companyvehicle`,`vehicle` WHERE vehicle.vehicleID=companyvehicle.vehicleID AND isRented =true; ");

        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            vehicle v = new vehicle();
            v.setVehicleName(rs.getString("Vehiclename"));
            v.setVehicleSubName(rs.getString("vehicleSubName"));
            v.setVehicleyear(rs.getInt("vehicleYear"));
            v.setPricePerday(rs.getDouble("pricePerDay"));
            v.setIsRented(rs.getBoolean("isRented"));
            rentedVehicleList.add(v);
        }              
         
    } catch(SQLException ex) {
        System.out.println("failed "+ex.getMessage());
    }
return rentedVehicleList;



}
public Date getRentedUntilVehicle(int vehicleId){
    Date rentedUntil=null;
             
    try {
        PreparedStatement ps = conn.prepareStatement("SELECT rentedUntil\n" +
"FROM companyvehicle\n" +
"INNER JOIN vehicle ON vehicle.vehicleID = companyvehicle.vehicleID\n" +
"WHERE vehicle.vehicleID = ?;" );

        ps.setInt(1, vehicleId);

        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            rentedUntil=rs.getDate("rentedUntil");
        }              
         
    } catch(SQLException ex) {
        System.out.println("failed "+ex.getMessage());
    }
    return rentedUntil;
}


public ArrayList<Appointment> getAllAppDate(){
ArrayList<Appointment> appList= new ArrayList<>();
           
    try {
        PreparedStatement ps = conn.prepareStatement("Select * FROM appointment;" );
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            
         Appointment app= new Appointment();
         app.setAppID(rs.getInt("appId"));
         app.setAppointmentDate(rs.getDate("appDate"));
         app.setAppointmentTime(rs.getDate("appTime"));
                 appList.add(app);
          
        }              
         
    } catch(SQLException ex) {
        System.out.println("failed "+ex.getMessage());
    }
    
        return appList;
}

public double getRevenue(){
    double revenue = 0;

    try {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM `revenue`");
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            revenue = rs.getDouble("totalRevenue");
        }
    } catch(SQLException ex) {
        System.out.println("failed "+ex.getMessage());
    }

    return revenue;
}

public ArrayList<feedBack> getFeedBackList(){
    ArrayList<feedBack> feedbackList= new ArrayList<>();
     try {
        PreparedStatement ps = conn.prepareStatement("SELECT commentBody from \n" +
"comment\n" +
    "WHERE commentType='feedback';");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            feedBack f = new feedBack();
            f.setCommentBody(rs.getString("commentBody"));
            feedbackList.add(f);
        }
    } catch(SQLException ex) {
        System.out.println("failed "+ex.getMessage());
    }
   return feedbackList;
}

public void updateCustName(int id,String newuserName){
           try {
           
      PreparedStatement stmt = conn.prepareStatement("UPDATE account \n" +
"SET userName=?\n" +
"WHERE userPID=?;");
       stmt.setString(1,newuserName);
      stmt.setInt(2,id);
      int rowsUpdated = stmt.executeUpdate();
      if (rowsUpdated > 0) {
        System.out.println("Record updated successfully");
      } else {
        System.out.println("Record not found");
      }
      
    } catch (SQLException e) {
      e.getMessage();
    }
}


public void updatePass(int id , String newPass){
 try {
             
      PreparedStatement stmt = conn.prepareStatement("UPDATE account \n" +
"SET pass=?\n" +
"WHERE userPID=?;");
       stmt.setString(1,newPass);
      stmt.setInt(2,id);
      int rowsUpdated = stmt.executeUpdate();
      if (rowsUpdated > 0) {
        System.out.println("Record updated successfully");
      } else {
        System.out.println("Record not found");
      }
      
    } catch (SQLException e) {
      e.getMessage();
    }


}

public final ArrayList<Offer> updateOffers() {
    //SwingUtilities.invokeLater(() -> {
       // OfferPanel.removeAll(); // Clear the previous offers from the panel
         Offer o=null;
         ArrayList<Offer> OfferList=new ArrayList<>();
        String sql = "SELECT * FROM offer";
        try (PreparedStatement statement =conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            LocalDate currentDate = LocalDate.now(); // Get the current date
            while (resultSet.next()) {
                int offerId = resultSet.getInt("offer_id");
                String offerMessage = resultSet.getString("offer_message");
                LocalDate startDate = resultSet.getDate("start_date").toLocalDate();
                LocalDate endDate = resultSet.getDate("end_date").toLocalDate();

                // Check if the offer end date has not passed the current date
                if (!endDate.isBefore(currentDate)) {
                    String formattedStartDate = startDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
                    String formattedEndDate = endDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
                     o = new Offer (offerMessage,startDate,endDate);
                     OfferList.add(o);

                    
                }
            }
        } catch (SQLException ex) {
        }
         return OfferList;}

public final Offer getLatestOffer() {
        // Connect to the database

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM offer ORDER BY start_date DESC LIMIT 1");
            rs = stmt.executeQuery();
            if (rs.next()) {
                LocalDate startDate = rs.getDate("start_date").toLocalDate();
                LocalDate endDate = rs.getDate("end_date").toLocalDate();
                String offerMessage = rs.getString("offer_message");
                return new Offer(offerMessage, startDate, endDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return null;
}

public Boolean isRegisterCust(String name , String pass){
    String nameDb="",passDb="";
    boolean isRegistered=false;

     try {
        PreparedStatement ps = conn.prepareStatement("SELECT account.userName,account.pass FROM account,`customer`,user \n" +
"WHERE\n" +
"user.PID=account.userPID\n" +
"AND\n" +
"user.PID=customer.userPID\n" +
"AND\n" +
"account.userName=?\n" +
"AND\n" +
"account.pass=?;");
        ps.setString(1, name);
        ps.setString(2, pass);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
           nameDb=rs.getString("userName");
           passDb=rs.getString("pass");
            if(nameDb.compareTo("")!=0&&passDb.compareTo("")!=0){
            isRegistered= true;
        }
        }
       
    } catch(SQLException ex) {
        System.out.println("failed "+ex.getMessage());
    }
     
     
return isRegistered;
}
//
//
////test cases 
//@Test
//public void testAddOfferToDatabase() throws SQLException {
// 
//  Offer  offer = new Offer("Get 10% off your first rental!", LocalDate.of(2023, 5, 1), LocalDate.of(2023, 5, 31));
//
//   Connection  conn = mock(Connection.class);
//
//
//    PreparedStatement stmt = mock(PreparedStatement.class);
//
//    when(conn.prepareStatement(anyString())).thenReturn(stmt);
//
//
//    verify(conn).prepareStatement("INSERT INTO offer (offer_message, start_date, end_date) VALUES (?, ?, ?)");
//
//
//    verify(stmt).setString(1, "Get 10% off your first rental!");
//    verify(stmt).setDate(2, java.sql.Date.valueOf(LocalDate.of(2023, 5, 1)));
//    verify(stmt).setDate(3, java.sql.Date.valueOf(LocalDate.of(2023, 5, 31)));
//
//    verify(stmt).executeUpdate();
//}
//
//@Test
//public void testUserType() throws SQLException {
//
//    ResultSet rs = mock(ResultSet.class);
//    when(rs.next()).thenReturn(true, false);
//    when(rs.getInt("userPID")).thenReturn(1);
//
//  
//    PreparedStatement stmt1 = mock(PreparedStatement.class);
//    when(stmt1.executeQuery()).thenReturn(rs);
//
//    PreparedStatement stmt2 = mock(PreparedStatement.class);
//    when(stmt2.executeQuery()).thenReturn(rs);
//
//    PreparedStatement stmt3 = mock(PreparedStatement.class);
//    when(stmt3.executeQuery()).thenReturn(rs);
//
//  
//    Connection conn = mock(Connection.class);
//    when(conn.prepareStatement(anyString())).thenReturn(stmt1, stmt2, stmt3);
//
//  
//    database myClass = new database();
//
//    
//    assertEquals("customer", myClass.userType("username", "password"));
//}
//
//    
//}
}