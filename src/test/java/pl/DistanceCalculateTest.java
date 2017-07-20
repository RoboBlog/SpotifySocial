package pl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.maps.DistanceCalculate;

/**
 * Created by maciek on 7/14/17.
 */
//@SpringBootTest
public class DistanceCalculateTest {
    @Autowired
    DistanceCalculate distanceCalculate;

    @Test
    public void distance1to2CheckTest(){
        double lat1= 1;
        double lon1= 1;
        double lat2= 1;
        double lon2= 1;
        int range = 10;
        boolean distanceCheck = distanceCalculate.distance1To2Check(lat1, lon1, lat2, lon2, range);
    }
}
