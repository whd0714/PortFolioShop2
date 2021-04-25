package portfolioshop.member;

import org.springframework.data.jpa.repository.JpaRepository;
import portfolioshop.member.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByUserId(String userId);

    Member findByUserId(String userId);
}
