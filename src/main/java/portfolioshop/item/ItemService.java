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
import portfolioshop.itemCategory.ItemCategoryRepository;
import portfolioshop.itemCategory.ItemCategoryService;
import portfolioshop.productSetting.dto.ProductAddDto;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final BrandRepository brandRepository;
    private final ItemCategoryRepository itemCategoryRepository;
    private final CategoryRepository categoryRepository;

    public void saveProduct(ProductAddDto productAddDto) throws IOException {
        String fileName = productAddDto.getItemImage().getOriginalFilename();

        byte[] bytes = productAddDto.getItemImage().getBytes();


        Item item = new Item(productAddDto.getItemNo(), productAddDto.getItemName(), productAddDto.getItemNameEng(),
                productAddDto.getItemPrice(), productAddDto.getSeason(), productAddDto.getGender(),
                productAddDto.getSubDescription(), productAddDto.getDescription(), bytes);

        itemRepository.save(item);

        Brand brand = brandRepository.findByBrandName(productAddDto.getItemBrandName());
        item.changeBrand(brand);

        Category mainCategory = categoryRepository.findByName(productAddDto.getMainCategory());
        Category subCategory = categoryRepository.findByName(productAddDto.getSubCategory());

        ItemCategory itemCategory = new ItemCategory();
        itemCategory.changeItem(item);
        itemCategory.changeCategory(mainCategory);
        itemCategory.changeCategory(subCategory);
        itemCategoryRepository.save(itemCategory);
    }


}
