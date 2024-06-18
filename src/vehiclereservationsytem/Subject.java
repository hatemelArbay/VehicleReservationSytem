package vehiclereservationsytem;

public interface Subject {
    void addObserver(Customer customer);
    void removeObserver(Customer customer);
    void updateAll(String message);
}
