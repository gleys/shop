package sample.shop.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Entity
public class Delivery extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id", columnDefinition = "varbinary(16)")
    private UUID id;

    @Embedded
    @Column(nullable = false)
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus status;


}
