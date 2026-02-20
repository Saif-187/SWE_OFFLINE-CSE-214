public class MentorSupport extends DecoModule {
    private static final double cost=20.0;

    public MentorSupport(AbstractModule module) {
        super(module);
    }

    @Override
    public double getPrice() {
        return module.getPrice() + cost;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Price increased from " + (getPrice() - cost) + " to " + getPrice() + " (includes mentor support)");
    }
    
}
