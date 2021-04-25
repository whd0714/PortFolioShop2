package portfolioshop.tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {


    boolean existsByTagName(String s);

    Tag findByTagName(String name);

    @Query("select t from Tag t where t.itemTags.size = 0")
    List<Tag> findNullTag();
}
