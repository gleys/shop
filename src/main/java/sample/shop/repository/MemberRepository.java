package sample.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.shop.domain.Member;

import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {
}
