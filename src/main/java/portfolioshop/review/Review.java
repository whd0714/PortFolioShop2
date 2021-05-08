package portfolioshop.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import portfolioshop.comment.Comment;
import portfolioshop.item.Item;
import portfolioshop.member.Member;
import portfolioshop.review.enumType.ColorStatus;
import portfolioshop.review.enumType.SizeStatus;
import portfolioshop.review.enumType.ThickStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    private String height;

    private String weight;

    private String size;

    private String color;

    private String thickness;

    private String description;

    private LocalDateTime reviewDate;

    private String itemSize;

    @OneToMany(mappedBy = "review", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Review(String height, String weight, String size, String color, String thickness, String description, String itemSize) {
        this.height = height + "cm";
        this.weight = weight + "kg";
        this.size = size;
        this.color = color;
        this.thickness = thickness;
        this.description = description;
        this.reviewDate = LocalDateTime.now();
        this.itemSize = itemSize;

    }

    public void changeItem(Item item) {
        this.item = item;
        item.getReviews().add(this);
    }

    public void changeMember(Member member) {
        this.member = member;
        member.getReviews().add(this);
    }
}
