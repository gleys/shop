package sample.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.shop.domain.Category;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
