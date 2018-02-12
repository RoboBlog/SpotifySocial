package pl.post

import pl.comments.Comment
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

    def "addComment to exist post"() {
        given:
        def comment = new Comment(securityContextService.getLoggedUser(), "test comment")
        when:
        postService.addComment(comment, 1L)
        then:
        def comments = postService.getPost(1L).getComments()
        def commentFromPost = comments.iterator().next()
        commentFromPost == comment
    }

    def "addComment to no exist post"() {
        given:
        def comment = new Comment(securityContextService.getLoggedUser(), "test comment")
        when:
        postService.addComment(comment, 2L)
        then:
        thrown NoSuchElementException
    }


}
