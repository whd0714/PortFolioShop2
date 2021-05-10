package portfolioshop.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import portfolioshop.member.CurrentUser;
import portfolioshop.member.Member;
import portfolioshop.orderItem.OrderItem;
import portfolioshop.orderItem.OrderItemRepository;
import portfolioshop.review.dto.NewReviewDto;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final OrderItemRepository orderItemRepository;
    private final ReviewService reviewService;

    @GetMapping("/new/review/{orderItemId}")
    public String reviewForm(@PathVariable Long orderItemId, @CurrentUser Member member, Model model) {

        if (member != null) {

            model.addAttribute(member);
        }

        Optional<OrderItem> byId = orderItemRepository.findById(orderItemId);
        byId.ifPresent(orderItem ->{
            model.addAttribute(orderItem);
        });

        model.addAttribute(new NewReviewDto());
        return "review/newReview";
    }

    @PostMapping("/new/review/process")
    @ResponseBody
    public int newReview(@CurrentUser Member member, @RequestBody NewReviewDto newReviewDto) {

        reviewService.newReview(member.getId(), newReviewDto);

        return 1;
    }
}
