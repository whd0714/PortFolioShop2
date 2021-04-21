package portfolioshop.brand;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    boolean existsByBrandName(String brandName);

    Brand findByBrandName(String brandName);
}
