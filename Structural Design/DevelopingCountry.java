public class DevelopingCountry extends DiscountCart {
    private final static double discountRate = 10.0;

    public DevelopingCountry(CartInterface cart) {
        super(cart,discountRate);
    }

    @Override
    protected boolean isEligibleForDiscount() {
    // Only apply discount if cart has items
        return super.isFromDevelopingCountry() && !cart.getItems().isEmpty();
    }
    
}
