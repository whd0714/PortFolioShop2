package portfolioshop.productSetting.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import portfolioshop.goods.enumType.SaleStatus;
import portfolioshop.item.enumType.Gender;
import portfolioshop.item.enumType.Season;

import javax.persistence.Lob;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductUpdateDto {

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

    private List<String> sizes = new ArrayList<>();
    private List<String> sizes2 = new ArrayList<>();
    private List<Integer> counts = new ArrayList<>();
    private List<Integer> counts2 = new ArrayList<>();

    private List<SaleStatus> saleStatuses = new ArrayList<>();
    private List<SaleStatus> saleStatuses2 = new ArrayList<>();
}
