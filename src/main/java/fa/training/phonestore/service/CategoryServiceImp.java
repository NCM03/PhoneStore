package fa.training.phonestore.service;

import fa.training.phonestore.entity.Brand;
import fa.training.phonestore.entity.Category;
import fa.training.phonestore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService  {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

}
