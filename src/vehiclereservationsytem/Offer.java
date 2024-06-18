package vehiclereservationsytem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Offer implements Subject {
    private String offerMessage;
    private List<Observer> observers = new ArrayList<>();
    private ArrayList<Offer> offers;
    private LocalDate startDate;
    private LocalDate endDate;

    public ArrayList<Offer> getOffers() {
        return offers;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
    public Offer(){}
public Offer(LocalDate startDate, LocalDate endDate) {
    this.offers = new ArrayList<>();
    this.startDate = startDate;
    this.endDate = endDate;
}
public Offer(String message, LocalDate startDate, LocalDate endDate) {
    this.offerMessage=message;
    this.offers = new ArrayList<>();
    this.startDate = startDate;
    this.endDate = endDate;
}

    public void setOfferMessage(String offerMessage) {
        this.offerMessage = offerMessage;
        updateAll(this.offerMessage);
    }
    
    
    @Override
    public void addObserver(Customer customer) {
    observers.add(customer);
}


    @Override
    public void removeObserver(Customer customer) {
        observers.remove(customer);
    }

    @Override
    public void updateAll(String message) {
        offerMessage = message;
        for (Observer observer : observers) {
            observer.update(offerMessage);
        }
    }

public void addOffer(Offer offer) {
    if (offer != null) {
        offers.add(offer);
        updateAll("New offer added: " + offer.toString());
    }
}

public ArrayList<Offer> getActiveOffers() {
    ArrayList<Offer> activeOffers = new ArrayList<>();
        
    for (Offer offer : offers) {
        if (offer.isActive()) {
            activeOffers.add(offer);
            updateAll("Offer now active: " + offer.toString());
        }
    }
        
    return activeOffers;
}

    
    public String getOfferMessage() {
     
        
        return offerMessage;
    }
    
    public boolean isActive() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.isAfter(startDate) && currentDate.isBefore(endDate);
    }

    @Override
    public String toString() {
        return "Offer{" + "offerMessage=" + offerMessage + ", startDate=" + startDate + ", endDate=" + endDate + '}';
    }
    
  
}

