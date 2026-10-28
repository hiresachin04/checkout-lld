public record Item(String name, long priceMinor) {

    public Item {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("item name is required");
        }
        if (priceMinor < 0) {
            throw new IllegalArgumentException("price cannot be negative: " + priceMinor);
        }
    }
}
