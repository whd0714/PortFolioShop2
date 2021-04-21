package portfolioshop.brand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    boolean existsByBrandName(String brandName);

    Brand findByBrandName(String brandName);

    @Query("select b.brandName from Brand b")
    List<String> findAllBrandName();

}
