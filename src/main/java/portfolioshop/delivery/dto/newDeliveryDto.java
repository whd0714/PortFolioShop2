package portfolioshop.delivery.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

@Data
public class newDeliveryDto {

    private String deliveryName;

    @Length(max = 3)
    @Pattern(regexp = "^01")
    private String num1;

    @Length(max = 4)
    private String num2;

    @Length(max = 4)
    private String num3;

    private String zipCode;
    private String address;
    private String detailAddress;
}
