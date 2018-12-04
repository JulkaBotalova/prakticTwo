package test.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import test.entity.OrderPos;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface OrderPosRepository extends PagingAndSortingRepository<OrderPos, Integer> {
    Optional<OrderPos> findById(Long id);
    List<OrderPos> findAll();

    @Query("SELECT COUNT(entity)>0 FROM OrderPos entity WHERE entity.id = :#{#_entity.id}")
    boolean isIdExists(@Param("_entity") OrderPos _entity);

    @Modifying
    @Transactional
    @Query("DELETE FROM OrderPos entity WHERE entity.id = :#{#_id}")
    void delete(@Param("_id") Optional<OrderPos> _id);
}