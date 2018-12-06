package test;

import org.springframework.expression.ExpressionException;
import org.springframework.web.bind.annotation.*;
import test.entity.Order;
import test.entity.OrderPos;
import test.entity.Product;
import test.repositories.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("ControllerAll")
public class RControllerOrderPos {

    private final OrderRepository orderRepository;
    private final OrderPosRepository orderPosRepository;
    private final UserRepository userRepository;
    private final IsuePointRepository isuePointRepository;
    private final ProductRepository productRepository;
    private final ProductGroupRepository productGroupRepository;
    private Random random = new Random();

    public RControllerOrderPos(OrderRepository orderRepository, OrderPosRepository orderPosRepository, UserRepository userRepository, IsuePointRepository isuePointRepository, ProductRepository productRepository, ProductGroupRepository productGroupRepository) {
        this.orderRepository = orderRepository;
        this.orderPosRepository = orderPosRepository;
        this.userRepository = userRepository;
        this.isuePointRepository = isuePointRepository;
        this.productRepository = productRepository;
        this.productGroupRepository = productGroupRepository;
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

    @RequestMapping("/orderPosCreate")
    OrderPos createOrderPos(@RequestParam(name = "idOrder", defaultValue = "") Integer idOrder,
                            @RequestParam(name = "idProduct", defaultValue = "") Integer idProduct,
                            @RequestParam(name  = "quantity", defaultValue = "") Integer quantity,
                            @RequestParam(name  = "price", defaultValue = "") Integer price,
                            @RequestParam(name  = "name", defaultValue = "") String name) {
        OrderPos orderPos = new OrderPos();
        Optional<Product> maybeProduct = productRepository.findById(idProduct);
        Product product = maybeProduct
                .orElseThrow(() -> new ExpressionException(String.valueOf(idProduct)));

        Optional<Order> maybeOrder = orderRepository.findById(idOrder);
        Order order = maybeOrder
                .orElseThrow(() -> new ExpressionException(String.valueOf(idOrder)));

        product.setOrderPoss(new HashSet<OrderPos>());
        order.setOrderPoses(new HashSet<OrderPos>());

        orderPos.setProduct(product);
        orderPos.setOrder(order);

        orderPos.setQuantity(quantity);
        orderPos.setPrice(price);
        orderPos.setGoodName(name);

        order.getOrderPoses().add(orderPos);
        product.getOrderPoss().add(orderPos);
        return orderPosRepository.save(orderPos);
    }

    @RequestMapping("/orderPosUp")
    OrderPos updateOrderPos(@RequestParam(name = "id", defaultValue = "") Integer id,
                            @RequestParam(name = "idOrder", defaultValue = "") Integer idOrder,
                            @RequestParam(name = "idProduct", defaultValue = "") Integer idProduct,
                            @RequestParam(name  = "quantity", defaultValue = "") Integer quantity,
                            @RequestParam(name  = "price", defaultValue = "") Integer price,
                            @RequestParam(name  = "name", defaultValue = "") String name) {
        OrderPos orderPosition = new OrderPos();
        if (orderPosRepository.findAll().size()<id){
            orderPosition = createOrderPos(idOrder, idProduct, quantity, price, name);
        }
        else{
            Optional<OrderPos> maybeOrder = orderPosRepository.findById(id);
            OrderPos orderPos = maybeOrder
                .orElseThrow(() -> new ExpressionException(String.valueOf(idOrder)));
            orderPos.setQuantity(quantity);
            orderPos.setPrice(price);
            orderPos.setGoodName(name);
            orderPosition = orderPos;
            orderPosRepository.save(orderPos);
        }

        return orderPosition;
    }

    @GetMapping("/orderPosDel/{pGroupId}")
    OrderPos deleteOrderPos(@PathVariable Integer pGroupId) throws Exception {
        OrderPos orderPos = orderPosRepository.findById(pGroupId)
                .orElseThrow(() -> new ExpressionException(String.valueOf(pGroupId)));
        orderPosRepository.delete(orderPos);
        return  orderPos;
    }



}
