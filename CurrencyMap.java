import java.util.HashMap;

public class CurrencyMap {
    private final HashMap<Integer, String> currencyCodes = new HashMap<>();

    public CurrencyMap() {
        currencyCodes.put(1, "USD");
        currencyCodes.put(2, "CAD");
        currencyCodes.put(3, "EUR");
        currencyCodes.put(4, "HKD");
        currencyCodes.put(5, "INR");
    }

    public String getCode(int index) {
        return currencyCodes.get(index);
    }

    public void printOptions() {
        System.out.println("1=USD(American Dollars), 2=CAD(Canadian Dollars), 3=EUR(Euro), 4=HKD(Hong Kong Dollar), 5=INR(Rupee)");
    }

    public boolean isValid(int index) {
        return currencyCodes.containsKey(index);
    }
}
