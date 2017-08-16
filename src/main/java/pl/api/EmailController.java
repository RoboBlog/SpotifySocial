package pl.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.userProfile.AccountActivationService;

/**
 * Created by maciek on 8/13/17.
 */
@CrossOrigin(origins = "http://localhost:8000")
@RestController
@RequestMapping("/api/email/")
public class EmailController {
    private final AccountActivationService accountActivationService;

    @Autowired
    public EmailController( AccountActivationService accountActivationService) {
        this.accountActivationService = accountActivationService;
    }

    @GetMapping("/active/{username}/{activationCode}")
    public String active(@PathVariable String username, @PathVariable String activationCode){
        String response = accountActivationService.accountActivation(username, activationCode);
        return response;
    }
}
