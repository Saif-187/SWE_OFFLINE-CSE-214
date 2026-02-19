public class PracticeSet extends DecoModule {
    private static final double cost=10.0;

    public PracticeSet(AbstractModule module) {
        super(module);
    }

    @Override
    public double getPrice() {
        return module.getPrice() + cost;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Price increased from " + (getPrice() - cost) + " to " + getPrice() + " (includes practice set)");
    }
}
