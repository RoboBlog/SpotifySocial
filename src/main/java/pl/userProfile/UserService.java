package pl.userProfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.other.SecurityContextService;
import pl.maps.Coordinates;
import pl.maps.GoogleMapsGetService;
import pl.model.Address;
import pl.model.AddressRepository;
import pl.model.User;
import pl.model.UserRepository;

import java.io.IOException;

/**
 * Created by maciek on 8/13/17.
 */
@Service
public class UserService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final GoogleMapsGetService googleMapsGetService;
    private final SecurityContextService securityContextService;

    @Autowired
    public UserService(UserRepository userRepository, AddressRepository addressRepository, GoogleMapsGetService googleMapsGetService, SecurityContextService securityContextService) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.googleMapsGetService = googleMapsGetService;
        this.securityContextService = securityContextService;
    }

    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
    public User authTest() {
        String username = securityContextService.getUsername();
        User loggedUser = userRepository.findByUsername(username);
        return loggedUser;
    }

    public void addAddress(String city, String country) throws IOException {
        Coordinates coordinates = googleMapsGetService.getCoordinates(city, country);
        Address address = new Address(country,city,coordinates.getLatitude(), coordinates.getLongtitude());
        addressRepository.save(address);
        User user = authTest();
        user.setAddress(address);
        userRepository.save(user);
    }
}
