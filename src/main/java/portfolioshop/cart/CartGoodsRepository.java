package portfolioshop.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartGoodsRepository extends JpaRepository<CartGoods, Long> {

    @Query("select cg from CartGoods  cg join fetch  cg.cart c join fetch cg.goods g where c.id = :cartId and g.id = :goodsId")
    CartGoods findByCartGoodsByCartIdAndGoodsId(Long cartId, Long goodsId);

}
