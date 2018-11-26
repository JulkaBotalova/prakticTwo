package test.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import test.entity.ProductGroup;

import java.util.List;
import java.util.Optional;

public interface ProductGroupRepository extends PagingAndSortingRepository<ProductGroup, Integer>{
    Optional<ProductGroup> findById(Long id);
    List<ProductGroup> findAll();
}

