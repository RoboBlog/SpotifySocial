package pl.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.email.EmailService;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.UUID;

@Slf4j
@Service
public class PasswordResetService {

    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Value("${ver}")
    private String apiVer;

    public PasswordResetService(PasswordResetTokenRepository passwordResetTokenRepository, UserRepository userRepository, EmailService emailService, PasswordEncoder passwordEncoder) {
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }


    //TODO new thread
    public void resetPassword(String userEmail) throws UnknownHostException {
        User user = userRepository
                .findByEmailIgnoreCase(userEmail)
                .orElseThrow(() -> new NoSuchElementException("No such User!"));

        String token = UUID.randomUUID().toString();

        PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(myToken);
        String resetPasswordUrl = getBaseUrl() + ":9000" + apiVer + "user/changePassword?id=" + user.getUserId() + "&token=" + token;

        log.info("User {} has sent request to reset password", user.getUsername());
        String message = "Reset Password: " + resetPasswordUrl;

        emailService.sendSimpleMessage("robovlogg@gmail.com", "Reset Password", message);
    }

    public void validatePasswordResetToken(String token, String password, Long id){
        PasswordResetToken passToken = passwordResetTokenRepository
                .findByToken(token)
                .orElseThrow(() -> new NoSuchElementException("No such Token!"));

        if (!passToken.getUser().getUserId().equals(id)) {
            throw new InvalidParameterException("Invalid token");
        }

        //todo testit
        if (passToken.getExpiryDate().isAfter(LocalDateTime.now())) {
            throw new CredentialsExpiredException("Expired token!");
        }

        User user = passToken.getUser();
        user.setPassword(passwordEncoder.encode(password));
        log.info("User {} changed password", user.getUsername());
        //delete
    }

    private String getBaseUrl() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }
}
