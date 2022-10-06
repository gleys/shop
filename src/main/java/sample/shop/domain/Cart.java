package sample.shop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "shopping_bag_id", columnDefinition = "varbinary(16)")
    private UUID id;

    @OneToOne(fetch = LAZY, optional = false)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "fk_cart_to_member"))
    private Member member;

    @OneToMany(mappedBy = "cart", cascade = ALL)
    private List<CartMenu> cartMenus = new ArrayList<>();


    public static Cart createCart(Member member) {
        Cart cart = new Cart();
        cart.member = member;
        return cart;
    }

    public void addMenu(CartMenu menu) {
        cartMenus.add(menu);
        menu.setCart(this);
    }

    public BigDecimal totalPrice() {
        BigDecimal totalPrice = BigDecimal.ZERO;

        this.cartMenus.stream()
                .map(menu ->
                    totalPrice.add(
                    menu.getMenu()
                        .getPrice()
                        .multiply(BigDecimal.valueOf(menu.getQuantity())))
                );
        return totalPrice;
    }




}
