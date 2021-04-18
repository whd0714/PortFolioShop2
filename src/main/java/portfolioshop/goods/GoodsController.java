package portfolioshop.goods;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GoodsController {

    @GetMapping("/goods")
    public String goodsForm(){
        return "goods/goods-form";
    }

}
