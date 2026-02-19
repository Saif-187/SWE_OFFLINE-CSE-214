import java.util.ArrayList;

public class Course extends AbstractProduct {

    public Course(String name) {
        super(name);
    }

    @Override
    public void displayInfo() {
        System.out.println("Course: " + name);
        System.out.println("Total Price: $" + getPrice());
        System.out.println("Total Duration: " + getDuration() + " hours");
        System.out.println("Lessons:");
        for (Product lesson : products) {
            lesson.displayInfo();
            System.out.println();
        }
    }
    public void addLesson(Product lesson) {
        if(lesson instanceof Lesson) {
            addProduct(lesson);
        }
        else{
            throw new IllegalArgumentException("Only Lesson components can be added to a Course.");
        }
    }
    public ArrayList<Product> getLessons() {
        return products;
    }
    public void removeLesson(Product lesson) {
        removeProduct(lesson);
    }

    
}
