import java.io.*;
import java.net.*;
import org.json.JSONObject;

public class CurrencyService {
    private final String apiKey = System.getenv("EXCHANGE_API_KEY");

    public double getExchangeRate(String fromCode, String toCode) throws IOException {
        String urlStr = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + fromCode + "/" + toCode;
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONObject obj = new JSONObject(response.toString());
        if (!obj.has("conversion_rate")) {
            throw new IOException("conversion_rate not found in API response.");
        }
        return obj.getDouble("conversion_rate");
    }
}
