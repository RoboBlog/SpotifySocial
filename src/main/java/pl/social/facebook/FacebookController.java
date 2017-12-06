package pl.social_integration.facebook;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.security.service.JwtAuthenticationResponse;

@RestController
public class FacebookController {

    private final FacebookService facebookService;

    public FacebookController(FacebookService facebookService) {
        this.facebookService = facebookService;
    }

    @GetMapping("/createFacebookAuthorization")
    public String createFacebookAuthorization() {
        return facebookService.createFacebookAuthorizationURL();
    }


    @GetMapping("/facebook")
    public String createFacebookAccessToken(@RequestParam("code") String code) {
        String facebookAccessToken = facebookService.createFacebookAccessToken(code);
        return facebookAccessToken;
    }

    @GetMapping("/getName/{accessToken}")
    public String getNameResponse(@PathVariable String accessToken) {
        return facebookService.getName(accessToken);
    }


    @GetMapping("/token/{accessToken}")
    public ResponseEntity<JwtAuthenticationResponse> returnToken(@PathVariable String accessToken) {
        return ResponseEntity.ok(new JwtAuthenticationResponse(accessToken));
    }
}