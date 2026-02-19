import java.util.ArrayList;

public class Course implements Product {
    private String name;
    private ArrayList<Lesson> lessons ;

    public Course(String name) {
        this.name = name;
        this.lessons = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        double totalPrice = 0;
        for (Product lesson : lessons) {
            totalPrice += lesson.getPrice();
        }
        return totalPrice;
    }

    @Override
    public double getDuration() {
        double totalDuration = 0;
        for (Product lesson : lessons) {
            totalDuration += lesson.getDuration();
        }
        return totalDuration;
    }

    @Override
    public void displayInfo() {
        System.out.println("Course: " + name);
        System.out.println("Total Price: $" + getPrice());
        System.out.println("Total Duration: " + getDuration() + " hours");
        System.out.println("Lessons:");
        for (Product lesson : lessons) {
            lesson.displayInfo();
            System.out.println();
        }
    }
    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }
    public ArrayList<Lesson> getLessons() {
        return lessons;
    }
    public void setLessons(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }
    public void removeLesson(Lesson lesson) {
        lessons.remove(lesson);
    }

    
}
