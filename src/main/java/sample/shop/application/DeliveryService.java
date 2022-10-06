package sample.shop.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.shop.domain.Address;
import sample.shop.domain.Delivery;
import sample.shop.domain.DeliveryStatus;
import sample.shop.repository.DeliveryRepository;
import sample.shop.repository.OrderRepository;

import java.util.UUID;

import static sample.shop.domain.DeliveryStatus.*;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final OrderRepository orderRepository;
    private final DeliveryRepository deliveryRepository;

    @Transactional(readOnly = true)
    public DeliveryStatus findDeliveryStatus(UUID deliveryId) {
        return findDelivery(deliveryId).getStatus();
    }

    @Transactional
    public void changeStatus(UUID deliveryId, DeliveryStatus status) {
        findDelivery(deliveryId).changeStatus(status);
    }

    @Transactional
    public void changeAddress(UUID deliveryId, Address address) {
        Delivery delivery = findDelivery(deliveryId);
        DeliveryStatus status = delivery.getStatus();

        if (status.equals(IN_TRANSIT) || status.equals(COMPLETE)) {
            throw new IllegalStateException("배송중이나 완료 상태에는 배송지 변경이 불가능 합니다.");
        }

        delivery.changeAddress(address);
    }

    private Delivery findDelivery(UUID deliveryId) {
        return deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new IllegalArgumentException("배송 정보가 없습니다."));
    }

}
