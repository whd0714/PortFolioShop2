package portfolioshop.itemTag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemTagRepository extends JpaRepository<ItemTag, Long> {

    @Query("select t.tagName from ItemTag it join fetch Item i on it.item.id = :itemId join fetch Tag t on t.id = it.tag.id")
    List<String> findByAllTagByItemId(Long itemId);
}
