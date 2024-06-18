package vehiclereservationsytem;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
public class Appointment {
    private int appID;
    private Customer customer;
    private service serv;
    private Date appointmentTime;
    private Date appointmentDate;
    database db=new database();
    private static Set<Integer> usedIds = new HashSet<>();
    
    public Appointment(Customer customer, Date appointmentTime, Date appointmentDate,service serv) {
        this.customer = customer;
        this.appointmentTime = appointmentTime;
        this.appointmentDate = appointmentDate;
        this.appID = generateId();
        this.serv=serv;
    }
    
    
    public Appointment(){}
    
  
    
    
    private int generateId() {
        int id;
        Random random = new Random();
        do {
            id = random.nextInt(1000000);
        } while (usedIds.contains(id));
        usedIds.add(id);
        return id;
    }
    // getters and setters
    public Customer getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public Date getAppointmentTime() {
        return appointmentTime;
    }
    
    public void setAppointmentTime(Date appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
    
    public Date getAppointmentDate() {
        return appointmentDate;
    }
    
    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
    public int getAppID() {
        return appID;
    }
    
    public void setAppID(int appID) {
        this.appID = appID;
    }
    
    //update also service type 
    public void bookAppointment() {
        Appointment app =new Appointment(customer, appointmentTime, appointmentDate,serv);
        db.saveAppointment(app);
    }
    
    public void cancelAppointment() {
        db.deleteAppointment(appID);
    }
    
    public void editAppointment() {
        Appointment app =new Appointment(customer, appointmentTime, appointmentDate,serv);
        db.updateAppointment(app);
    }
    
//    public void displayAppointmentList() {
//        db.getAllAppointments();
//    }

    @Override
    public String toString() {
        return "Appointment{" + "appID=" + appID + ", customer=" + customer + ", serv=" + serv + ", appointmentTime=" + appointmentTime + ", appointmentDate=" + appointmentDate + '}';
    }

    public service getSevice() {
        return serv;
    }


    
}
