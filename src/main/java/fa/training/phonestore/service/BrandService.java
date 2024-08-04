package fa.training.phonestore.service;

import fa.training.phonestore.entity.Brand;
import fa.training.phonestore.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BrandService {
    public List<Brand> findAll();

}
