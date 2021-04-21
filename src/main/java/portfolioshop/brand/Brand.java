package portfolioshop.brand;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import portfolioshop.item.Item;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Brand {

    @Id @GeneratedValue
    @Column(name = "brand_id")
    private Long id;

    private String brandName;

    @Lob
    private byte[] brandBanner;

    @OneToMany(mappedBy = "brand")
    private List<Item> items = new ArrayList<>();

    public Brand(String brandName, byte[] brandBanner) {
        this.brandName = brandName;
        this.brandBanner = brandBanner;
    }


}
