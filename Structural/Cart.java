import java.util.ArrayList;

public class Cart implements CartInterface {
    private ArrayList<Product> items;
    private boolean isFromDevelopingCountry=false;
    public Cart() {
        this.items = new ArrayList<>();
    }
    @Override
    public String getName() {
        return "Shopping Cart";
    }
    @Override
    public void addItem(Product product) {
        if(!(product instanceof Course || product instanceof Lesson || product instanceof AbstractModule)) {
            System.out.println("Only lessons,courses,modules can be added to the cart.");
            return;
        }
        items.add(product);
    }
    @Override
    public void removeItem(Product product) {
        items.remove(product);
    }
    @Override
    public double getPrice() {
        double totalPrice = 0;
        for (Product item : items) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }
    @Override
    public double getDuration() {
        double totalDuration = 0;
        for (Product item : items) {
            totalDuration += item.getDuration();
        }
        return totalDuration;
    }
    @Override
    public void displayInfo() {
        System.out.println("Cart Contents:");
        for (Product item : items) {
            item.displayInfo();
            System.out.println();
        }
        System.out.println("Total Price: $" + getPrice());
        System.out.println("Total Duration: " + getDuration() + " hours");
    }
    @Override
    public int totalModules(){
        int count = 0;
        for (Product item : items) {
            if (item instanceof Module) {
                count++;
            }
        }
        return count;
    }
    @Override
    public boolean isFromDevelopingCountry() {
        return isFromDevelopingCountry;
    }
    @Override
    public void setFromDevelopingCountry(boolean fromDevelopingCountry) {
        isFromDevelopingCountry = fromDevelopingCountry;
    }
    @Override
    public ArrayList<Product> getItems() {
        return items;
    }
    public void setItems(ArrayList<Product> items) {
        this.items = items;
    }
    @Override
    public void clearCart() {
        items.clear();
    }
    public void generateReceipt() {
        System.out.println("=".repeat(50));
        System.out.println("RECEIPT");
        System.out.println("=".repeat(50));
        CartInterface receiptCart = this;
        receiptCart = new DevelopingCountry(receiptCart);
        receiptCart = new DurationDiscount(receiptCart);
        receiptCart = new MultiModule(receiptCart);
        receiptCart.displayInfo();
        
        System.out.println("=".repeat(50));
        System.out.println("Thank you for your purchase!");
        System.out.println("=".repeat(50));
    }
}
