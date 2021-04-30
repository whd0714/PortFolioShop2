package portfolioshop.item.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GoodsSelectOptionDto {

    private List<String> sizes = new ArrayList<>();

    private List<Integer> counts = new ArrayList<>();
}
