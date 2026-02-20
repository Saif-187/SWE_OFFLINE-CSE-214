public class DevelopingCountry extends DiscountCart {
    private final static double discountRate = 10.0;

    public DevelopingCountry(CartInterface cart) {
        super(cart,discountRate);
    }

    @Override
    protected boolean isEligibleForDiscount() {
        return super.isFromDevelopingCountry() && !cart.getItems().isEmpty();
    }
    
}
