package portfolioshop.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import portfolioshop.member.CurrentUser;
import portfolioshop.member.Member;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(@CurrentUser Member member, Model model) {
        if(member != null) {
            model.addAttribute(member);
        }

        return "index";
    }
}
