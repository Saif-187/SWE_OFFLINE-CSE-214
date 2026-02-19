public class Lesson implements Product {
    private String name;
    private double price;
    private double duration; 

    public Lesson(String name, double price, double duration) {
        this.name = name;
        this.price = price;
        this.duration = duration;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public double getDuration() {
        return duration;
    }

    @Override
    public void displayInfo() {
        System.out.println("Lesson: " + name);
        System.out.println("Price: $" + price);
        System.out.println("Duration: " + duration + " hours");
    }
    
}
