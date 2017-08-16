package pl.maps;

import com.mashape.unirest.http.exceptions.UnirestException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by maciek on 7/12/17.
 */
@Service
public class GoogleMapsGetService {
     private final String apiKey = System.getenv("GOOGLE_MAPS_API_KEY");

        public Coordinates getCoordinates(String city, String country) throws IOException {

            String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + city+","+country + "&key=" + apiKey;
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            String body = client.newCall(request).execute().body().string();

            JSONObject googleMapsResponse = new JSONObject(body);
            JSONObject jsonObjectGoogleMapResponse = googleMapsResponse.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location");

            double longtitude = jsonObjectGoogleMapResponse.getDouble("lng");
            double latitude = jsonObjectGoogleMapResponse.getDouble("lat");

            return new Coordinates(longtitude, latitude);
        }

}
