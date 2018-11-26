package test.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import test.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    Optional<User> findById(Long id);
  //  Optional<User> findByName(String username);
    List<User> findAll();
}
