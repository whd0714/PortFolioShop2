package portfolioshop.productSetting.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import portfolioshop.goods.enumType.SaleStatus;
import portfolioshop.item.enumType.Gender;
import portfolioshop.item.enumType.Season;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductUpdateDto {

    @NotBlank(message = "메인카테고리를 선택하세요")
    private String mainCategory;

    private String subCategory;

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

    private List<String> sizes = new ArrayList<>();
    private List<String> sizes2 = new ArrayList<>();
    private List<Integer> counts = new ArrayList<>();
    private List<Integer> counts2 = new ArrayList<>();

    private List<SaleStatus> saleStatuses = new ArrayList<>();
    private List<SaleStatus> saleStatuses2 = new ArrayList<>();
}
