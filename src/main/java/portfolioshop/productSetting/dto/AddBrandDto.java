package portfolioshop.productSetting.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Lob;

@Data
public class AddBrandDto {

    private String brandName;

    private String brandNameEng;
//    @Lob
//    private String brandBanner;
    @Lob
    private MultipartFile brandBanner;
}
