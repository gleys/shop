package sample.shop.domain;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;

@Getter
@Entity
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


}
