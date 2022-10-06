package sample.shop.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.shop.domain.*;
import sample.shop.repository.OrderRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static sample.shop.domain.DeliveryStatus.*;
import static sample.shop.domain.OrderStatus.CANCEL;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    @Transactional
    public UUID create(OrderRequestDto orderDto) {
        Member member = orderDto.getMember();
        Delivery delivery = orderDto.getDelivery();
        List<OrderMenu> orderMenus = orderDto.getOrderMenus();

        Order order = Order.createOrder(member, orderMenus, delivery);
        Map<Long, List<MenuItem>> menuItemMap = new HashMap<>();

        //재고수량 감소
        orderMenus.forEach(
            orderMenu -> {
                orderMenu.getMenu().getMenuItems()
                .forEach(
                    menuItem ->
                    menuItem.reduceQuantity(orderMenu.getQuantity()));
            });

        orderRepository.save(order);
        return order.getId();
    }

    @Transactional
    public void cancelOrder(UUID orderId) {
        Order order = findOrder(orderId);
        // 배송중이거나 완료시 주문 취소 불가 -> 반송 구현
        validCancel(order);
        order.changeStatus(CANCEL);
        List<OrderMenu> orderMenus = order.getOrderMenus();


        order.getDelivery().changeStatus(READY);

        //재고수량 복원
        orderMenus.forEach(
            orderMenu -> {
                orderMenu.getMenu().getMenuItems()
                    .forEach(
                        menuItem ->
                        menuItem.reduceQuantity(orderMenu.getQuantity()));
            });
    }

    @Transactional
    public void returnOrder(UUID orderId) {

    }


    private void validCancel(Order order) {
        DeliveryStatus status = order.getDelivery().getStatus();

        if (status.equals(READY) || status.equals(COMPLETE)) {
            throw new IllegalStateException("배송중에는 주문 취소가 불가합니다.");
        }
    }

    public Order findOrder(UUID orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문번호 입니다."));
    }

}
