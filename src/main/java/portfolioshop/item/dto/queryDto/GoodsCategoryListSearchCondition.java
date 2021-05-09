package portfolioshop.item.dto.queryDto;

import lombok.Data;

//해당 카테고리에 포함된 상품 쿼리
@Data
public class GoodsCategoryListSearchCondition {

    private String brandName;
    private String sort;

}
