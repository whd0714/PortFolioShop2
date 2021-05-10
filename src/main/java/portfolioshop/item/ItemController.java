package portfolioshop.item;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import portfolioshop.main.dto.MainSearchDto;
import portfolioshop.main.dto.TagSearchDto;
import portfolioshop.member.CurrentUser;
import portfolioshop.member.Member;
import portfolioshop.member.MemberRepository;

import javax.persistence.Lob;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static portfolioshop.item.QItem.item;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;

    @GetMapping("/goods/{itemId}")
    public String goodsForm(@CurrentUser Member member, @PathVariable("itemId") Long itemId, Model model){

        member(member, model);

        category(model);

        model.addAttribute("mainSearch", new MainSearchDto());

        model.addAttribute("mainSearchDto",new MainSearchDto());


        Optional<Item> byId = itemRepository.findById(itemId);
        byId.ifPresent(item -> {
            model.addAttribute(item);
            itemService.upDateView(item);
            model.addAttribute("saleStatusNo", SaleStatus.NOSALE);
            model.addAttribute("saleStatusYes",SaleStatus.SALE);
            model.addAttribute("reviews",item.getReviews());
        });
        return "goods/goods-form";
    }

    @PostMapping("/update/selectDisplay")
    @ResponseBody
    public int updateDisplay(@RequestBody DisplayStatusDto displayStatusDto) {
        itemService.updateDisplay(displayStatusDto);
        return 1;
    }

    @GetMapping("/goods/mainCategory/{categoryId}")
    public String goodsMainCategoryForm(@CurrentUser Member member, @PathVariable("categoryId") Long categoryId, Model model,
                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                    @Valid GoodsCategoryListSearchCondition condition,
                                        @RequestParam(value = "brandName", defaultValue = "") String brandName) {
        member(member, model);

        if (brandName != null) {
            model.addAttribute("brandName", brandName);
        }
        model.addAttribute("mainSearch", new MainSearchDto());
        model.addAttribute("nowPage",page);
        model.addAttribute("goForm", new GoodsCategoryListSearchCondition());

        PageRequest of = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "item.itemPrice"));


        Page<Item> itemFetchJoin = itemRepository.findItemFetchJoin2(condition, categoryId, of);


        model.addAttribute("itemFetchJoin", itemFetchJoin);
        model.addAttribute("maxPage", 10);

        //브랜드 이름 중복제거
        List<Item> itemFetchJoinNoConditions = itemRepository.findItemFetchJoinNoConditions2(categoryId);
        List<Brand> collectBrand = itemFetchJoinNoConditions.stream().map(Item::getBrand).distinct().collect(Collectors.toList());

        model.addAttribute("brands",collectBrand);

        List<Category> all = categoryRepository.findAll();

        List<Category> collect = all.stream().collect(Collectors.toList());

        List<Category> mainCategory = new ArrayList<>();
        List<Category> subCategory = new ArrayList<>();


        for (int i = 0; i < collect.size(); i++) {
            if (collect.get(i).getParent() != null) {
                subCategory.add(collect.get(i));
                if(collect.get(i).getParent().getId() == categoryId) {
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
        return "goods/goods-main-category-form";
    }

    @GetMapping("/goods/category/{categoryId}")
    public String goodsCategoryForm(@CurrentUser Member member, @PathVariable("categoryId") Long categoryId, Model model,
                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                    @Valid GoodsCategoryListSearchCondition condition,
                                    @RequestParam(value = "brandName", defaultValue = "") String brandName) {
        member(member, model);

        model.addAttribute("mainSearch", new MainSearchDto());
        model.addAttribute("nowPage",page);
        model.addAttribute("goForm", new GoodsCategoryListSearchCondition());

        PageRequest of = PageRequest.of(page, 10);

        Page<Item> itemFetchJoin = itemRepository.findItemFetchJoin(condition, categoryId, of);
        model.addAttribute("itemFetchJoin", itemFetchJoin);
        model.addAttribute("maxPage", 10);

        List<Item> content = itemFetchJoin.getContent();

        if (brandName != null) {
            model.addAttribute("brandName", brandName);
        }


        //브랜드 이름 중복제거
        List<Item> itemFetchJoinNoConditions = itemRepository.findItemFetchJoinNoConditions(categoryId);
        List<Brand> collectBrand = itemFetchJoinNoConditions.stream().map(Item::getBrand).distinct().collect(Collectors.toList());
        model.addAttribute("brands",collectBrand);

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
    private void category(Model model) {
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
    }

    private void member(@CurrentUser Member member, Model model) {
        if(member != null) {
            Optional<Member> byId = memberRepository.findById(member.getId());
            byId.ifPresent(m -> {
                if(m.getCart() != null) {
                    model.addAttribute("cartSize", m.getCart().getCartGoods().size());
                } else {
                    model.addAttribute("cartSize", 0);
                }
                model.addAttribute(m);
            });
        }


        model.addAttribute("mainSearch", new MainSearchDto());
    }
}
