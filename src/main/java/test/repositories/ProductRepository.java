package test.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import test.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer>{
    Optional<Product> findById(Long id);
    List<Product> findAll();
}

