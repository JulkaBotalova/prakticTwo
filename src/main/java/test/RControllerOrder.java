package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.web.bind.annotation.*;
import test.entity.IsuePoint;
import test.entity.Order;
import test.entity.User;
import test.repositories.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("ControllerAll")
public class RControllerOrder {
    /////ORDER//////
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final IsuePointRepository isuePointRepository;


    @Autowired
    public RControllerOrder(OrderRepository orderRepository, UserRepository userRepository, IsuePointRepository isuePointRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.isuePointRepository = isuePointRepository;
    }

    @GetMapping("/orders")
    Iterable<Order> getAllOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    @GetMapping("/orders/{orderId}")
    Optional<Order> getOrderId(@PathVariable Integer orderId){
        return orderRepository.findById(orderId);
    }

    @RequestMapping("/orderCreate")
    Order createOrder(@RequestParam(name = "idUser", defaultValue = "") Integer idUser,
                      @RequestParam(name = "idIsuePoint", defaultValue = "") Integer idIsuePoint,
                      @RequestParam(name  = "phone", defaultValue = "") String phone,
                      @RequestParam(name  = "remark", defaultValue = "") String remark) {
        Order order = new Order();
        Optional<IsuePoint> maybeIsuePoint = isuePointRepository.findById(idIsuePoint);
        IsuePoint isuePoint = maybeIsuePoint
                .orElseThrow(() -> new ExpressionException(String.valueOf(idIsuePoint)));

        Optional<User> maybeUser = userRepository.findById(idUser);
        User user = maybeUser
                .orElseThrow(() -> new ExpressionException(String.valueOf(idUser)));

        isuePoint.setOrder(new HashSet<Order>());
        user.setOrderUs(new HashSet<Order>());

        order.setIsuePoint(isuePoint);
        order.setUser(user);

        order.setPhone(phone);
        order.setRemark(remark);

        isuePoint.getOrder().add(order);
        user.getOrderUs().add(order);
        return orderRepository.save(order);
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


}
