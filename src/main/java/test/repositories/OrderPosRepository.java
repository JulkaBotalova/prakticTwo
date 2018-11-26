package test.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import test.entity.OrderPos;

import java.util.List;
import java.util.Optional;

public interface OrderPosRepository extends PagingAndSortingRepository<OrderPos, Integer> {
    Optional<OrderPos> findById(Long id);
    List<OrderPos> findAll();
}
