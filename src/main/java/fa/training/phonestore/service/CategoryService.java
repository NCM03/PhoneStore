package fa.training.phonestore.service;

import fa.training.phonestore.entity.Category;
import fa.training.phonestore.repository.CategoryRepository;
import fa.training.phonestore.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryService implements CategoryServiceImp {
@Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<Category> findAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Page<Category> findCategoriesByCategoryNameContaining(String Name, Pageable pageable) {
        return categoryRepository.findCategoryByNameContaining(Name, pageable);
    }
    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }
    @Override
    public boolean existsCategoryByCategoryName(String categoryName) {
        return categoryRepository.existsCategoryByName(categoryName);
    }
}
