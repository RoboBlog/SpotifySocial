package pl.groups;

import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

//    public Post likePost(PostI){
//
//    }

    public Post getPost(Long id){
        return postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No such Post!"));
    }

    public Post createNewPost(Post post){
        postRepository.save(post);
        return post;
    }

    public Post editPostContent(Long id, String content){
        //TODO update date
        Post post = postRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("No such Post!"));
        post.setContent(content);
        postRepository.save(post);

        return post;
    }

    public void deletePost(){
        //TODO delete post
    }
}
