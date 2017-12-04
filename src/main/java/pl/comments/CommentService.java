package pl.comments;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import pl.userProfile.UserService;

import java.util.NoSuchElementException;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;

    public CommentService(CommentRepository commentRepository, UserService userService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
    }

    public void deleteComment(Long id) {
        Comment comment = commentRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("No such Comment!"));

        if (!(userService.authTest().equals(comment.getAuthor()))) {
            throw new AccessDeniedException("Access denied!");
        } else {
            //TODO it is bad?
            commentRepository.delete(id);
        }
    }
}
