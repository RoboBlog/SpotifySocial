package pl.userProfile;

import org.springframework.stereotype.Service;
import pl.email.EmailService;
import pl.model.User;
import pl.model.UserRepository;

import java.util.Objects;

@Service
public class AccountActivationService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final String serverUrl = "http://localhost:8080";

    public AccountActivationService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    //Where use stringBuilder TODO
    public String getUrl(User user) {
        return serverUrl + "/api/email/active/" + user.getUsername() + "/" + user.getActivationCode();
    }

    public void sendActivationMail(User user) {
        String url = getUrl(user);
        emailService.sendSimpleMessage(user.getEmail(), "Active account mail", "Click to active account: " + url);
    }

    //TODO refactor this
    public String accountActivation(String username, String activationCode) {
        User user = userRepository.findByUsername(username);
        if (Objects.equals(user.getActivationCode(), activationCode) && !user.isAccountEnabled()) {
            user.setAccountEnabled(true);
            userRepository.save(user);
            return "OK";
        } else if (user.isAccountEnabled()) {
            return "Your account is active";
        } else {
            return "error";
        }
    }
}
