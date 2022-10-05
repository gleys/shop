package sample.shop.domain;

import lombok.Getter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;

@Getter
@Entity
@Table(name = "orders")
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

}
