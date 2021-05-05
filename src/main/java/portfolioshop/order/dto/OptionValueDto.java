package portfolioshop.order.dto;

import lombok.Data;

@Data
public class OptionValueDto {

    private String[] size;
    private int[] count;
    private Long[] itemId;
}
