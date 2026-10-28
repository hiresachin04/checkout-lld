import java.util.HashMap;
import java.util.Map;

public class Cart {

    // HashMap: lookups by item on every add; line order doesn't matter for pricing.
    private final Map<Item, Integer> quantities = new HashMap<>();

    public void add(Item item, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be positive: " + quantity);
        }
        quantities.merge(item, quantity, Integer::sum);
    }

    public Map<Item, Integer> items() {
        return Map.copyOf(quantities);
    }

    public boolean isEmpty() {
        return quantities.isEmpty();
    }

    public long subTotalMinor() {
        long subTotal = 0;
        for (Map.Entry<Item, Integer> line : quantities.entrySet()) {
            subTotal = Math.addExact(subTotal, Math.multiplyExact(line.getKey().priceMinor(), line.getValue()));
        }
        return subTotal;
    }
}
