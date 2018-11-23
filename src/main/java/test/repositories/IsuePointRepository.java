package test.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import test.entity.IsuePoint;

public interface IsuePointRepository extends PagingAndSortingRepository<IsuePoint, Integer> {
}
