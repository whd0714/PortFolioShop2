package portfolioshop.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("select c from Cart c join fetch c.member m where m.id = :id")
    Cart findCartByMemberId(Long id);
}
