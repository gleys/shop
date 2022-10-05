package sample.shop.domain;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@Entity
public class MenuItem {

    @Id
    @GeneratedValue
    @Column(name = "menu_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "menu_id", foreignKey = @ForeignKey(name = "fk_menu_item_to_menu"))
    Menu menu;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id", foreignKey = @ForeignKey(name = "fk_menu_item_to_item"))
    Item item;

    @Column(nullable = false)
    private int quantity;


}
