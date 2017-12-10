package pl.other;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.comments.Comment;
import pl.comments.CommentRepository;
import pl.user.User;
import pl.user.UserRepository;
import pl.security.Authority;
import pl.security.AuthorityName;
import pl.security.AuthorityRepository;
import pl.user.AccountActivationService;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Component
public class RunAtStart {


    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AccountActivationService accountActivationService;
    private final AuthorityRepository authorityRepository;
    private final CommentRepository commentRepository;


    public RunAtStart(PasswordEncoder passwordEncoder, UserRepository userRepository, AccountActivationService accountActivationService, AuthorityRepository authorityRepository, CommentRepository commentRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.accountActivationService = accountActivationService;
        this.authorityRepository = authorityRepository;
        this.commentRepository = commentRepository;
    }

    @PostConstruct
    public void runAtStart() {
        User user = new User("admin", passwordEncoder.encode("admin"), "admin@admin.com", true, new Date());

        List<User> users = new LinkedList<>();
        userRepository.save(user);
        Authority authority = new Authority(AuthorityName.ROLE_ADMIN, users);
        user.addAuthority(authority);
        authorityRepository.save(authority);
        userRepository.save(user);

        Comment comment = new Comment();
        comment.setContent("test");
        commentRepository.save(comment);

    }
}