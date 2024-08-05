package fa.training.phonestore.service;

import fa.training.phonestore.entity.Brand;
import fa.training.phonestore.entity.Category;
import fa.training.phonestore.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImp implements BrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }
}
