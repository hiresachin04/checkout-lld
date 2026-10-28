public class InvalidCouponException extends RuntimeException {

    public InvalidCouponException(String couponCode) {
        super("invalid coupon code: " + couponCode);
    }
}
