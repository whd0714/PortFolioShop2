package portfolioshop.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import portfolioshop.category.Category;
import portfolioshop.category.CategoryRepository;
import portfolioshop.item.Item;
import portfolioshop.item.ItemRepository;
import portfolioshop.main.dto.MainSearchDto;
import portfolioshop.member.CurrentUser;
import portfolioshop.member.Member;
import portfolioshop.member.MemberRepository;
import portfolioshop.order.dto.OptionValueDto;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderService orderService;

    @GetMapping("/myPage/order-list")
    public String orderListView() {
        return "member/order-list";
    }

    @GetMapping("/order-form")
    public String orderForm(@CurrentUser Member member, @Valid OptionValueDto optionValueDto, Model model) {

        member(member, model);
        category(model);

        String[] sizes = optionValueDto.getSize();
        int[] counts = optionValueDto.getCount();
        ArrayList<Item> items = new ArrayList<>();

        model.addAttribute("sizes", sizes);
        model.addAttribute("counts", counts);

        Long[] itemId = optionValueDto.getItemId();

        for (Long aLong : itemId) {
            Optional<Item> byId = itemRepository.findById(aLong);
            byId.ifPresent(item -> items.add(item));
        }

        model.addAttribute("items", items);

        model.addAttribute(new OptionValueDto());

        return "/order/order-form";
    }

    @PostMapping("/order-form")
    public String order(@CurrentUser Member member, @Valid OptionValueDto optionValueDto, Errors errors, Model model) {
        if (errors.hasErrors()) {

        }

        if (member != null) {

            model.addAttribute(member);
        }
        orderService.orderProcess(member.getId(), optionValueDto);

        return "redirect:/order-complete";
    }

    @GetMapping("/order-complete")
    public String orderCompleteForm(@CurrentUser Member member, Model model) {

        member(member, model);
        category(model);

        return "order/complete-form";
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
