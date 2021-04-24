package portfolioshop.tag;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {


    boolean existsByTagName(String s);

    Tag findByTagName(String name);
}
