package fa.training.phonestore.service.imp;


import fa.training.phonestore.dto.request.PageableDTO;
import fa.training.phonestore.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductServiceImp {
    List<Product> getHomeTopRateProductSection1();
    Page<Product> getHomeReviewProduct();
    List<Product> findByCategory(int categoryId);

}
