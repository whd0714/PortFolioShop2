package portfolioshop.main;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import portfolioshop.brand.Brand;
import portfolioshop.category.Category;
import portfolioshop.category.CategoryRepository;
import portfolioshop.item.Item;
import portfolioshop.item.ItemRepository;
import portfolioshop.item.ItemService;
import portfolioshop.item.dto.queryDto.GoodsCategoryListSearchCondition;
import portfolioshop.item.searchQuery.ItemCategoryCondition;
import portfolioshop.main.dto.MainSearchDto;
import portfolioshop.main.dto.TagSearchDto;
import portfolioshop.member.CurrentUser;
import portfolioshop.member.Member;
import portfolioshop.productSetting.dto.CategoryDelDto;
import portfolioshop.productSetting.dto.CategoryNameDto;
import portfolioshop.productSetting.dto.MainCategoryDto;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;
    private final ItemService itemService;

    @GetMapping("/")
    public String home(@CurrentUser Member member, Model model) {
        if(member != null) {
            model.addAttribute(member);
        }
        model.addAttribute("mainSearch", new MainSearchDto());

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
        //List<Item> newItemByCategory = itemRepository.findNewItemByCategory(condition);

       // model.addAttribute("newItemByCategory", newItemByCategory);
        List<Item> itemFromBestView = itemRepository.findItemFromBestView();

        List<Item> itemFromBestSale = itemRepository.findItemFromBestSale();

        List<Item> itemFromNewItem = itemRepository.findItemFromNewItem();


        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "/member/login-form";
    }

    @GetMapping("/main/search")
    public String mainSearchForm(@ModelAttribute MainSearchDto mainSearchDto,
                                 @CurrentUser Member member, Model model,
                                 @RequestParam(value = "page", defaultValue = "0") int page,
                                 @RequestParam(value = "tagName", defaultValue = "") String tagName,
                                 @RequestParam(value = "query", defaultValue = "") String query) {
        if(member != null) {
            model.addAttribute(member);
        }


        model.addAttribute("mainSearch", new MainSearchDto());

        PageRequest of = PageRequest.of(0, 10);
        Page<Item> itemFromQueryAndTag = itemRepository.findItemFromQuery(mainSearchDto, of);

        List<Item> content = itemFromQueryAndTag.getContent();




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


        return "search/main-form";
    }
}
