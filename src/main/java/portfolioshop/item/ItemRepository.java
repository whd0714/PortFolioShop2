package portfolioshop.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import portfolioshop.itemTag.ItemTag;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {


}
