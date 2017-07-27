package pl.maps;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * Created by maciek on 7/12/17.
 */
@Service
public class GoogleMapsGetService {
     String apiKey = System.getenv("GOOGLE_MAPS_API_KEY");
        public JSONObject getCoordinates(String city, String country) throws UnirestException {
            HttpResponse<JsonNode> coordinates = Unirest.get("https://maps.googleapis.com/maps/api/geocode/json?address=" + city+","+country + "&key=" + apiKey).asJson();
            JSONObject coord = coordinates.getBody().getObject().getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
            double longtitude = coord.getDouble("lng");
            double latitude = coord.getDouble("lat");
//            return coord;
        //add set to lattitude and longtitude
            return coord;
        }

}
