package vehiclereservationsytem;
public class User {
    private static int latestId = 0;
    private int id;
    private String name;
    private String phone;
    private String address;
    private String email;
    private Account account;
    database db= new database();
  
    public User(String name, String phone, String address, String email,Account account ) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.account = account;
         this.id = db.getLatestuserId();
       
         
         
      
    }
    
  public void saveUser(){
     
      db.saveUser(id,name,phone,address, email,account);
       db.saveAccount(account , id);
  
  }

    public User() {
        this.id = ++latestId;
    }
    
    // getters and setters
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Account getAccount() {
        return account;
    }
    
    public void setAccount(Account account) {
        this.account = account;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", phone=" + phone + ", address=" + address + ", email=" + email + ", account=" + account + '}';
    }
    
}