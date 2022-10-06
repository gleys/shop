package sample.shop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor(access = PROTECTED)
public class Order extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "order_id", columnDefinition = "varbinary(16)")
    private UUID id;

    @ManyToOne(fetch = LAZY, optional = false)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "fk_order_to_member"))
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderMenu> orderMenus = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "delivery_id", foreignKey = @ForeignKey(name = "fk_order_to_delivery"))
    private Delivery delivery;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public static Order createOrder(Member member, List<OrderMenu> menus, Delivery delivery) {
        Order order = new Order();
        order.member = member;
        order.orderMenus = menus;
        order.delivery = delivery;
        order.status = OrderStatus.ORDER;

        return order;
    }

    public BigDecimal totalPrice() {
        BigDecimal totalPrice = BigDecimal.ZERO;

        this.orderMenus.stream()
                .map(menu ->
                        totalPrice.add(
                                menu.getMenu()
                                    .getPrice()
                                    .multiply(BigDecimal.valueOf(menu.getQuantity())))
                );
        return totalPrice;
    }

    public void changeStatus(OrderStatus status) {
        this.status = status;
    }

    public void addMenu(OrderMenu orderMenu) {
        this.orderMenus.add(orderMenu);
    }

    public void removeMenu(OrderMenu menu) {
        this.orderMenus.remove(menu);
    }

    public void changeMenu(OrderMenu before, OrderMenu after) {
        int beforeIdx = this.orderMenus.indexOf(before);
        this.orderMenus.remove(beforeIdx);
        this.orderMenus.add(beforeIdx - 1, after);
    }

}
