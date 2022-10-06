package sample.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.shop.domain.Review;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
}
