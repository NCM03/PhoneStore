package fa.training.phonestore.service;

import fa.training.phonestore.dto.Request.ProductInfoDTO;
import fa.training.phonestore.entity.Product;
import fa.training.phonestore.entity.ProductImage;
import fa.training.phonestore.entity.ProductInfo;
import fa.training.phonestore.entity.ProductStatus;
import fa.training.phonestore.repository.ProductImagineRepository;
import fa.training.phonestore.repository.ProductRepository;
import fa.training.phonestore.repository.ProductInfoRepository;
import fa.training.phonestore.repository.ProductStatusRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ManageProductServiceIMP implements ManageProductService {
    @Autowired
    private StorageService storageService;
    @Autowired
    private ProductStatusRepository productStatusRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Autowired
    private ProductImagineRepository productImagineRepository;

    @Override
    public void replaceProduct(Product newProduct) {
        // Lấy sản phẩm cũ từ cơ sở dữ liệu
        Optional<Product> optionalOldProduct = productRepository.findById(newProduct.getProductId());
        if (optionalOldProduct.isPresent()) {
            Product oldProduct = optionalOldProduct.get();
            System.out.println("oke"+ oldProduct.getProductId());
            // Cập nhật thông tin sản phẩm cũ bằng thông tin sản phẩm mới
            oldProduct.setProductName(newProduct.getProductName());
            System.out.println("oke"+oldProduct.getProductName());
            oldProduct.setImageData(newProduct.getImageData());
            oldProduct.setComment(newProduct.getComment());
            oldProduct.setCategoryId(newProduct.getCategoryId());
            oldProduct.setBrandId(newProduct.getBrandId());
            // Lưu sản phẩm đã được cập nhật vào cơ sở dữ liệu
            productRepository.save(oldProduct);
        }
    }

    @Override
    public void replaceProductInfo(ProductInfo newProductInfo, String url, int imageId) {
        Optional<ProductInfo> optionalOldProduct = productInfoRepository.findById(newProductInfo.getProductInfoId());
        if (optionalOldProduct.isPresent()) {
            Optional<ProductStatus> productStatus = productStatusRepository.findById(newProductInfo.getProductInfoStatus().getStatusId());
            ProductStatus oldProductStatus = productStatus.get();
            ProductInfo oldProductInfo = optionalOldProduct.get();
            // Cập nhật thông tin sản phẩm cũ bằng thông tin sản phẩm mới
            oldProductInfo.setProductInfoName(newProductInfo.getProductInfoName());
            oldProductInfo.setRam(newProductInfo.getRam());
            oldProductInfo.setCapacity(newProductInfo.getCapacity());
            oldProductInfo.setColor(newProductInfo.getColor());
            oldProductInfo.setQuantity(newProductInfo.getQuantity());
            oldProductInfo.setDescription(newProductInfo.getDescription());
            oldProductInfo.setPrice(newProductInfo.getPrice());
            oldProductInfo.setProductInfoStatus(oldProductStatus);
            // Lưu sản phẩm đã được cập nhật vào cơ sở dữ liệu
            productInfoRepository.save(oldProductInfo);
        }
        Optional<ProductImage> productImage = productImagineRepository.findById(imageId);
        if (productImage.isPresent()) {
            ProductImage oldProductImage = productImage.get();
            oldProductImage.setImageURL(url);
            productImagineRepository.save(oldProductImage);

        }
    }

    public List<ProductImage> saveImages(List<String> imageNames) {
        List<ProductImage> productImages = new ArrayList<>();
        for (String imageName : imageNames) {
            // Lưu file ảnh vào thư mục
            Path path = Paths.get("path/to/save/images/" + imageName);
            // Files.copy(source, path, StandardCopyOption.REPLACE_EXISTING);
            // Tạo đối tượng ProductImage và thêm vào danh sách
            ProductImage productImage = new ProductImage();
            productImage.setImageURL(path.toString());
            productImages.add(productImage);
        }
        return productImages;
    }

    public void saveProductInfo(Product product, List<ProductInfoDTO> productInfoList, MultipartFile[] fileSupProduct) {
        List<MultipartFile> fileSupProductList = new ArrayList<>(List.of(fileSupProduct));
        for (ProductInfoDTO productInfoDTO : productInfoList) {
            ProductInfo productInfo = new ProductInfo();
            productInfo.setProduct(product);
            productInfo.setProductInfoName(product.getProductName() + " " + productInfoDTO.getCapacity() + " " + productInfoDTO.getColor());
            productInfo.setCapacity(Integer.parseInt(productInfoDTO.getCapacity()));
            productInfo.setRam(Integer.parseInt(productInfoDTO.getRam()));
            productInfo.setColor(productInfoDTO.getColor());
            productInfo.setQuantity(Integer.parseInt(productInfoDTO.getQuantity()));
            productInfo.setPrice(BigDecimal.valueOf(Integer.parseInt(productInfoDTO.getPrice())));
            productInfo.setDescription(productInfoDTO.getDescription());
            productInfo.setImportDate(LocalDateTime.now());
            productInfo.setQuantitySold(0);
            productInfoRepository.save(productInfo);
            System.out.println("lỗi");
            // Tạo danh sách sao lưu để tránh ConcurrentModificationException
            List<MultipartFile> filesToRemove = new ArrayList<>();

            for (MultipartFile file : fileSupProductList) {
                System.out.println("Xử lý tệp: " + file.getOriginalFilename());
                if (productInfoDTO.getImageUrl().contains(file.getOriginalFilename())) {
                    String extension = FilenameUtils.getExtension(file.getOriginalFilename());
                    String baseURL = String.valueOf(productInfo.getProductInfoId());
                    String newURL = baseURL;
                    int counter = 1;
                    // Kiểm tra và thay đổi URL nếu đã tồn tại
                    while (!productImagineRepository.findProductImagesByImageURL(newURL + "." + extension).isEmpty()) {
                        newURL = baseURL + counter;
                        counter++;
                        System.out.println("Đổi URL thành: " + newURL);
                    }
                    storageService.store(file, newURL);
                    ProductImage productImage = new ProductImage();
                    productImage.setImageURL(newURL + "." + extension);
                    productImage.setProductID(product.getProductId());
                    productImage.setProductInfoID(productInfo.getProductInfoId());
                    productImagineRepository.save(productImage);
                    System.out.println("Đã lưu: " + productImage.getImageURL());
                    filesToRemove.add(file);
                }
            }

            fileSupProductList.removeAll(filesToRemove);
        }
    }

    public void saveProduct(Product product, String extension) {
        product.setWarrantyPeriod(LocalDateTime.now());
        productRepository.save(product);
    }


}
