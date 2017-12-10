package pl.email;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.user.AccountActivationService;

@RestController
@RequestMapping("${ver}/email/")
public class EmailController {
    private final AccountActivationService accountActivationService;

    public EmailController(AccountActivationService accountActivationService) {
        this.accountActivationService = accountActivationService;
    }

    @GetMapping("/active/{username}/{activationCode}")
    public String active(@PathVariable String username, @PathVariable String activationCode) {
        String response = accountActivationService.accountActivation(username, activationCode);
        return response;
    }
}
