package portfolioshop.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewReviewDto {

    private Long itemId;

    private String height;

    private String weight;

    private String size;

    private String color;

    private String thickness;

    private String description;

    private String itemSize;

}
