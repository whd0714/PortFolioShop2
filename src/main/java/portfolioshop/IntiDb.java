package portfolioshop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import portfolioshop.category.Category;
import portfolioshop.category.CategoryRepository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class IntiDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.db1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final CategoryRepository categoryRepository;

        public void db1() {
            Category category = new Category("상의");
            Category category1 = new Category("하의");
            Category category2 = new Category("아우터");
            Category category3 = new Category("반팔");
            Category category4 = new Category("반바지");
            Category category5 = new Category("페딩");
            Category category6 = new Category("긴팔 티셔츠");
            category3.changeCategory(category);
            category4.changeCategory(category1);
            category5.changeCategory(category2);
            category6.changeCategory(category);
            /*em.persist(category);
            em.persist(category1);
            em.persist(category2);
            em.persist(category3);
            em.persist(category4);
            em.persist(category5);*/

            categoryRepository.save(category);
            categoryRepository.save(category1);
            categoryRepository.save(category2);
            categoryRepository.save(category3);
            categoryRepository.save(category4);
            categoryRepository.save(category5);
            categoryRepository.save(category6);
            //category3.changeCategory(category1);
        }
    }
}
