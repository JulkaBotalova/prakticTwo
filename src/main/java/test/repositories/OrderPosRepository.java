package test.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import test.entity.OrderPos;

public interface OrderPosRepository extends PagingAndSortingRepository<OrderPos, Integer> {
}
