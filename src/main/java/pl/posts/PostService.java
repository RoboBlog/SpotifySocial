package pl.posts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import pl.comments.Comment;
import pl.security.SecurityContextService;
import pl.user.User;
import pl.user.UserService;

import java.util.NoSuchElementException;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final SecurityContextService securityContextService;

    public PostService(PostRepository postRepository, SecurityContextService securityContextService) {
        this.postRepository = postRepository;
        this.securityContextService = securityContextService;
    }

//    public Post likePost(PostI){
//
//    }

    public Post getPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No such Post!"));
    }

    public Post createNewPost(Post post) {
        User user = securityContextService.getLoggedUser();
        post.setUser(user);
        postRepository.save(post);
        return post;
    }

    public Post editPostContent(Long id, String content) {
        Post post = postRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("No such Post!"));

        if (!(securityContextService.getLoggedUser().equals(post.getUser()))) {
            throw new AccessDeniedException("Access denied!");
        }

        post.setContent(content);
        postRepository.save(post);

        return post;
    }

    //TODO test it (frontend)
    public void deletePost(Long id) {
        Post post = postRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("No such Post!"));

        if (!(securityContextService.getLoggedUser().equals(post.getUser()))) {
            throw new AccessDeniedException("Access denied!");
        } else {
            postRepository.delete(post);
        }
    }

    //auth
    public Comment addComment(Comment comment, Long postId) {
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new NoSuchElementException("No such Post!"));
        comment.setAuthor(securityContextService.getLoggedUser());
        post.addComment(comment);

        postRepository.save(post);
        return comment;
    }

    public Page<Post> getAllPaginated(Pageable page) {
        return postRepository.findAll(page);
    }

    public Page<Post> getAllUserPostByDateAdded(Pageable pageble, Long userId) {
        Page<Post> allUserPosts = postRepository.findAllByUser_UserIdOrderByDateAdded(pageble, userId);
        return allUserPosts;
    }
}
