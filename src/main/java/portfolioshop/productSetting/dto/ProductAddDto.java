package portfolioshop.productSetting.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import portfolioshop.item.enumType.Gender;
import portfolioshop.item.enumType.Season;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;

@Data
public class ProductAddDto {

    private String mainCategory;

    private String subCategory;

    private String itemBrandName;

    private String itemNo;

    private String itemName;

    private String itemNameEng;

    private int itemPrice;

    private Season season;

    private Gender gender;

    private String subDescription;

    private String itemTag;

    @Lob
    private String description;

    @Lob
    private MultipartFile itemImage;
}
