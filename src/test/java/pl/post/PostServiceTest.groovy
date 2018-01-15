package pl.post

import pl.posts.Post
import pl.posts.PostRepository
import pl.posts.PostService
import pl.security.SecurityContextService
import pl.user.User
import spock.lang.Specification

class PostServiceTest extends Specification {


    def securityContextService = Mock(SecurityContextService.class)
    def postRepository = Mock(PostRepository.class)
    def postService = new PostService(postRepository, securityContextService)

    def setup() {
        def user = new User("test", "test", "test@gmail.com")
        securityContextService.getLoggedUser() >> user
        postRepository.findById(1L) >> Optional.of(new Post(user, "test content"))
        postRepository.findById(_ as Long) >> Optional.ofNullable()

    }

    def "getPost with correct id"() {
        when:
        def post = postService.getPost(1L)
        then:
        post.getContent() == "test content"
    }

    def "getPost with incorrect id"() {
        when:
        def post = postService.getPost(2L)
        then:
        thrown NoSuchElementException
    }
}
