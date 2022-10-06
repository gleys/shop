package sample.shop.application;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.shop.domain.Item;
import sample.shop.repository.ItemRepository;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public UUID create(final Item request) {
        BigDecimal price = request.getPrice();
        String name = request.getName();

        checkValidPrice(price);
        checkValidName(name);

        itemRepository.save(request);
        return request.getId();
    }

    @Transactional
    public void changeName(UUID itemId, String name) {
        checkValidName(name);
        Item targetItem = findItem(itemId);
        targetItem.changeItemName(name);
    }

    @Transactional
    public void changePrice(UUID itemId, BigDecimal price) {
        checkValidPrice(price);
        Item targetItem = findItem(itemId);
        targetItem.changeItemPrice(price);
    }

    private Item findItem(UUID itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));
    }

    private void checkValidPrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) < 0 || Objects.isNull(price)) {
            throw new IllegalArgumentException("상품 가격은 반드시 양수여야 합니다.");
        }
    }

    private void checkValidName(String name) {
        if (Objects.isNull(name)) {
            throw new IllegalArgumentException("상품명을 반드시 입력해야 합니다.");
        }
    }

}
