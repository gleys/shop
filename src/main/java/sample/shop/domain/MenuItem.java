package sample.shop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.*;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class MenuItem {

    @Id
    @GeneratedValue
    @Column(name = "menu_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "menu_id", foreignKey = @ForeignKey(name = "fk_menu_item_to_menu"))
    private Menu menu;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id", foreignKey = @ForeignKey(name = "fk_menu_item_to_item"))
    private Item item;

    @Column(nullable = false)
    private Long quantity;

    public static MenuItem createMenuItem(Item item, Long quantity) {
        MenuItem menuItem = new MenuItem();
        menuItem.item = item;
        menuItem.quantity = quantity;

        return menuItem;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void changeQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public void addQuantity(Long quantity) {
        this.quantity += quantity;
    }

    public void reduceQuantity(Long quantity) {
        Long updateQuantity = this.quantity - quantity;
        if (updateQuantity < 0) {
            throw new IllegalArgumentException("주문가능한 수량을 초과하였습니다.");
        }
        this.quantity = updateQuantity;
    }

}
