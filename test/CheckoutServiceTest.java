import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckoutServiceTest {

    @Test
    void appliesCouponAsDiscountOffSubtotalAcrossRepeatedAdds() {
        Cart cart = new Cart();
        Item pen = new Item("pen", 999);
        cart.add(pen, 1);
        cart.add(pen, 2);
        CheckoutService checkoutService = new CheckoutService(
                new CouponRegistry(Map.of("SAVE-10", new CouponDiscountStrategy(10))),
                new EmailNotificationService());

        Order order = checkoutService.checkout(cart, new User("u1", "A", "a@example.com"), "SAVE-10", false);

        // 2997 * 10% = 299.7, truncated to 299.
        assertEquals(2997, order.subTotalMinor());
        assertEquals(299, order.discountMinor());
        assertEquals(2698, order.totalMinor());
    }
}
