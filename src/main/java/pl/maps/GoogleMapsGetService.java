package pl.maps;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import pl.util.HttpClient;

import java.io.IOException;

@Service
public class GoogleMapsGetService {
    private final String apiKey = System.getenv("GOOGLE_MAPS_API_KEY");
    private final HttpClient httpClient;

    public GoogleMapsGetService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Coordinates getCoordinates(String city, String country) throws IOException {

        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + city + "," + country + "&key=" + apiKey;

        String body = httpClient.get(url, 250, 250);

        JSONObject googleMapsResponse = new JSONObject(body);
        JSONObject jsonObjectGoogleMapResponse = googleMapsResponse.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location");

        double longtitude = jsonObjectGoogleMapResponse.getDouble("lng");
        double latitude = jsonObjectGoogleMapResponse.getDouble("lat");

        return new Coordinates(longtitude, latitude);
    }

}
