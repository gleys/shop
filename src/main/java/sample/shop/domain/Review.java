package sample.shop.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
//no sql 고려
public class Review extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "review_id", columnDefinition = "varbinary(16)")
    private UUID id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "fk_review_to_member"))
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "fk_review_to_order"))
    private Order order;

    @Column(nullable = true)
    @Min(1) @Max(5)
    private int rate;

    @Column(nullable = false, length = 255)
    private String title;

    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(
            name = "review_id",
            nullable = true,
            columnDefinition = "varbinary(16)",
            foreignKey = @ForeignKey(name = "fk_review_to_image")
    )
    private List<Image> images = new ArrayList<>();

    @Column(nullable = true, length = 255)
    private String description;

    @Builder
    public Review(Member member, Order order, int rate, List<Image> images, String description) {
        this.member = member;
        this.order = order;
        this.rate = rate;
        this.images = images;
        this.description = description;
    }

    public void addImage(byte[] image) {
        Image newImage = Image.createImage(image);
        this.images.add(newImage);
    }

    public void editRate(int rate) {
        this. rate = rate;
    }
    public void editDescription(String description) {
        this.description = description;
    }

}
