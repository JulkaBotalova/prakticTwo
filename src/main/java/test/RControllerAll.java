package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.web.bind.annotation.*;
import test.entity.*;
import test.repositories.*;

import java.util.*;


@RestController
@RequestMapping("ControllerAll")
public class RControllerAll {

    private final OrderRepository orderRepository;
    private final OrderPosRepository orderPosRepository;
    private final UserRepository userRepository;
    private final IsuePointRepository isuePointRepository;
    private final ProductRepository productRepository;
    private final ProductGroupRepository productGroupRepository;
    private Random random = new Random();


    @Autowired
    public RControllerAll(OrderRepository orderRepository, OrderPosRepository orderPosRepository, UserRepository userRepository, IsuePointRepository isuePointRepository, ProductRepository productRepository, ProductGroupRepository productGroupRepository) {
        this.orderRepository = orderRepository;
        this.orderPosRepository = orderPosRepository;
        this.userRepository = userRepository;
        this.isuePointRepository = isuePointRepository;
        this.productRepository = productRepository;
        this.productGroupRepository = productGroupRepository;
    }

   /////ORDER//////

    @GetMapping("/orders")
    Iterable<Order> getAllOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    @GetMapping("/orders/{orderId}")
    Optional<Order> getOrderId(@PathVariable Integer orderId){
        return orderRepository.findById(orderId);
    }

    @RequestMapping(value = "/ordersUp")
     Order updateOrder(@RequestParam(value = "id", defaultValue = "") Integer id,
                       @RequestParam(name  = "phone", defaultValue = "") String phone,
                       @RequestParam(name  = "remark", defaultValue = "") String remark) {
        Optional<Order> maybeOrder = orderRepository.findById(id);
        Order order = maybeOrder
                .orElseThrow(() -> new ExpressionException(String.valueOf(id)));
         order.setPhone(phone);
         order.setRemark(remark);

        return orderRepository.save(order);
    }


    @GetMapping(value="/ordersDel/{orderId}")
    Order deleteOrder(@PathVariable Integer orderId) throws Exception {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ExpressionException(String.valueOf(orderId)));
        orderRepository.delete(order);
        return order;
        }

////////ISUEPOINT////////////



    @GetMapping("/isuepoints")
    Iterable<IsuePoint> getAllIsuePoints() {
    return (List<IsuePoint>) isuePointRepository.findAll();
}

    @GetMapping("/isuepoints/{ipId}")
    Optional<IsuePoint> getIPId(@PathVariable Integer ipId){
        return isuePointRepository.findById(ipId);
    }

    @RequestMapping("/isuepointsUp")
    IsuePoint updateIsuePoint( @RequestParam(name = "id", defaultValue = "") Integer id,
                               @RequestParam(name  = "name", defaultValue = "") String name,
                               @RequestParam(name  = "address", defaultValue = "") String address) {
        Optional<IsuePoint> maybeIP = isuePointRepository.findById(id);
        IsuePoint isuePoint = maybeIP
                .orElseThrow(() -> new ExpressionException(String.valueOf(id)));
        isuePoint.setIsuePointName(name);
        isuePoint.setAddress(address);

        return isuePointRepository.save(isuePoint);
    }

    @GetMapping("/isuepointsDel/{ipId}")
    IsuePoint deleteIsuePoint(@PathVariable Integer ipId) throws Exception {
        IsuePoint isuePoint = isuePointRepository.findById(ipId)
                .orElseThrow(() -> new ExpressionException(String.valueOf(ipId)));
        isuePointRepository.delete(isuePoint);
        return isuePoint;
    }


    ////////USER////////////
    @GetMapping("/users")
    Iterable<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/users/{userId}")
    Optional<User> getUserId(@PathVariable Integer userId){
        return userRepository.findById(userId);
    }

    @RequestMapping("/usersUp")
    User updateUser(@RequestParam(name = "id", defaultValue = "") Integer id,
                    @RequestParam(name  = "name", defaultValue = "") String name,
                    @RequestParam(name  = "phone", defaultValue = "") String phone) {
        Optional<User> maybeUser = userRepository.findById(id);
        User user = maybeUser
                .orElseThrow(() -> new ExpressionException(String.valueOf(id)));
        user.setUsername(name);
        user.setUserphone(phone);

        return userRepository.save(user);
    }

    @GetMapping("/usersDel/{userId}")
    User deleteUser(@PathVariable Integer userId) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ExpressionException(String.valueOf(userId)));
        userRepository.delete(user);
        return user;
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


  ////////PRODUCT///////////
    @GetMapping("/product")
    Iterable<Product> getAllProduct() {
        return (List<Product>) productRepository.findAll();
    }

    @GetMapping("/product/{ProductId}")
    Optional<Product> getProductId(@PathVariable Integer ProductId){
        return productRepository.findById(ProductId);
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

       ////////ORDERPOS///////////
    @GetMapping("/orderPos")
    Iterable<OrderPos> getAllOrderPos() {
        return (List<OrderPos>) orderPosRepository.findAll();
    }

    @GetMapping("/orderPos/{orderPosId}")
    Optional<OrderPos> getorderPosId(@PathVariable Integer orderPosId){
        return orderPosRepository.findById(orderPosId);
    }

    @RequestMapping("/orderPosUp")
    OrderPos updateOrderPos(@RequestParam(name = "id", defaultValue = "") Integer id,
                            @RequestParam(name  = "quantity", defaultValue = "") Integer quantity,
                            @RequestParam(name  = "price", defaultValue = "") Integer price,
                            @RequestParam(name  = "name", defaultValue = "") String name) {
        Optional<OrderPos> maybeOrderPos = orderPosRepository.findById(id);
        OrderPos orderPos = maybeOrderPos
                .orElseThrow(() -> new ExpressionException(String.valueOf(id)));
        orderPos.setQuantity(quantity);
        orderPos.setPrice(price);
        orderPos.setGoodName(name);
        return orderPosRepository.save(orderPos);
    }

    @GetMapping("/orderPosDel/{pGroupId}")
    OrderPos deleteOrderPos(@PathVariable Integer pGroupId) throws Exception {
        OrderPos orderPos = orderPosRepository.findById(pGroupId)
                .orElseThrow(() -> new ExpressionException(String.valueOf(pGroupId)));
        orderPosRepository.delete(orderPos);
        return  orderPos;
    }


    }

