package portfolioshop.itemCategory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolioshop.category.Category;
import portfolioshop.item.Item;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemCategoryService {

    private final ItemCategoryRepository itemCategoryRepository;


    public void createItemCategory(Category category, Item item) {
        ItemCategory itemCategory = new ItemCategory();
        itemCategory.changeCategory(category);
        itemCategory.changeItem(item);
        itemCategoryRepository.save(itemCategory);
    }
}
