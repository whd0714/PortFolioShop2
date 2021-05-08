package portfolioshop.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import portfolioshop.category.Category;
import portfolioshop.member.Member;
import portfolioshop.review.Review;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    private String description;

    private LocalDateTime commentDate;

    private String username;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Comment> child = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    public Comment(Review review, String comment, String username) {
        changeReview(review);
        this.description = comment;
        this.username = username;
        this.commentDate = LocalDateTime.now();

    }

    public void changeReview(Review review) {
        this.review = review;
        review.getComments().add(this);
    }

    public void changeMember(Member member) {
        this.member = member;
        member.getComments().add(this);
    }
    
}
