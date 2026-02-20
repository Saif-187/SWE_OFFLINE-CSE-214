public class MultiModule extends DiscountCart {
    private final static double discountRate = 15.0;
    private static final int moduleThreshold = 2;
    public MultiModule(CartInterface cart) {
        super(cart, discountRate);
    }

    @Override
    protected boolean isEligibleForDiscount() {
        return !cart.getItems().isEmpty() && super.totalModules() >= moduleThreshold;
    }
    
}
