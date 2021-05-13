package portfolioshop.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewCommentDto {

    private Long reviewId;
    private String comment;
    private String username;
}
