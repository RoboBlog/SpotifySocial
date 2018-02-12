package pl.util;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class TimeProvider {

    public static final String DEFAULT_ZONE_NAME = "Europe/Warsaw";
    public static final ZoneId DEFAULT_ZONE = ZoneId.of(DEFAULT_ZONE_NAME);

    public Date now() {
        return new Date();
    }

//    public LocalDate today() {
//        return now().toLocalDate();
//    }
}