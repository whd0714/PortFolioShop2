package portfolioshop.member.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import portfolioshop.member.dto.SignUpForm;

@Component
@RequiredArgsConstructor
public class SignUpValidator implements Validator {

    private final MemberRepository memberRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(SignUpForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SignUpForm signUpForm = (SignUpForm) o;

        if(memberRepository.existsByUserId(signUpForm.getUserId())) {
            errors.rejectValue("userId", "error.userId", null, "이미 존재하는 아이디입니다.");
        }

        if(!signUpForm.getNewPassword().equals(signUpForm.getPasswordConfirm())) {
            errors.rejectValue("newPassword", "error.password", null, "패스워드가 일치하지 않습니다.");
        }
    }
}
