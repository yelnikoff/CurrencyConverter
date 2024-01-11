package eu.yelnikoff.curverter.entities.user;

import java.util.Optional;

public interface UserRepository extends org.springframework.data.repository.CrudRepository<User, Integer> {

    Optional<User> findByEmail(String email);

}
