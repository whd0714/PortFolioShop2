package portfolioshop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import portfolioshop.main.dto.MainSearchDto;
import portfolioshop.member.dto.SignUpForm;
import portfolioshop.member.validator.SignUpValidator;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final SignUpValidator signUpValidator;
    private final MemberRepository memberRepository;

    @InitBinder("signUpForm")
    public void signUpBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(signUpValidator);
    }

    @GetMapping("/sign-up")
    public String signUpForm(Model model) {
        model.addAttribute(new SignUpForm());
        return "member/signup-form";
    }

    @PostMapping("/sign-up")
    public String signUp(@Valid SignUpForm signUpForm, Errors errors) {
        if (errors.hasErrors()) {
            return "member/signup-form";
        }
        memberService.processSignUp(signUpForm);
        return "redirect:/";
    }

    @GetMapping("/myPage")
    public String myPageView(@CurrentUser Member member, Model model)
    {
        if(member != null) {
            Optional<Member> byId = memberRepository.findById(member.getId());
            byId.ifPresent(m->{
                model.addAttribute(m);
                model.addAttribute("mainSearch", new MainSearchDto());
                if(m.getCart() != null) {
                    model.addAttribute("cartSize", m.getCart().getCartGoods().size());
                } else {
                    model.addAttribute("cartSize", 0);
                }
            });

        }
        return "/member/my-page";
    }
}
