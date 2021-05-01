package portfolioshop.item;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import portfolioshop.brand.Brand;
import portfolioshop.category.Category;
import portfolioshop.category.CategoryRepository;
import portfolioshop.goods.Goods;
import portfolioshop.goods.enumType.DisplayStatus;
import portfolioshop.goods.enumType.SaleStatus;
import portfolioshop.item.dto.DisplayStatusDto;
import portfolioshop.item.dto.queryDto.GoodsCategoryListSearchCondition;
import portfolioshop.itemCategory.ItemCategory;

import javax.persistence.Lob;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;
    private final CategoryRepository categoryRepository;

    @GetMapping("/goods/{itemId}")
    public String goodsForm(@PathVariable("itemId") Long itemId, Model model){

        List<Category> all = categoryRepository.findAll();

        List<Category> collect = all.stream().collect(Collectors.toList());

        List<Category> mainCategory = new ArrayList<>();
        List<Category> subCategory = new ArrayList<>();


        for (int i = 0; i < collect.size(); i++) {
            if (collect.get(i).getParent() != null) {
                subCategory.add(collect.get(i));
            } else {
                mainCategory.add(collect.get(i));
            }
        }

        Optional<Item> byId = itemRepository.findById(itemId);
        byId.ifPresent(item -> {
            model.addAttribute(item);
            model.addAttribute("saleStatusNo", SaleStatus.NOSALE);
            model.addAttribute("saleStatusYes",SaleStatus.SALE);
            model.addAttribute("mainCategories", mainCategory);
            model.addAttribute("subCategories", subCategory);
        });
        return "goods/goods-form";
    }

    @PostMapping("/update/selectDisplay")
    @ResponseBody
    public int updateDisplay(@RequestBody DisplayStatusDto displayStatusDto) {
        itemService.updateDisplay(displayStatusDto);
        return 1;
    }

    @GetMapping("/goods/category/{categoryId}")
    public String goodsCategoryForm(@PathVariable("categoryId") Long categoryId, Model model,
                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                    @Valid GoodsCategoryListSearchCondition condition) {

        PageRequest of = PageRequest.of(page, 12);

        Page<Item> itemFetchJoin = itemRepository.findItemFetchJoin(categoryId, of);
        model.addAttribute("itemFetchJoin", itemFetchJoin);
        model.addAttribute("maxPage", 10);

        //브랜드 이름 중복제거
        List<Brand> brands = itemFetchJoin.stream().map(Item::getBrand).distinct().collect(Collectors.toList());
        model.addAttribute("brands",brands);

        List<Category> all = categoryRepository.findAll();

        List<Category> collect = all.stream().collect(Collectors.toList());

        List<Category> mainCategory = new ArrayList<>();
        List<Category> subCategory = new ArrayList<>();


        for (int i = 0; i < collect.size(); i++) {
            if (collect.get(i).getParent() != null) {
                subCategory.add(collect.get(i));
                if(collect.get(i).getId() == categoryId) {
                    model.addAttribute("subCate",collect.get(i));
                }
            } else {
                mainCategory.add(collect.get(i));
            }
        }


        if(condition!=null) {
            model.addAttribute("condition",condition);
        }
        model.addAttribute("mainCategories", mainCategory);
        model.addAttribute("subCategories", subCategory);
        model.addAttribute("categoryId", categoryId);
        return "goods/goods-category-form";
    }




    /*@GetMapping("/goods/category/{categoryId}")
    public String goodsCategoryForm(@PathVariable("categoryId") ItemCategoryCondition condition, Model model ,
                                    @RequestParam(value = "page", defaultValue = "0") int page) {
       *//* PageRequest of = PageRequest.of(page, 10);
        Page<Item> allItemByCategory = itemRepository.findAllItemByCategory(condition, of);

        List<Item> content = allItemByCategory.getContent();

        List<ItemByCategoryDto> itemByCategoryDtos = content.stream().map(i -> new ItemByCategoryDto(i))
                .collect(Collectors.toList());*//*

        List<Category> all = categoryRepository.findAll();

        List<Category> collect = all.stream().collect(Collectors.toList());

        List<Category> mainCategory = new ArrayList<>();
        List<Category> subCategory = new ArrayList<>();


        for (int i = 0; i < collect.size(); i++) {
            if (collect.get(i).getParent() != null) {
                subCategory.add(collect.get(i));
            } else {
                mainCategory.add(collect.get(i));
            }
        }

        model.addAttribute("mainCategories", mainCategory);
        model.addAttribute("subCategories", subCategory);
  *//*      model.addAttribute("pages", allItemByCategory);
        model.addAttribute("items", itemByCategoryDtos);
*//*
        return "goods/goods-category-form";
    }
*/
    static class ItemByCategoryDto {

        private Long itemId;
        private int itemPrice;
        private DisplayStatus displayStatus;
        private String brandName;
        private List<GoodsDto> goods;
        private List<ItemCategoryDto> categories;

        public ItemByCategoryDto(Item item) {
            this.itemId = item.getId();
            this.itemPrice = item.getItemPrice();
            this.displayStatus = item.getDisplayStatus();
            this.brandName = item.getBrand().getBrandName();
            this.goods = item.getGoods().stream()
                    .map(g -> new GoodsDto(g))
                    .collect(Collectors.toList());
            this.categories = item.getItemCategories().stream()
                    .map(c -> new ItemCategoryDto(c))
                    .collect(Collectors.toList());
        }
    }

    @Data
    static class GoodsDto {
        private String size;
        private int count;
        private SaleStatus saleStatus;

        public GoodsDto(Goods goods) {
            this.size = goods.getSize();
            this.count = goods.getCount();
            this.saleStatus = goods.getSaleStatus();
        }
    }

    @Data
    static class ItemCategoryDto {
        private String categoryName;

        public ItemCategoryDto(ItemCategory category) {
            this.categoryName = category.getCategory().getName();
        }
    }

}
