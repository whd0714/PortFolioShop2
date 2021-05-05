package portfolioshop.goods;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GoodsRepository extends JpaRepository<Goods, Long> {

    @Query("select g from Goods g where g.item.id =:id and g.size =:size")
    Goods findGoodsByItemIdAndSize(Long id, String size);
}
