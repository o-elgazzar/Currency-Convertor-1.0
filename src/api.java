import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import org.json.JSONObject;

public class api {
    

    public static double getExchangeRate(String fromCurrency, String toCurrency) {
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://openexchangerates.org/api/latest.json?app_id=" + api_key + "&symbols=" + fromCurrency + "," + toCurrency;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JSONObject obj = new JSONObject(response.body());
                double rateFrom = obj.getJSONObject("rates").getDouble(fromCurrency);
                double rateTo = obj.getJSONObject("rates").getDouble(toCurrency);
                if (rateFrom == 0)
                    return 0.0;
                return rateTo / rateFrom;
            } else {
                return 0.0;
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("An error occurred: " + e.getMessage());
            return 0.0;
        }
    }
}
