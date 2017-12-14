package pl.other;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.comments.Comment;
import pl.comments.CommentRepository;
import pl.posts.Post;
import pl.posts.PostRepository;
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
    private final PostRepository postRepository;


    public RunAtStart(PasswordEncoder passwordEncoder, UserRepository userRepository, AccountActivationService accountActivationService, AuthorityRepository authorityRepository, CommentRepository commentRepository, PostRepository postRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.accountActivationService = accountActivationService;
        this.authorityRepository = authorityRepository;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
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

        Post post1 = new Post(user, "test1");
        Post post2 = new Post(user, "test2");
        Post post3 = new Post(user, "test3");
        Post post4 = new Post(user, "test4");
        Post post5 = new Post(user, "test5");
        Post post6 = new Post(user, "test6");
        Post post7 = new Post(user, "test7");
        Post post8 = new Post(user, "test8");
        Post post9 = new Post(user, "test9");
        Post post10 = new Post(user, "test10");
        Post post11 = new Post(user, "test11");

        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);
        postRepository.save(post4);
        postRepository.save(post5);
        postRepository.save(post6);
        postRepository.save(post7);
        postRepository.save(post8);
        postRepository.save(post9);
        postRepository.save(post10);
        postRepository.save(post11);

    }
}