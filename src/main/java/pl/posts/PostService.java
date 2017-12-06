package pl.posts;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import pl.comments.Comment;
import pl.user.User;
import pl.userProfile.UserService;

import java.util.NoSuchElementException;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

//    public Post likePost(PostI){
//
//    }

    public Post getPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No such Post!"));
    }

    public Post createNewPost(Post post) {
        User user = userService.authTest();
        post.setUser(user);
        postRepository.save(post);
        return post;
    }

    public Post editPostContent(Long id, String content) {
        //TODO update date
        Post post = postRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("No such Post!"));

        if (!(userService.authTest().equals(post.getUser()))) {
            throw new AccessDeniedException("Access denied!");
        }

        post.setContent(content);
        postRepository.save(post);

        return post;
    }


    //TODO test it
    public void deletePost(Long id) {
        Post post = postRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("No such Post!"));

        if (!(userService.authTest().equals(post.getUser()))) {
            throw new AccessDeniedException("Access denied!");
        } else {
            postRepository.delete(post);
        }
    }

    //TODO null pointer if user is no login?
    public Comment addComment(Comment comment, Long postId) {
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new NoSuchElementException("No such Post!"));
        post.addComment(comment);

        User user = userService.authTest();
        post.setUser(user);

        postRepository.save(post);
        return comment;
    }
}
