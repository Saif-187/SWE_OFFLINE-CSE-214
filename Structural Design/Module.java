import java.util.ArrayList;

public class Module extends AbstractModule {
    public Module(String name) {
        super(name);
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
