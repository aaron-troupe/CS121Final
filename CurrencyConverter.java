import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class CurrencyConverter {
    public static void main(String[] args) {
        HashMap<Integer, String> currencyCodes = new HashMap<Integer, String>();
        currencyCodes.put(1, "USD");
        currencyCodes.put(2, "CAD");
        currencyCodes.put(3, "EUR");
        currencyCodes.put(4, "HKD");
        currencyCodes.put(5, "INR");

        String fromCode, toCode;
        double amount;

        Scanner sc = new Scanner(System.in);
        System.out.println("Currency Converter: ");
        System.out.println("What Currency are you converting from?");
        System.out.println("1=USD(American Dollars), 2=CAD(Canadian Dollars), 3=EUR(Euro), 4=HKD(Hong Kong Dollar), 5=INR(Rupee)");

        int from = sc.nextInt();
        while (from < 1 || from > 5) {
            System.out.println("Please select a valid currency (1-5);");
            System.out.println("1=USD(American Dollars), 2=CAD(Canadian Dollars), 3=EUR(Euro), 4=HKD(Hong Kong Dollar), 5=INR(Rupee)");
            from = sc.nextInt();
        }

        fromCode = currencyCodes.get(from);

        System.out.println("What Currency are you converting to?");
        System.out.println("1=USD(American Dollars), 2=CAD(Canadian Dollars), 3=EUR(Euro), 4=HKD(Hong Kong Dollar), 5=INR(Rupee)");

        int to = sc.nextInt();
        while (to < 1 || to > 5) {
            System.out.println("Please select a valid currency (1-5);");
            System.out.println("1=USD(American Dollars), 2=CAD(Canadian Dollars), 3=EUR(Euro), 4=HKD(Hong Kong Dollar), 5=INR(Rupee)");
            to = sc.nextInt();
        }

        toCode = currencyCodes.get(to);

        System.out.println("How much do you want to convert?");
        amount = sc.nextFloat();

	try {
		sendHttpGETRequest(fromCode, toCode, amount);
	} catch (IOException e) {
		System.out.println("Error: " +  e.getMessage());
   	}
   	sc.close();

    }


    private static void sendHttpGETRequest(String fromCode, String toCode, double amount) throws IOException {
        String GET_URL = "https://api.exchangerate.host/latest?base=" + fromCode + "&symbols=" + toCode;
        URL url = new URL(GET_URL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");

        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            JSONObject obj = new JSONObject(response.toString());
            Double exchangeRate = obj.getJSONObject("rates").getDouble(fromCode);
            System.out.println(obj.getJSONObject("rates"));
            System.out.println(exchangeRate);
            System.out.println();
            System.out.println(amount + fromCode + " = " + amount / exchangeRate + toCode);
        } else {
            System.out.println("GET request failed");
        }
    }
}    
