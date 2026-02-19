import java.util.ArrayList;
public abstract class AbstractProduct implements Product {
    protected final String name;
    protected final ArrayList<Product> products;
    public AbstractProduct(String name) {
        this.name = name;
        this.products = new ArrayList<>();
    }
    public void addProduct(Product product) {
        products.add(product);
    }
    public void removeProduct(Product product) {
        products.remove(product);
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public double getPrice() {
        double totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }
    @Override
    public double getDuration() {
        double totalDuration = 0;
        for (Product product : products) {
            totalDuration += product.getDuration();
        }
        return totalDuration;
    }
    @Override
    public abstract void displayInfo() ;
}
