package pl.maps;

import org.springframework.stereotype.Service;

@Service
public class DistanceCalculator {
    public boolean distance1To2Check(double lat1, double lon1, double lat2, double lon2, int range) {
        double distanceInKilometer = 6371 * 2 * Math.atan2(Math.sqrt(Math.sin(((Math.PI / 180) * (lat2 - lat1)) / 2) *
                Math.sin(((Math.PI / 180) * (lat2 - lat1)) / 2) + Math.cos((Math.PI / 180) * (lat1)) * Math.cos((Math.PI / 180) * (lat2)) *
                Math.sin(((Math.PI / 180) * (lon2 - lon1)) / 2) * Math.sin(((Math.PI / 180) * (lon2 - lon1)) / 2)), Math.sqrt(1 - Math.sin(((Math.PI / 180) *
                (lat2 - lat1)) / 2) * Math.sin(((Math.PI / 180) * (lat2 - lat1)) / 2) + Math.cos((Math.PI / 180) * (lat1)) * Math.cos((Math.PI / 180) * (lat2)) *
                Math.sin(((Math.PI / 180) * (lon2 - lon1)) / 2) * Math.sin(((Math.PI / 180) * (lon2 - lon1)) / 2)));
        return distanceInKilometer <= range;
    }
}
