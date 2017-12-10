package pl.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.maps.Coordinates;
import pl.maps.GoogleMapsGetService;
import pl.user.Address;
import pl.user.AddressRepository;
import pl.user.User;
import pl.user.UserRepository;
import pl.security.SecurityContextService;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final GoogleMapsGetService googleMapsGetService;
    private final SecurityContextService securityContextService;


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

    public Address addAddress(String city, String country) throws IOException {
        Coordinates coordinates = googleMapsGetService.getCoordinates(city, country);
        Address address = new Address(country, city, coordinates.getLatitude(), coordinates.getLongtitude());
        addressRepository.save(address);
        User user = authTest();
        user.setAddress(address);
        userRepository.save(user);

        return address;
    }
}
