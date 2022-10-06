package sample.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.shop.domain.Item;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
}
