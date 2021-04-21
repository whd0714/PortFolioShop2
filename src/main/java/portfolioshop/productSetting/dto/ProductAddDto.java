package portfolioshop.productSetting.dto;

import lombok.Data;
import portfolioshop.item.enumType.Gender;
import portfolioshop.item.enumType.Season;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class ProductAddDto {

    private String mainCategory;

    private String subCategory;

    private String brandName;

    private String itemNo;

    private String itemName;

    private String itemNameEng;

    private int itemPrice;

    private String season;

    private String gender;

    private String subDescription;

    private String description;

}
