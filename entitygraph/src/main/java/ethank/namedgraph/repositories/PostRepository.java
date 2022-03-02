package ethank.namedgraph.repositories;

import ethank.namedgraph.models.Post;
import ethank.namedgraph.models.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
//    @EntityGraph(value = "post-entity-graph")
    List<Post> findByUser(User user);
}
