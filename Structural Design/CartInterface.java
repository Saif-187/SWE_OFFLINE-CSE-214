import java.util.ArrayList;
public interface CartInterface {
    void addItem(Product product);
    void removeItem(Product product);
    double getTotalPrice();
    double getTotalDuration();
    void displayCart();
    int totalModules();
    boolean isFromDevelopingCountry();
    void setFromDevelopingCountry(boolean fromDevelopingCountry);
    ArrayList<Product> getItems();
    void clearCart();
}