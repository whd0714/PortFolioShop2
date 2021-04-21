package portfolioshop.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolioshop.brand.Brand;
import portfolioshop.brand.BrandRepository;
import portfolioshop.brand.BrandService;
import portfolioshop.category.Category;
import portfolioshop.category.CategoryRepository;
import portfolioshop.category.CategoryService;
import portfolioshop.itemCategory.ItemCategory;
import portfolioshop.itemCategory.ItemCategoryService;
import portfolioshop.productSetting.dto.ProductAddDto;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final BrandService brandService;
    private final CategoryService categoryService;
    private final ItemCategoryService itemCategoryService;


    public void addItem(ProductAddDto productAddDto) {
        Item item = new Item(productAddDto.getItemNo(), productAddDto.getItemName(), productAddDto.getItemNameEng(),
                productAddDto.getItemPrice(), productAddDto.getGender(), productAddDto.getGender(),
                productAddDto.getSubCategory(), productAddDto.getDescription());
        itemRepository.save(item);
        brandService.confirm(productAddDto.getBrandName(), item);
        categoryService.confirm(productAddDto.getMainCategory(), item);
        categoryService.confirm(productAddDto.getSubCategory(), item);
        //categoryService.categoryRelation(productAddDto.getMainCategory(), productAddDto.getSubCategory());
    }
}
