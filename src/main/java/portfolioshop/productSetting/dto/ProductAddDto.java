package portfolioshop.productSetting.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import portfolioshop.item.enumType.Gender;
import portfolioshop.item.enumType.Season;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ProductAddDto {

    @NotBlank(message = "메인카테고리를 선택하세요")
    private String mainCategory;

    @NotBlank(message = "서브카테고리를 선택하세요")
    private String subCategory;

    @NotNull(message = "현재 등록된 브랜드가 없습니다. 브랜드등록을 하세요")
    private String itemBrandName;

    @NotBlank(message = "품번을 입력하세요")
    private String itemNo;

    @NotBlank(message = "상품명을 입력하세요")
    private String itemName;

    @NotBlank(message = "상품명(영어)를 입력하세요")
    private String itemNameEng;

    @NotNull(message = "상품 가격을 입력하세요")
    private int itemPrice;

    private Season season;

    private Gender gender;

    @NotBlank(message = "삼품의 요약을 작성하세요")
    private String subDescription;

    @NotBlank(message = "상품의 해쉬태그를 입력하세요")
    private String itemTag;

    @Lob
    private String description;

    @Lob
    private MultipartFile itemImage;
}
