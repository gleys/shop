package sample.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.shop.domain.Order;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    // 주문 조회기능 구현(주문별 물품과 수량 표시)
}
