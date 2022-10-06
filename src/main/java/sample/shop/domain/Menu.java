package sample.shop.domain;

import lombok.Builder;
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
@NoArgsConstructor(access = PROTECTED)
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "menu_id", columnDefinition = "varbinary(16)")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<MenuItem> menuItems = new ArrayList<>();

    @ManyToOne(fetch = LAZY, optional = false)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_menu_to_category"))
    private Category category;

    @Builder
    public Menu(String name, BigDecimal price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    //연관관계 설정
    public void addItem(MenuItem item) {
        this.menuItems.add(item);
        item.setMenu(this);
    }

    public void changePrice(BigDecimal price) {
        this.price = price;
    }

    public void editMenuItem(MenuItem before, MenuItem after) {
        this.menuItems.remove(before);
        this.addItem(after);
    }
    public void removeItem(MenuItem item) {
        this.menuItems.remove(item);
    }

    public void changeCategory(Category category) {
        this.category = category;
    }


}
