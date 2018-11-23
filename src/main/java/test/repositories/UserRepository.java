package test.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import test.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
}
