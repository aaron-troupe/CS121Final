import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) {
        CurrencyMap currencyMap = new CurrencyMap();
        CurrencyService currencyService = new CurrencyService();
        Scanner sc = new Scanner(System.in);
        boolean continueConverting = true;

        while (continueConverting) {
            System.out.println("Currency Converter:");
            System.out.println("What currency are you converting from?");
            currencyMap.printOptions();

            int from = sc.nextInt();
            while (!currencyMap.isValid(from)) {
                System.out.println("Invalid choice. Please select from the list:");
                currencyMap.printOptions();
                from = sc.nextInt();
            }
            String fromCode = currencyMap.getCode(from);

            System.out.println("What currency are you converting to?");
            currencyMap.printOptions();

            int to = sc.nextInt();
            while (!currencyMap.isValid(to)) {
                System.out.println("Invalid choice. Please select from the list:");
                currencyMap.printOptions();
                to = sc.nextInt();
            }
            String toCode = currencyMap.getCode(to);

            System.out.println("How much do you want to convert?");
            double amount = sc.nextDouble();

            try {
                double rate = currencyService.getExchangeRate(fromCode, toCode);
                double converted = amount * rate;
                System.out.printf("%.2f %s = %.2f %s\n", amount, fromCode, converted, toCode);
            } catch (Exception e) {
                System.out.println("Error fetching exchange rate: " + e.getMessage());
            }

            System.out.println("Do you need to convert again?");
            System.out.println("1: Yes \t Any other key: No");          
	    if(sc.nextInt() != 1){
		    continueConverting = false;
	    }
	}
     	sc.close();
    }
}
