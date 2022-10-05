package sample.shop.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class Category extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "category_id", columnDefinition = "varbinary(16)")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id", foreignKey = @ForeignKey(name = "fk_child_to_parent"))
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();
//
//    //연관관계 메소드
//    public void addChildCategory(Category child) {
//        this.child.add(child);
//        child.setParent(this);
//    }


}
