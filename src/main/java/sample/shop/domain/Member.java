package sample.shop.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.EnumType.*;

@Getter
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_id", columnDefinition = "varbinary(16)")
    private UUID id;

    private String name;

    @Embedded
    @Column(nullable = true)
    private Address address;

    @Column(nullable = true)
    @Enumerated(STRING)
    private MemberType type;

}
