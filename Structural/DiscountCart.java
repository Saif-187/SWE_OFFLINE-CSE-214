import java.util.ArrayList;
public abstract class DiscountCart implements CartInterface {
    protected CartInterface cart;
    private final double discountRate;
    
    public DiscountCart(CartInterface cart, double discountRate) {
        this.cart = cart;
        this.discountRate = discountRate;
    }
    @Override
    public String getName() {
        return "Duration Discount";
    }
    protected abstract boolean isEligibleForDiscount();
    
    public double effectiveDiscount() {
        if (isEligibleForDiscount()) {
            return discountRate;
        }
        return 0.0;
    }
    
    @Override
    public double getPrice() {
        double total = cart.getPrice();
        if (isEligibleForDiscount()) {
            total -= effectiveDiscount();
        }
        return total;
    }
    
    @Override
    public void displayInfo() {
        cart.displayInfo();
        if (isEligibleForDiscount()) {
            System.out.println("Discount applied: $" + effectiveDiscount());
            System.out.println("Total after discount: $" + getPrice());
        } else {
            System.out.println("No discount applied.");
        }
    }
    
    @Override
    public void addItem(Product product) {
        cart.addItem(product);
    }
    
    @Override
    public void removeItem(Product product) {
        cart.removeItem(product);
    }
    
    @Override
    public double getDuration() {
        return cart.getDuration();
    }
    
    @Override
    public int totalModules() {
        return cart.totalModules();
    }
    
    @Override
    public boolean isFromDevelopingCountry() {
        return cart.isFromDevelopingCountry();
    }
    
    @Override
    public void setFromDevelopingCountry(boolean fromDevelopingCountry) {
        cart.setFromDevelopingCountry(fromDevelopingCountry);
    }
    
    @Override
    public ArrayList<Product> getItems() {
        return cart.getItems();
    }
    
    @Override
    public void clearCart() {
        cart.clearCart();
    }
}