package pl.comments;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import pl.security.SecurityContextService;
import pl.user.UserService;

import java.util.NoSuchElementException;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final SecurityContextService securityContextService;


    public CommentService(CommentRepository commentRepository, SecurityContextService securityContextService) {
        this.commentRepository = commentRepository;
        this.securityContextService = securityContextService;
    }

    public void deleteComment(Long id) {
        Comment comment = commentRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("No such Comment!"));

        if (!(securityContextService.getLoggedUser().equals(comment.getAuthor()))) {
            throw new AccessDeniedException("Access denied!");
        } else {
            //TODO it is bad?
            commentRepository.delete(comment);
        }
    }
}
