import java.util.ArrayList;
public class Module implements Product {
    private String name;
    private ArrayList<Product> courses;

    public Module(String name) {
        this.name = name;
        this.courses = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        double totalPrice = 0;
        for (Product product : courses) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    @Override
    public double getDuration() {
        double totalDuration = 0;
        for (Product product : courses) {
            totalDuration += product.getDuration();
        }
        return totalDuration;
    }

    @Override
    public void displayInfo() {
        System.out.println("Module: " + name);
        System.out.println("Total Price: $" + getPrice());
        System.out.println("Total Duration: " + getDuration() + " hours");
        System.out.println("Products:");
        for (Product product : courses) {
            product.displayInfo();
            System.out.println();
        }
    }

    public void addCourse(Product product) {
        courses.add(product);
    }

    public ArrayList<Product> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Product> courses) {
        this.courses = courses;
    }

    public void removeCourse(Product product) {
        courses.remove(product);
    }
    
}
