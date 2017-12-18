package pl.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.user.User;
import pl.user.UserRepository;

@Service
public class SecurityContextService {
    private final UserRepository userRepository;

    public SecurityContextService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getLoggedUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public Object getLoggedAuthorities() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities();
    }

    public User getLoggedUser() {
        String username = getLoggedUsername();
        User loggedUser = userRepository.findByUsername(username);
        return loggedUser;
    }


}