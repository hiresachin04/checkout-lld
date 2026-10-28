public class EmailNotificationService implements NotificationService {

    @Override
    public void orderPlaced(Order order) {
        System.out.printf("Email to %s: order %s placed, total %d%n",
                order.user().email(), order.orderId(), order.totalMinor());
    }
}
