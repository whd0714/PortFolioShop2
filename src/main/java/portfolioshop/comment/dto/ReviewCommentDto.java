package portfolioshop.comment.dto;

import lombok.Data;

@Data
public class ReviewCommentDto {

    private Long reviewId;
    private String comment;
    private String username;
}
