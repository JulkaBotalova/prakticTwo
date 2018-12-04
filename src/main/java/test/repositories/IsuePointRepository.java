package test.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import test.entity.IsuePoint;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface IsuePointRepository extends PagingAndSortingRepository<IsuePoint, Integer> {
    Optional<IsuePoint> findById(Long id);
    List<IsuePoint> findAll();

    @Query("SELECT COUNT(entity)>0 FROM IsuePoint entity WHERE entity.id = :#{#_entity.id}")
    boolean isIdExists(@Param("_entity") IsuePoint _entity);

    @Modifying
    @Transactional
    @Query("DELETE FROM IsuePoint entity WHERE entity.id = :#{#_id}")
    void delete(@Param("_id") Optional<IsuePoint> _id);
}

