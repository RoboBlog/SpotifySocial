package pl.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.maps.GoogleMapsGetService;

@RestController
public class GoogleMapsController {

    private final GoogleMapsGetService googleMapsGetService;

    public GoogleMapsController(GoogleMapsGetService googleMapsGetService) {
        this.googleMapsGetService = googleMapsGetService;
    }

    @RequestMapping("/getcord")
    public String getCord() throws UnirestException {
//        HttpResponse<JsonNode> coordinates = googleMapsGetService.getCoordinates("Tychy", "Polska");
//        JSONObject coord = coordinates.getBody().getObject().getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
//        double longtitude = coord.getDouble("lng");
//        double latitude = coord.getDouble("lat");
//        return longtitude + "   " + latitude;
return "test";
    }

}
