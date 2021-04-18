package portfolioshop.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {


    @GetMapping("/myPage/order-list")
    public String orderListView() {
        return "member/order-list";
    }
}
