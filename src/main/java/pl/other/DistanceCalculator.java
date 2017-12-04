package pl.other;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DistanceCalculator {


    //KD TREE
    //Vantage-point tree
    //https://dev.mysql.com/doc/refman/5.7/en/spatial-extensions.html
    //https://postgis.net/docs/ST_DWithin.html
    //https://boundlessgeo.com/2012/07/making-geography-faster/

    public static void main(String args[]) {
        double lat1 = 50.03437;
        double lon1 = 19.21037;
        double lat2 = 50.13717;
        double lon2 = 18.96641;
        double range = 50.0;

        long startTime = System.currentTimeMillis();
        long elapsedTime = 0L;
        int j = 1;
        for (int i = 0; i < 1000000000; i++) {
            lat1 -= i * (elapsedTime / 10000);
            lon1 -= i * (elapsedTime / 1000);
            lon2 += i * (elapsedTime / 10000);
            lat2 -= i * (elapsedTime / 1000);
            double distanceInKilometer = 6371 * 2 * Math.atan2(Math.sqrt(Math.sin(((Math.PI / 180) * (lat2 - lat1)) / 2) *
                    Math.sin(((Math.PI / 180) * (lat2 - lat1)) / 2) + Math.cos((Math.PI / 180) * (lat1)) * Math.cos((Math.PI / 180) * (lat2)) *
                    Math.sin(((Math.PI / 180) * (lon2 - lon1)) / 2) * Math.sin(((Math.PI / 180) * (lon2 - lon1)) / 2)), Math.sqrt(1 - Math.sin(((Math.PI / 180) *
                    (lat2 - lat1)) / 2) * Math.sin(((Math.PI / 180) * (lat2 - lat1)) / 2) + Math.cos((Math.PI / 180) * (lat1)) * Math.cos((Math.PI / 180) * (lat2)) *
                    Math.sin(((Math.PI / 180) * (lon2 - lon1)) / 2) * Math.sin(((Math.PI / 180) * (lon2 - lon1)) / 2)));

            if (distanceInKilometer < range) {
//                System.out.println(j+ ". " + distanceInKilometer);
                j++;
            }
        }
        elapsedTime = (new Date()).getTime() - startTime;
        System.out.print(elapsedTime / 1000.0);
//        System.out.print(distanceInKilometer);
    }
}
