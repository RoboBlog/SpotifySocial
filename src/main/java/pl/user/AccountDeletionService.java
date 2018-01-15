package pl.user;

import org.springframework.stereotype.Service;
import pl.email.EmailService;
import pl.security.SecurityContextService;
import pl.util.ServerUtil;

import java.net.UnknownHostException;

//TODO RENAME - account delete process 30 days
@Service
public class AccountDeletionService {

    private final SecurityContextService securityContextService;
    private final ServerUtil serverUtil;
    private final EmailService emailService;
    private final UserProfileService userProfileService;
    private final UserRepository userRepository;

    public AccountDeletionService(SecurityContextService securityContextService, ServerUtil serverUtil, EmailService emailService, UserProfileService userProfileService, UserRepository userRepository) {
        this.securityContextService = securityContextService;
        this.serverUtil = serverUtil;
        this.emailService = emailService;
        this.userProfileService = userProfileService;
        this.userRepository = userRepository;
    }

    //TODO async mail/mail content...auth
    public void initDeleteUserAccountProcess() throws UnknownHostException {
        User user = securityContextService.getLoggedUser();
        String email = user.getEmail();
        user.setDeleteProcess(true);
        userRepository.save(user);

        sendMail(user, email);
    }

    private void sendMail(User user, String email) throws UnknownHostException {
        String deleteAccountUrl = serverUtil.getBaseUrl() + ":9000" + serverUtil.getApiVer() + "user/delete/account/confirm?userId=" + user.getUserId();
        emailService.sendSimpleMessage(email, "Delete account confirm", deleteAccountUrl);
    }

    //TODO user soft delete... after 30 days complete delete
    public void confirmDeleteUserAccountProcess(Long userId) {
        if(userRepository.findByUserId(userId).isDeleteProcess()) {
            userProfileService.deleteUser(userId);
        }
    }

}
