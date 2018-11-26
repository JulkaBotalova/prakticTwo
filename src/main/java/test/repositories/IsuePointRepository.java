package test.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import test.entity.IsuePoint;

import java.util.List;
import java.util.Optional;

public interface IsuePointRepository extends PagingAndSortingRepository<IsuePoint, Integer> {
    Optional<IsuePoint> findById(Long id);
    List<IsuePoint> findAll();
}
