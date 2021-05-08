package portfolioshop.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import portfolioshop.comment.dto.ReviewCommentDto;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/add/comment")
    @ResponseBody
    public int addComment(@RequestBody ReviewCommentDto reviewCommentDto) {
        System.out.println("!!!!!!!!!!!!!!!!!" + reviewCommentDto);
        commentService.newComment(reviewCommentDto);
        return 1;
    }
}
