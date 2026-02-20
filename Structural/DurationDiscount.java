public class DurationDiscount extends DiscountCart{
    private final static double discountRate = 12.0;
    private final static double durationThreshold = 5.0;
    public DurationDiscount(CartInterface cart) {
        super(cart,discountRate);
    }
    @Override
    protected boolean isEligibleForDiscount() {
        return !cart.getItems().isEmpty() && super.getDuration() >= durationThreshold;
    }
}
