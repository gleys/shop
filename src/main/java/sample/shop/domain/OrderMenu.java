package sample.shop.domain;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@Entity
public class OrderMenu {

    @Id
    @GeneratedValue
    @Column(name = "order_menu_id")
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "fk_order_menu_to_order"))
    private Order order;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "menu_id", foreignKey = @ForeignKey(name = "fk_order_menu_to_menu"))
    private Menu menu;

}
