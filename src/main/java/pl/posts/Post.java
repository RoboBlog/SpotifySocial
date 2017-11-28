package pl.posts;

import pl.comments.Comment;
import pl.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private User user;
    private LocalDateTime date;
    private String content;
    //TODO
    //html
    //image or images
//    private Long likes;

    @OneToMany
    private Set<Comment> comments;

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addComment(Comment comment){
       this.comments.add(comment);
    }
    public Post() {
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", user=" + user +
                ", date=" + date +
                ", content='" + content + '\'' +
                ", comments=" + comments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (id != null ? !id.equals(post.id) : post.id != null) return false;
        if (user != null ? !user.equals(post.user) : post.user != null) return false;
        if (date != null ? !date.equals(post.date) : post.date != null) return false;
        if (content != null ? !content.equals(post.content) : post.content != null) return false;
        return comments != null ? comments.equals(post.comments) : post.comments == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        return result;
    }
}
