package portfolioshop.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import portfolioshop.itemTag.ItemTag;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemSearchRepository {


    @Query("select i from Item i where i.id = :itemId")
    Item findByUpdateProductByItemId(Long itemId);

    boolean existsByItemNo(String itemNo);

    boolean existsByItemName(String itemName);

    boolean existsByItemNameEng(String itemNameEng);

    @Query("select it from Item i join ItemTag it on i.id = it.item.id")
    List<ItemTag> findAllItemTag(Long itemId);

    /*@Query("select i from Item i join fetch i.brand b")
    List<Item> findAllItemInSettingForm(Pageable pageable);*/


}
