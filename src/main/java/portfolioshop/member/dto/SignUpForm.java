package portfolioshop.member.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class SignUpForm {

    @NotBlank
    @Length(min = 3, max = 10)
    @Pattern(regexp = "^[a-zA-Z0-9-_]{3,10}$")
    private String userId;

    @NotBlank
    @Length(min = 8, max = 30)
    private String newPassword;

    @NotBlank
    @Length(min = 8, max = 30)
    private String passwordConfirm;

    @NotBlank
    @Length(min = 1, max = 10)
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣]{1,10}$")
    private String username;
}
