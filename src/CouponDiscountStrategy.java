public class CouponDiscountStrategy implements DiscountStrategy {

    private final int percent;

    public CouponDiscountStrategy(int percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("percent must be in [0, 100]: " + percent);
        }
        this.percent = percent;
    }

    @Override
    public long discountMinor(long subTotalMinor) {
        // Integer division truncates, so the discount never exceeds the advertised percent.
        return Math.multiplyExact(subTotalMinor, percent) / 100;
    }
}
