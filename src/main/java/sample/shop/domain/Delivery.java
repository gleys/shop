package sample.shop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

import static lombok.AccessLevel.PROTECTED;
import static sample.shop.domain.DeliveryStatus.*;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
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

    public static Delivery createDelivery(Address address) {
        Delivery delivery = new Delivery();
        delivery.address = address;
        delivery.status = READY;

        return delivery;
    }

    public void changeAddress(Address address) {
        this.address = address;
    }

    public void changeStatus(DeliveryStatus status) {
        this.status = status;
    }


}
