package portfolioshop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import portfolioshop.member.dto.SignUpForm;
import portfolioshop.member.validator.SignUpValidator;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final SignUpValidator signUpValidator;

    @InitBinder("signUpForm")
    public void signUpBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(signUpValidator);
    }

    @GetMapping("/sign-up")
    public String signUpForm(Model model) {
        model.addAttribute(new SignUpForm());
        return "member/signUpForm";
    }

    @PostMapping("/sign-up")
    public String signUp(@Valid SignUpForm signUpForm, Errors errors) {
        if (errors.hasErrors()) {
            return "member/signUpForm";
        }
        memberService.processSignUp(signUpForm);
        return "redirect:/";
    }
}
