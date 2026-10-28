import java.util.UUID;

public class CheckoutService {

    private static final DiscountStrategy NO_DISCOUNT = subTotalMinor -> 0;

    private final CouponRegistry couponRegistry;
    private final NotificationService notificationService;

    public CheckoutService(CouponRegistry couponRegistry, NotificationService notificationService) {
        this.couponRegistry = couponRegistry;
        this.notificationService = notificationService;
    }

    public Order checkout(Cart cart, User user, boolean notify) {
        return placeOrder(cart, user, NO_DISCOUNT, notify);
    }

    public Order checkout(Cart cart, User user, String couponCode, boolean notify) {
        return placeOrder(cart, user, couponRegistry.forCode(couponCode), notify);
    }

    private Order placeOrder(Cart cart, User user, DiscountStrategy discount, boolean notify) {
        if (cart.isEmpty()) {
            throw new IllegalStateException("cannot checkout an empty cart");
        }
        long subTotal = cart.subTotalMinor();
        long discountAmount = discount.discountMinor(subTotal);
        Order order = new Order(UUID.randomUUID().toString(), user, subTotal, discountAmount, subTotal - discountAmount);
        if (notify) {
            notificationService.orderPlaced(order);
        }
        return order;
    }
}
