public abstract class AbstractModule extends AbstractProduct {
    public AbstractModule(String name) {
        super(name);
    }
    @Override
    public void displayInfo() {
        System.out.println("Module: " + name);
        System.out.println("Total Price: $" + getPrice());
        System.out.println("Total Duration: " + getDuration() + " hours");
        System.out.println("Products:");
        for (Product product : products) {
            product.displayInfo();
            System.out.println();
        }
    }
    
}
