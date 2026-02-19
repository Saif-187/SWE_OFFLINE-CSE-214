public abstract class DecoModule extends Module {
    protected Module module;

    public DecoModule(Module module) {
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
