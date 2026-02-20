import java.util.ArrayList;
public interface CartInterface extends Product {
    void addItem(Product product);
    void removeItem(Product product);
    int totalModules();
    boolean isFromDevelopingCountry();
    void setFromDevelopingCountry(boolean fromDevelopingCountry);
    ArrayList<Product> getItems();
    void clearCart();
}