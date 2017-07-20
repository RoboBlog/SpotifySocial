package pl.api;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.web.bind.annotation.*;
import pl.ProfileService;
import pl.maps.GoogleMapsGetService;
import pl.model.Address;
import pl.model.AddressRepository;
import pl.model.UserRepository;

// exception
@CrossOrigin(origins = "http://localhost:8000")
@RestController
@RequestMapping("/user/")
public class User {

    private final AddressRepository addressRepository;
    private final GoogleMapsGetService googleMapsGetService;
    private final ProfileService profileService;
    private final UserRepository userRepository;

    public User(AddressRepository addressRepository, GoogleMapsGetService googleMapsGetService, ProfileService profileService, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.googleMapsGetService = googleMapsGetService;
        this.profileService = profileService;
        this.userRepository = userRepository;
    }

    @RequestMapping("/addAddress")
    public void addAddress(@RequestBody Address address) throws UnirestException {
        googleMapsGetService.getCoordinates(address.getCity(), address.getCountry());
    }
    @GetMapping("/profile")
    public pl.model.User profile(){
        pl.model.User user = profileService.authTest();
        return user;
    }


    @GetMapping("/addfriend")
    public void addFriend(){

        //fix
//        profileService.addFriend(userRepository.findByUserName("admin"));
    }

    @PutMapping("/editAddress")
    public void editAddress(@RequestBody Address address) {
    }

    @PutMapping("/editPassword")
    public void editPassword(){

    }

    @PutMapping("/editEmail")
    public void editEmail(){

    }
}
