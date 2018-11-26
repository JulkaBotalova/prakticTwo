package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.web.bind.annotation.*;
import test.entity.Order;
import test.repositories.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("ControllerAll")
public class RControllerAll {

    private final OrderRepository orderRepository;
    private final OrderPosRepository orderPosRepository;
    private final UserRepository userRepository;
    private final IsuePointRepository isuePointRepository;
    private final ProductRepository productRepository;
    private final ProductGroupRepository productGroupRepository;

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
        return orderRepository.getOne(orderId);
    }

    @PutMapping("/ordersUp")//Request method 'GET' not supported?????????????????????
    Order updateOrder(@RequestBody Order input) {
        Optional<Order> maybeOrder = orderRepository.findById(input.getId());
        Order order = maybeOrder
                .orElseThrow(() -> new ExpressionException(String.valueOf(input.getId())));
        order.setPhone(input.getPhone());
        order.setRemark(input.getRemark());

        return orderRepository.save(order);
    }

    @DeleteMapping("/ordersDel/{userId}") //Request method 'GET' not supported?????????????????????
    Order deleteAccount(@PathVariable Long orderId) throws AccountNotFoundException {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ExpressionException(String.valueOf(orderId)));
        orderRepository.delete(order);
        return order;
        }



    }

