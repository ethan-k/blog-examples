package ethank.namedgraph.repositories;

import ethank.namedgraph.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
