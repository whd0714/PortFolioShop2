package portfolioshop.productSetting.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDelDto {

    private List<String> delNames = new ArrayList<>();
}
