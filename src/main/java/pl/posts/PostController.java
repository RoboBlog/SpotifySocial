package pl.posts;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.comments.Comment;
import pl.exception.MyResourceNotFoundException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("${ver}/post/")
@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/get/{postId}")
    public Post getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }

    @PostMapping("/create")
    public Post createNewPost(@RequestBody Post post) {
        return postService.createNewPost(post);
    }

    @PutMapping("/edit/{postId}")
    public Post editPost(@PathVariable Long postId,
                         @RequestParam String content) {
        return postService.editPostContent(postId, content);
    }

    @PostMapping("/add/comment/{postId}")
    public Comment addComment(@RequestBody Comment comment, @PathVariable Long postId) {
        return postService.addComment(comment, postId);
    }

    @DeleteMapping("/delete/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }

    @GetMapping("/get/all/{userId}")
    public List<Post> getAllUserPosts(@RequestParam int page,
                                      @RequestParam int size,
                                      @PathVariable Long userId) {
        List<Post> allUserPost = postService.getAllUserPost(userId);

       Page<Post> resultPage = postService.getAllPaginated(page, size);

        //todo code review
        if (page+1 > resultPage.getTotalPages()) {
            throw new MyResourceNotFoundException("Resource not Found");
        }
        return resultPage.getContent();
    }

    @GetMapping("/get/all")
    public List<Post> getAllPaginated(@RequestParam int page,
                                    @RequestParam int size) {
        Page<Post> resultPage = postService.getAllPaginated(page,   size);

        //todo code review
        if (page+1 > resultPage.getTotalPages()) {
            throw new MyResourceNotFoundException("Resource not Found");
        }
        return resultPage.getContent();
    }
}
