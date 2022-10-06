package sample.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.shop.domain.Menu;

import java.util.UUID;

public interface MenuRepository extends JpaRepository<Menu, UUID> {
}
