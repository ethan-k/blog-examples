package ethank.namedgraph.demo;

import ethank.namedgraph.models.Comment;
import ethank.namedgraph.models.Post;
import ethank.namedgraph.models.User;
import ethank.namedgraph.repositories.CommentRepository;
import ethank.namedgraph.repositories.PostRepository;
import ethank.namedgraph.repositories.UserRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class EntityGraphTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @BeforeEach
    void populateData() {
        Faker faker = new Faker();

        for(int i=0; i<1; i++) {
            User user = new User();
            user.setEmail("example" + i + "@example.com");
            user.setName(faker.name().name());
            user = userRepository.save(user);

            for(int j=0; j<10; j++) {
                Post post = new Post();
                post.setUser(user);
                post.setSubject(faker.book().title());
                post = postRepository.save(post);

                for(int z=0; z<100; z++) {
                    Comment comment = new Comment();
                    comment.setUser(user);
                    comment.setPost(post);
                    comment.setReply(faker.lorem().characters());
                    commentRepository.save(comment);
                }

            }
        }
    }

    @Test
    void testNameGraph() {
        Optional<User> byId = userRepository.findById(1L);
        List<Post> byUser = postRepository.findByUser(byId.get());
        for (Post post : byUser) {
            for (Comment comment : post.getComments()) {
            }
        }
    }
}
