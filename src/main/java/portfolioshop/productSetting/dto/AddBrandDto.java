package portfolioshop.productSetting.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;

@Data
public class AddBrandDto {

    @NotBlank(message = "브랜드명을 입력하세요.")
    private String brandName;

    @NotBlank(message = "브랜드명(영어)을 입력하세요.")
    private String brandNameEng;
//    @Lob
//    private String brandBanner;
    @Lob
    private MultipartFile brandBanner;
}
