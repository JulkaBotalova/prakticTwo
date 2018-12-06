package test;

import org.springframework.expression.ExpressionException;
import org.springframework.web.bind.annotation.*;
import test.entity.Product;
import test.entity.ProductGroup;
import test.repositories.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("ControllerAll")
public class RControllerProduct {

    private final ProductRepository productRepository;
    private final ProductGroupRepository productGroupRepository;
    private Random random = new Random();

    public RControllerProduct(ProductRepository productRepository, ProductGroupRepository productGroupRepository) {
        this.productRepository = productRepository;
        this.productGroupRepository = productGroupRepository;
    }

    ////////PRODUCT///////////
    @GetMapping("/product")
    Iterable<Product> getAllProduct() {
        return (List<Product>) productRepository.findAll();
    }

    @GetMapping("/product/{ProductId}")
    Optional<Product> getProductId(@PathVariable Integer ProductId){
        return productRepository.findById(ProductId);
    }

    @RequestMapping("/productCreate")
    Product createProduct(@RequestParam(name = "id", defaultValue = "") Integer id,
                          @RequestParam(name  = "name", defaultValue = "") String name,
                          @RequestParam(name  = "price", defaultValue = "") Integer price) {
        Product product = new Product();
        Optional<ProductGroup> maybeProductGroup  = productGroupRepository.findById(id);
        ProductGroup  productGroup = maybeProductGroup
                .orElseThrow(() -> new ExpressionException(String.valueOf(id)));

        productGroup.setProducts(new HashSet<Product>());
        product.setProductGroup(productGroup);
        product.setProductName(name);
        product.setProductPrice(price);
        productGroup.getProducts().add(product);
        return productRepository.save(product);

    }

    @RequestMapping("/productUp")
    Product updateProduct(@RequestParam(name = "id", defaultValue = "") Integer id,
                          @RequestParam(name  = "name", defaultValue = "") String name,
                          @RequestParam(name  = "price", defaultValue = "") Integer price) {
        Optional<Product> maybeProduct = productRepository.findById(id);
        Product product = maybeProduct
                .orElseThrow(() -> new ExpressionException(String.valueOf(id)));
        product.setProductName(name);
        product.setProductPrice(price);
        return productRepository.save(product);
    }

    @GetMapping("/productDel/{pGroupId}")
    Product deleteProduct(@PathVariable Integer pGroupId) throws Exception {
        Product product = productRepository.findById(pGroupId)
                .orElseThrow(() -> new ExpressionException(String.valueOf(pGroupId)));
        productRepository.delete(product);
        return  product;

    }
}
