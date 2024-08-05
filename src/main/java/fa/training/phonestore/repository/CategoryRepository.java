package fa.training.phonestore.repository;

import fa.training.phonestore.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Page<Category> findAll(Pageable pageable);
    Page<Category> findCategoryByCategoryNameContaining(String Name, Pageable pageable);
    boolean deleteCategoryByCategoryID(int categoryID);
    boolean existsCategoryByCategoryName(String categoryName);
}
