package pl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

import pl.maps.DistanceCalculate;
import pl.model.User;

/**
 * Created by maciek on 7/14/17.
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class DistanceCalculateTest {
//    @Autowired
//    DistanceCalculate distanceCalculate;
//
//
//    @Test
//    public void distance1to2CheckTest(){

//
//        double lat1= 1;
//        double lon1= 1;
//        double lat2= 1;
//        double lon2= 1;
//        int range = 10;
//        boolean distanceCheck = distanceCalculate.distance1To2Check(lat1, lon1, lat2, lon2, range);
//    }
}
