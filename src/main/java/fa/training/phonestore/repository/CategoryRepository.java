package fa.training.phonestore.repository;

import fa.training.phonestore.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Page<Category> findAll(Pageable pageable);
    Page<Category> findCategoryByNameContaining(String Name, Pageable pageable);
    boolean deleteCategoryByCategoryId(int categoryID);
    boolean existsCategoryByName(String categoryName);
    public List<Category>  findAll();
}
