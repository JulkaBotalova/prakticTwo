package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.repositories.*;

import java.util.Random;


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











  }

