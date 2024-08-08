package fa.training.phonestore.service.imp;

import fa.training.phonestore.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CategoryServiceImp {
    List<Category> findAll();
    Page<Category> findAllCategories(Pageable pageable);
    Page<Category> findCategoriesByCategoryNameContaining(String Name, Pageable pageable);
    Category saveCategory(Category category);
    boolean existsCategoryByCategoryName(String categoryName);
}
