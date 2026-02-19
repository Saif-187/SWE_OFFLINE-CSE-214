public abstract class DecoModule extends AbstractModule {
    protected AbstractModule module;

    public DecoModule(AbstractModule module) {
        super(module.getName());
        this.module = module;
    }

    @Override
    public String getName() {
        return module.getName();
    }

    @Override
    public double getPrice() {
        return module.getPrice();
    }

    
    @Override
    public double getDuration() {
        return module.getDuration();
    }

    
    @Override
    public void displayInfo() {
        module.displayInfo();
    }
    
}
