package test.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import test.entity.ProductGroup;

public interface ProductGroupRepository extends PagingAndSortingRepository<ProductGroup, Integer>{
}

