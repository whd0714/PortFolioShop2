package portfolioshop.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import portfolioshop.category.Category;
import portfolioshop.goods.Goods;
import portfolioshop.goods.enumType.DisplayStatus;
import portfolioshop.goods.enumType.SaleStatus;
import portfolioshop.item.dto.DisplayStatusDto;
import portfolioshop.item.searchQuery.ItemCategoryCondition;
import portfolioshop.itemCategory.ItemCategory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;

    @GetMapping("/goods/{itemId}")
    public String goodsForm(@PathVariable("itemId") Long itemId, Model model){
        Optional<Item> byId = itemRepository.findById(itemId);
        byId.ifPresent(item -> {
            model.addAttribute(item);
            model.addAttribute("saleStatusNo", SaleStatus.NOSALE);
            model.addAttribute("saleStatusYes",SaleStatus.SALE);
        });
        return "goods/goods-form";
    }

    @PostMapping("/update/selectDisplay")
    @ResponseBody
    public int updateDisplay(@RequestBody DisplayStatusDto displayStatusDto) {
        itemService.updateDisplay(displayStatusDto);
        return 1;
    }

    @GetMapping("/goods/{categoryId}")
    public String goodsCategoryForm(@PathVariable("categoryId") ItemCategoryCondition condition, Model model) {
        PageRequest of = PageRequest.of(0, 10);
        Page<Item> allItemByCategory = itemRepository.findAllItemByCategory(condition, of);

        List<Item> content = allItemByCategory.getContent();
        List<ItemByCategoryDto> collect = content.stream().map(i -> new ItemByCategoryDto(i))
                .collect(Collectors.toList());

        model.addAttribute("items", collect);

        return "";
    }

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
