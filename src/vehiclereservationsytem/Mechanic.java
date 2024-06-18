package vehiclereservationsytem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Mechanic extends User {
    private static Set<Integer> usedIds = new HashSet<>();
    private String role;
    private double salary;
    private supply s ; 
    private payment p ;
    private int mechanicID;
    //private int userID;
        public Mechanic(int mechanicID) {
//        try {
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclereservationsystem?", "root", "");
//
//            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM mechanic WHERE mechanicID=?");
//            stmt.setInt(1, mechanicID);
//
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                this.mechanicID = rs.getInt("mechanicID");
//                this.role = rs.getString("role");
//                this.salary = rs.getDouble("salary");
//                this.userID = rs.getInt("userID");
//            } else {
//                throw new SQLException("Mechanic not found with ID: " + mechanicID);
//            }
//
//            conn.close();
//        } catch (SQLException ex) {
//            // Handle SQL exception
//            ex.printStackTrace();
//        }
    }
    public Mechanic(String name, String phone, String address, String email, String password, String role, double salary) {
        super(name, phone, address, email, createAccount(name, password));
        this.role = role;
        this.salary = salary;
     this.mechanicID = generateId();
    }
    private static Account createAccount(String username, String password) {
        return new Account(username, password);
    }
        public Mechanic(int id,String name, String phone, String address, String email, String password, String role, double salary) {
        super(name, phone, address, email, createAccount(name, password));
        this.role = role;
        this.salary = salary;
     this.mechanicID = id;
    }
    public void makePayment(){
        p=new payment();
    
    }
    public void createOffer(String offerMessage, List<Customer> customers,LocalDate StartDate,LocalDate EndDate) {
        // Create and publish the offer message
        Offer offer = new Offer(StartDate,EndDate);
        offer.setOfferMessage(offerMessage);
        
        // Notify all the customers of the new offer
        for (Customer customer : customers) {
            offer.addObserver(customer);
        }
        offer.updateAll(offerMessage);
    }
    
    
    public void createSupply(String suppName, double suppPrice){
       s= new supply(suppName,suppPrice);
    
    }
    // getters and setters
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public double getSalary() {
        return salary;
    }
    
    public void setSalary(double salary) {
        this.salary = salary;
    }

    public supply getS() {
        return s;
    }

    public payment getP() {
 
       return p;
    }

    @Override
    public String toString() {
        return "Mechanic{" + "role=" + role + ", salary=" + salary + ", s=" + s + ", p=" + p + ", mechanicID=" + mechanicID + '}';
    }

   
    

   
     private int generateId() {
        int id;
        Random random = new Random();
        do {
            id = random.nextInt(1000000);
        } while (usedIds.contains(id));
        usedIds.add(id);
        return id;
    }
}

