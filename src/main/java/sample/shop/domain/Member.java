package sample.shop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.EnumType.*;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_id", columnDefinition = "varbinary(16)")
    private UUID id;

    private String name;

    @Embedded
    @Column(nullable = true)
    private Address address;

    @Column(nullable = false)
    @Enumerated(STRING)
    private RegisterChannel registerChannel;

    @Column(nullable = false)
    @Enumerated(STRING)
    private MemberType type;

    @Builder
    public Member(String name, Address address, RegisterChannel registerChannel, MemberType type) {
        this.name = name;
        this.address = address;
        this.registerChannel = registerChannel;
        this.type = type;
    }

    public void changeAddress(Address address) {
        this.address = address;
    }

    public void changeMemberType(MemberType type) {
        this.type = type;
    }

}
