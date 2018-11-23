package test.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import test.entity.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer>{
}

