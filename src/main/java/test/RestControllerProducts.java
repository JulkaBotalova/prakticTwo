package test;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.entity.*;
import test.repositories.*;

import java.util.HashSet;
import java.util.Random;

@RestController

public class RestControllerProducts {

    private final IsuePointRepository isuePointRepository;
    private final OrderPosRepository orderPosRepository;
    private final OrderRepository orderRepository;
    private final ProductGroupRepository productGroupRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public RestControllerProducts(IsuePointRepository isuePointRepository, OrderPosRepository orderPosRepository, OrderRepository orderRepository, ProductGroupRepository productGroupRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.isuePointRepository = isuePointRepository;
        this.orderPosRepository = orderPosRepository;
        this.orderRepository = orderRepository;
        this.productGroupRepository = productGroupRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    private Random random = new Random();

    @RequestMapping("ControllerProducts")
    public User controller(@RequestParam(name = "name", defaultValue = "") String name) {

        User user = new User();
        IsuePoint isuePoint = new IsuePoint();
        ProductGroup productGroup = new ProductGroup();

        user.setUsername(name);
        user.setUserphone("88005553535");
        //

        isuePoint.setIsuePointName("Point" + String.valueOf(random.nextInt()));
        isuePoint.setAddress("Lenina" + String.valueOf(random.nextInt()));
        //

        productGroup.setProductGroupName("GoodProducts");
        productGroup.setRemark("remarks");
       //

        userRepository.save(user);
        isuePointRepository.save(isuePoint);
        productGroupRepository.save(productGroup);


        for (Integer i = 0; i < 5; i++) {
            Product product = new Product();
            Order order1 = new Order();

            order1.setPhone("8- " + String.valueOf(random.nextInt()));
            order1.setRemark("Remark " + i.toString());
            user.setOrderUs(new HashSet<Order>());
            isuePoint.setOrder(new HashSet<Order>());
            productGroup.setProducts(new HashSet<Product>());

            product.setProductName("Продукты " + i.toString());
            product.setProductPrice(random.nextInt());

            product.setProductGroup(productGroup);
            product.setOrderPoss(new HashSet<OrderPos>());

            order1.setUser(user);
            order1.setIsuePoint(isuePoint);

            productGroup.getProducts().add(product);
            user.getOrderUs().add(order1);
            isuePoint.getOrder().add(order1);

           /* productRepository.saveAll(productGroup.getProducts());
            orderRepository.saveAll(user.getOrderUs());
            orderRepository.saveAll(isuePoint.getOrder());*/
            productRepository.save(product);
            orderRepository.save(order1);

            if (i == 5) {
                for (int j = 0; j <= 5; j++) {

                    OrderPos orderPos = new OrderPos();
                    order1.setOrderPoses(new HashSet<OrderPos>());
                    product.setOrderPoss(new HashSet<OrderPos>());


                    orderPos.setPrice(random.nextDouble());
                    orderPos.setQuantity(random.nextInt());
                    orderPos.setGoodName(String.valueOf(random.nextInt()));

                    orderPos.setOrder(order1);
                    orderPos.setProduct(product);

                    orderPosRepository.save(orderPos);

                    /*
                    OrderPos orderPos = new OrderPos();
                    order1.setOrderPoses(new HashSet<OrderPos>());
                    product.setOrderPoss(new HashSet<OrderPos>());


                    orderPos.setPrice(random.nextDouble());
                    orderPos.setQuantity(random.nextInt());
                    orderPos.setGoodName(String.valueOf(random.nextInt()));

                    orderPos.setOrder(order1);
                    orderPos.setProduct(product);

                    orderPosRepository.save(orderPos);
                     */

                }
            }

        }


        return user;

    }

}
