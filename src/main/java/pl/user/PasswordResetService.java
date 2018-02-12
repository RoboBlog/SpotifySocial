package pl.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.email.EmailService;
import pl.util.ServerUtil;

import java.net.UnknownHostException;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;

@Slf4j
@Service
public class PasswordResetService {

    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final ServerUtil serverUtil;

    public PasswordResetService(PasswordResetTokenRepository passwordResetTokenRepository, UserRepository userRepository, EmailService emailService, PasswordEncoder passwordEncoder, ServerUtil serverUtil) {
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.serverUtil = serverUtil;
    }


    //TODO new thread
    public void resetPassword(String userEmail) throws UnknownHostException {
        User user = userRepository
                .findByEmailIgnoreCase(userEmail)
                .orElseThrow(() -> new NoSuchElementException("No such User!"));

        String token = UUID.randomUUID().toString();

        PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(myToken);
        String resetPasswordUrl = serverUtil.getBaseUrl() + ":9000" + serverUtil.getApiVer() + "user/changePassword?id=" + user.getUserId() + "&token=" + token;

        log.info("User {} has sent request to reset password", user.getUsername());
        String message = "Reset Password: " + resetPasswordUrl;

        emailService.sendSimpleMessage(userEmail, "Reset Password", message);
    }

    public void validatePasswordResetToken(String token, String password, Long id){
        PasswordResetToken passToken = passwordResetTokenRepository
                .findByToken(token)
                .orElseThrow(() -> new NoSuchElementException("No such Token!"));

        if (!passToken.getUser().getUserId().equals(id)) {
            throw new InvalidParameterException("Invalid token");
        }

        //todo testit
        if (passToken.getExpiryDate().isAfter(ZonedDateTime.now())) {
            throw new CredentialsExpiredException("Expired token!");
        }

        User user = passToken.getUser();
        user.setPassword(passwordEncoder.encode(password));
        log.info("User {} changed password", user.getUsername());
        //delete
    }


}
