package sample.shop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class CartMenu {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "cart_id", foreignKey = @ForeignKey(name = "fk_cart_menu_to_cart"))
    private Cart cart;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "menu_id", foreignKey = @ForeignKey(name = "fk_cart_menu_to_menu"))
    private Menu menu;

    @Column(nullable = false)
    private Long quantity;

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public static CartMenu createCartMenu(Menu menu, Long quantity) {
        CartMenu cartMenu = new CartMenu();
        cartMenu.menu = menu;
        cartMenu.quantity = quantity;

        return cartMenu;
    }
}
