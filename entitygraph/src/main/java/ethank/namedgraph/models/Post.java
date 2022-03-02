package ethank.namedgraph.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedEntityGraph(
        name = "post-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("subject"),
                @NamedAttributeNode("user"),
                @NamedAttributeNode("comments"),
        }
)
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject;
    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    public void setId(Long id) {
        this.id = id;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public User getUser() {
        return user;
    }
}