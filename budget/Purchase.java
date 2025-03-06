package budget;

public record Purchase(String name, double price, Type type) {
    @Override
    public String toString() {
        return name + " $" + String.format("%.2f", price);
    }
}
