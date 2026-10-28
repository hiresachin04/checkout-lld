void main() {
    User user = new User("123", "Sachin", "hire.sachin.04@gmail.com");

    Cart cart = new Cart();
    cart.add(new Item("pen", 1000), 2);
    cart.add(new Item("eraser", 500), 4);

    CouponRegistry coupons = new CouponRegistry(Map.of(
            "SAVE-10", new CouponDiscountStrategy(10),
            "SAVE-20", new CouponDiscountStrategy(20)));
    CheckoutService checkoutService = new CheckoutService(coupons, new EmailNotificationService());

    Order order = checkoutService.checkout(cart, user, "SAVE-10", true);
    IO.println(order);
}
