package test;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.entity.*;
import test.repositories.*;

import java.util.HashSet;
import java.util.Random;

@RestController

public class RestControllerOrderAddPosition {

    private final IsuePointRepository isuePointRepository;
    private final OrderPosRepository orderPosRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public RestControllerOrderAddPosition(IsuePointRepository isuePointRepository, OrderPosRepository orderPosRepository, OrderRepository orderRepository, UserRepository userRepository) {
        this.isuePointRepository = isuePointRepository;
        this.orderPosRepository = orderPosRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    private Random random = new Random();

    @RequestMapping("ControllerOrder")
    public Order controller(@RequestParam(name = "name", defaultValue = "") String name) {

        User user = new User();
        IsuePoint isuePoint = new IsuePoint();

        user.setUsername("user"+ String.valueOf(random.nextInt()));
        user.setUserphone("88005553535");
        user.setOrderUs(new HashSet<Order>());

        isuePoint.setIsuePointName("Point" + String.valueOf(random.nextInt()));
        isuePoint.setAddress("Lenina" + String.valueOf(random.nextInt()));
        isuePoint.setOrder(new HashSet<Order>());

        Order order1 = new Order();

        order1.setPhone("8- " + String.valueOf(random.nextInt()));
        order1.setRemark("Remark " + name);

        order1.setUser(user);
        order1.setIsuePoint(isuePoint);

        user.getOrderUs().add(order1);
        isuePoint.getOrder().add(order1);

        userRepository.save(user);
        isuePointRepository.save(isuePoint);

        orderRepository.save(order1);


        return order1;

    }





}
