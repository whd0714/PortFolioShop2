package portfolioshop.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolioshop.item.Item;
import portfolioshop.item.ItemRepository;
import portfolioshop.member.Member;
import portfolioshop.member.MemberRepository;
import portfolioshop.review.dto.NewReviewDto;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    public void newReview(Long memberId, NewReviewDto newReviewDto) {
        Review review = new Review(newReviewDto.getHeight(), newReviewDto.getWeight(), newReviewDto.getSize(), newReviewDto.getColor(),
                newReviewDto.getThickness(), newReviewDto.getDescription(), newReviewDto.getItemSize());

        Optional<Member> memberById = memberRepository.findById(memberId);
        memberById.ifPresent(member -> {
            review.changeMember(member);
        });

        Optional<Item> itemById = itemRepository.findById(newReviewDto.getItemId());
        itemById.ifPresent(item -> {
            review.changeItem(item);
        });

        reviewRepository.save(review);
    }
}


