package test;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.entity.IsuePoint;
import test.entity.ProductGroup;
import test.entity.*;
import test.repositories.*;
import java.util.HashSet;
import java.util.Random;

import static java.lang.Math.abs;

@RestController
public class RestControllerOrderPosAddPosition {

    private final IsuePointRepository isuePointRepository;
    private final OrderPosRepository orderPosRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductGroupRepository productGroupRepository;
    private final ProductRepository productRepository;

    public RestControllerOrderPosAddPosition(IsuePointRepository isuePointRepository, OrderPosRepository orderPosRepository, OrderRepository orderRepository, UserRepository userRepository, ProductGroupRepository productGroupRepository, ProductRepository productRepository) {
        this.isuePointRepository = isuePointRepository;
        this.orderPosRepository = orderPosRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productGroupRepository = productGroupRepository;
        this.productRepository = productRepository;
    }

    private Random random = new Random();

    @RequestMapping("ControllerOrderPos")
    public OrderPos controller(@RequestParam(name = "name", defaultValue = "") String name) {

        User user = new User();
        IsuePoint isuePoint = new IsuePoint();
        ProductGroup productGroup = new ProductGroup();

        user.setUsername("user" + String.valueOf(abs(abs(Math.random()))));
        user.setUserphone("88005553535");
        user.setOrderUs(new HashSet<Order>());
        userRepository.save(user);

        isuePoint.setIsuePointName("Point" + String.valueOf(abs(Math.random())));
        isuePoint.setAddress("Lenina" + String.valueOf(abs(Math.random())));
        isuePoint.setOrder(new HashSet<Order>());
        isuePointRepository.save(isuePoint);

        productGroup.setProductGroupName("GoodProducts");
        productGroup.setRemark("FreshProducts");
        productGroup.setProducts(new HashSet<Product>());
        productGroupRepository.save(productGroup);


        Order order1 = new Order();
        Product product = new Product();

        order1.setPhone("8- " + String.valueOf(abs(random.nextInt())));
        order1.setRemark("Remark " + name);
        order1.setUser(user);
        order1.setIsuePoint(isuePoint);
        order1.setOrderPoses(new HashSet<OrderPos>());

        product.setProductGroup(productGroup);
        product.setProductName(String.valueOf(abs(Math.random())));
        product.setProductPrice(random.nextInt());
        product.setProductGroup(productGroup);
        product.setOrderPoss(new HashSet<OrderPos>());

        user.getOrderUs().add(order1);
        isuePoint.getOrder().add(order1);
        productGroup.getProducts().add(product);


        OrderPos orderPos = new OrderPos();

        orderPos.setPrice(abs(random.nextInt()));
        orderPos.setQuantity(abs(random.nextInt()));
        orderPos.setGoodName(name);

        orderPos.setOrder(order1);
        orderPos.setProduct(product);

        order1.getOrderPoses().add(orderPos);
        product.getOrderPoss().add(orderPos);

        orderRepository.save(order1);
        productRepository.save(product);
        orderPosRepository.save(orderPos);


        return orderPos;
    }

}
