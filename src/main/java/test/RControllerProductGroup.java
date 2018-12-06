package test;

import org.springframework.expression.ExpressionException;
import org.springframework.web.bind.annotation.*;
import test.entity.ProductGroup;
import test.repositories.ProductGroupRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("ControllerAll")
public class RControllerProductGroup {

    private final ProductGroupRepository productGroupRepository;

    public RControllerProductGroup(ProductGroupRepository productGroupRepository) {
        this.productGroupRepository = productGroupRepository;
    }

    ////////PRODUCTGROUP///////////
    @GetMapping("/productgroup")
    Iterable<ProductGroup> getAllPGroup() {
        return (List<ProductGroup>) productGroupRepository.findAll();
    }

    @GetMapping("/productgroup/{pGroupId}")
    Optional<ProductGroup> getpGroupId(@PathVariable Integer pGroupId){
        return productGroupRepository.findById(pGroupId);
    }

    @GetMapping("/productGroupCreate")
    ProductGroup createProductGroup( @RequestParam(name  = "name", defaultValue = "") String name,
                                     @RequestParam(name  = "remark", defaultValue = "") String remark) {

        ProductGroup productGroup = new ProductGroup();
        productGroup.setProductGroupName(name);
        productGroup.setRemark(remark);

        return productGroupRepository.save(productGroup);
    }

    @RequestMapping("/productgroupUp")
    ProductGroup updateProductGroup(@RequestParam(name = "id", defaultValue = "") Integer id,
                                    @RequestParam(name  = "name", defaultValue = "") String name,
                                    @RequestParam(name  = "remark", defaultValue = "") String remark) {
        Optional<ProductGroup> maybeProductGroup = productGroupRepository.findById(id);
        ProductGroup productGroup = maybeProductGroup
                .orElseThrow(() -> new ExpressionException(String.valueOf(id)));
        productGroup.setProductGroupName(name);
        productGroup.setRemark(remark);
        return productGroupRepository.save(productGroup);
    }

    @GetMapping("/productGroupDel/{pGroupId}")
    ProductGroup deleteProductGroup(@PathVariable Integer pGroupId) throws Exception {
        ProductGroup productGroup = productGroupRepository.findById(pGroupId)
                .orElseThrow(() -> new ExpressionException(String.valueOf(pGroupId)));
        productGroupRepository.delete(productGroup);
        return  productGroup;
    }

}
