package portfolioshop.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolioshop.comment.dto.ReviewCommentDto;
import portfolioshop.review.Review;
import portfolioshop.review.ReviewRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final ReviewRepository reviewRepository;
    private final CommentRepository commentRepository;
    public void newComment(ReviewCommentDto reviewCommentDto) {
        Optional<Review> byId = reviewRepository.findById(reviewCommentDto.getReviewId());
        byId.ifPresent(review -> {
            Comment comment = new Comment(review, reviewCommentDto.getComment(), reviewCommentDto.getUsername());
            commentRepository.save(comment);
        });

    }
}
