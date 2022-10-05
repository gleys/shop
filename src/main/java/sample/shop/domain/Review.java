package sample.shop.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Getter
@Entity
//no sql 고려
public class Review extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "review_id", columnDefinition = "varbinary(16)")
    private UUID id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "fk_review_to_member"))
    private Member memberId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "fk_review_to_order"))
    private Order orderId;

    @Column(nullable = true)
    @Min(1) @Max(5)
    private int rate;

    @Column(nullable = false, length = 255)
    private String title;

    @OneToMany(cascade = ALL)
    @JoinColumn(
            name = "review_id",
            nullable = true,
            columnDefinition = "varbinary(16)",
            foreignKey = @ForeignKey(name = "fk_review_to_image")
    )
    private List<Image> images = new ArrayList<>();

    @Column(nullable = true, length = 255)
    private String description;

}
