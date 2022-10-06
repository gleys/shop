package sample.shop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.UUID;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Item extends BaseEntity {

    @Id
    @Column(name = "item_id", columnDefinition = "varbinary(16)")
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Min(0) @Max(1000000)
    private BigDecimal price;

    public static Item createItem(String name, BigDecimal price) {
        Item item = new Item();
        item.name = name;
        item.price = price;

        return item;
    }

    public void changeItemName(String name) {
        this.name = name;
    }

    public void changeItemPrice(BigDecimal price) {
        this.price = price;
    }

}