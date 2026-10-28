import java.util.Map;

public class CouponRegistry {

    private final Map<String, DiscountStrategy> strategiesByCode;

    public CouponRegistry(Map<String, DiscountStrategy> strategiesByCode) {
        this.strategiesByCode = Map.copyOf(strategiesByCode);
    }

    public DiscountStrategy forCode(String couponCode) {
        DiscountStrategy strategy = strategiesByCode.get(couponCode);
        if (strategy == null) {
            throw new InvalidCouponException(couponCode);
        }
        return strategy;
    }
}
