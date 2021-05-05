package portfolioshop.delivery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import portfolioshop.delivery.dto.newDeliveryDto;
import portfolioshop.member.CurrentUser;
import portfolioshop.member.Member;
import portfolioshop.member.MemberRepository;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class DeliveryController {

    private final MemberRepository memberRepository;
    private final DeliveryService deliveryService;

    @GetMapping("/delivery/listPopUp")
    public String deliveryForm(@CurrentUser Member member, Model model) {
        Optional<Member> byId = memberRepository.findById(member.getId());
        byId.ifPresent(m->{
            model.addAttribute(m);
        });

        return "delivery/delivery-form";
    }

    @GetMapping("/new/delivery/listPopUp")
    public String newDeliveryForm(Model model) {
        model.addAttribute(new newDeliveryDto());
        return "delivery/new-delivery-form";
    }

    @PostMapping("/new/delivery/listPopUp")
    public String newDelivery(@CurrentUser Member member, @Valid newDeliveryDto newDeliveryDto, Errors errors) {

        System.out.println(newDeliveryDto);

        deliveryService.createNewDelivery(newDeliveryDto,member.getId());

        return "redirect:/delivery/listPopUp";
    }

}
