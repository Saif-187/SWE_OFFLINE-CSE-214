import java.util.ArrayList;

public class Cart implements CartInterface {
    private ArrayList<Product> items;
    private boolean isFromDevelopingCountry=false;
    public Cart() {
        this.items = new ArrayList<>();
    }
    public void addItem(Product product) {
        if(!(product instanceof Module || product instanceof Course || product instanceof Lesson)) {
            System.out.println("Only lessons,courses,modules can be added to the cart.");
            return;
        }
        items.add(product);
    }
    public void removeItem(Product product) {
        items.remove(product);
    }
    public double getTotalPrice() {
        double totalPrice = 0;
        for (Product item : items) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }
    public double getTotalDuration() {
        double totalDuration = 0;
        for (Product item : items) {
            totalDuration += item.getDuration();
        }
        return totalDuration;
    }
    public void displayCart() {
        System.out.println("Cart Contents:");
        for (Product item : items) {
            item.displayInfo();
            System.out.println();
        }
        System.out.println("Total Price: $" + getTotalPrice());
        System.out.println("Total Duration: " + getTotalDuration() + " hours");
    }
    public int totalModules(){
        int count = 0;
        for (Product item : items) {
            if (item instanceof Module) {
                count++;
            }
        }
        return count;
    }
    public boolean isFromDevelopingCountry() {
        return isFromDevelopingCountry;
    }
    public void setFromDevelopingCountry(boolean fromDevelopingCountry) {
        isFromDevelopingCountry = fromDevelopingCountry;
    }
    public ArrayList<Product> getItems() {
        return items;
    }
    public void setItems(ArrayList<Product> items) {
        this.items = items;
    }
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
        receiptCart.displayCart();
        
        System.out.println("=".repeat(50));
        System.out.println("Thank you for your purchase!");
        System.out.println("=".repeat(50));
    }
}
