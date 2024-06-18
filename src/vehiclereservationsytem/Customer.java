package vehiclereservationsytem;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class Customer extends User implements Observer {
    private int custID;
    private payment p ;

    private static Set<Integer> usedIds = new HashSet<>();
    public Customer(String name, String phone, String address, String email, String username, String password) {
        super(name, phone, address, email, createAccount(username, password));
        this.custID = generateId();
    }
    public Customer(int custId){
    this.custID=custId;
    }

    private static Account createAccount(String username, String password) {
        return new Account(username, password);
    }
    
public void pay(){
    p= new payment();

}
    
    public int getCustID() {
        return custID;
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

    @Override
    public String toString() {
        return "Customer{" + "custID=" + custID + '}';
    }
    public void setCustID(int custID) {
        this.custID = custID;
    }
     @Override
    public void update(String message) {
//        // Retrieve the most recent offer from the database based on its start date
//        Offer latestOffer = null;
//        try {
//            latestOffer = db.getLatestOffer();
//        } catch (ParseException ex) {
//            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        // Check if the current date is between the start and end date of the offer
//        if (latestOffer != null && isWithinOfferPeriod(latestOffer)) {
//            myaccount.displayNewOffer(latestOffer.getOfferMessage());
//        }
    }
//            private boolean isWithinOfferPeriod(Offer offer) {
//        LocalDate currentDate = LocalDate.now();
//        return currentDate.isAfter(offer.getStartDate()) && currentDate.isBefore(offer.getEndDate());
    }


