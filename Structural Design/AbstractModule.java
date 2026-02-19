import java.util.ArrayList;
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
    public void addCourse(Product product) {
        if(product instanceof Course) {
            addProduct(product);
        }
        else{
            throw new IllegalArgumentException("Only Course components can be added to a Module.");
        }
    }
    public ArrayList<Product> getCourses() {
        return products;
    }
    public void removeCourse(Product product) {
        removeProduct(product);
    }
}
