package vehiclereservationsytem;

public class Account {
    private static int latestId = 0;
    private int userID;
    private String username;
    private String password; 
    database db=new database();

    
    public Account(String username, String password) {
      //  this.userID = db.getLatestAccountId();
        this.username = username;
        this.password = password;
        
      
    }
    
    // getters and setters
    public int getUserID() {
        return userID;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" + "userID=" + userID + ", username=" + username + ", password=" + password + '}';
    }
    

}