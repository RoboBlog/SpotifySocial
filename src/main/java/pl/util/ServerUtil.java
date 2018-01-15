package pl.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

//TODO rename it
@Component
public class ServerUtil {

    @Value("${ver}")
    private String apiVer;

    public String getApiVer() {
        return apiVer;
    }

    public String getBaseUrl() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }
}
