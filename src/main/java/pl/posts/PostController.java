package pl.posts;

import org.springframework.web.bind.annotation.*;
import pl.comments.Comment;

@RequestMapping("/post/")
@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/get/{postId}")
    public Post getPost(@PathVariable Long postId){
        return postService.getPost(postId);
    }

    @PostMapping("/create")
    public Post createNewPost(@RequestBody Post post){
        return postService.createNewPost(post);
    }

    @PutMapping("/edit/{postId}")
    public Post editPost(@PathVariable Long postId, @RequestParam String content){
        return postService.editPostContent(postId, content);
    }

    @PostMapping("/add/comment/{postId}")
    public Comment addComment(@RequestBody Comment comment, @PathVariable Long postId){
        return postService.addComment(comment, postId);
    }

    @DeleteMapping("/delete/{postId}")
    public void deletePost(){

    }
}
