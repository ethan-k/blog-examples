package ethank.namedgraph.repositories;

import ethank.namedgraph.models.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
