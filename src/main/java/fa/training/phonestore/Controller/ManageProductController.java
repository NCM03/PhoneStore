package fa.training.phonestore.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fa.training.phonestore.dto.Request.ProductDTO;
import fa.training.phonestore.dto.Request.ProductInfoDTO;
import fa.training.phonestore.entity.*;
import fa.training.phonestore.mapper.ProductMapper;
import fa.training.phonestore.repository.ProductImagineRepository;
import fa.training.phonestore.service.*;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
public class ManageProductController {
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private ManageProductService manageProductService;
    @Autowired
    private ProductImagineRepository productImagineRepository;

    @RequestMapping(value = "/manage-product", method = RequestMethod.GET)
    public String manageProduct(Model model) {
        List<Category> category = categoryService.findAll();
        List<Brand> brand = brandService.findAll();
        List<ProductInfo> productInfos = productInfoService.findAll();
        List<Product> product = productService.findAll();
        ProductDTO productForm = new ProductDTO();
        Product productEditForm = new Product();
        ProductInfo productInfoEditForm = new ProductInfo();
        model.addAttribute("productInfoEdit", productInfoEditForm);
        model.addAttribute("productEdit", productEditForm);
        model.addAttribute("product", product);
        model.addAttribute("productForm", productForm);
        model.addAttribute("productInfo", productInfos);
        model.addAttribute("category", category);
        model.addAttribute("brand", brand);
        model.addAttribute("mess", "oke");
        model.addAttribute("isManageProductPage", true);
        return "manage-product";
    }


    @RequestMapping(value = "/add-product", method = RequestMethod.POST)
    public String addProduct(@RequestParam("category") int category,
                                   @RequestParam("brand") int brand,
                                   @ModelAttribute("product") Product product,
                                   @RequestPart("imgProduct") MultipartFile imgProduct,
                                   @RequestPart("files") MultipartFile[] fileArray,
                                   @RequestParam("products") String productsJson
//                             RedirectAttributes redirectAttributes
                             ) {
        try {
            System.out.println("imgProduct: " + imgProduct.getOriginalFilename());
            System.out.println("Number of files: " + fileArray.length);

            product.setCategoryId(category);
            product.setBrandId(brand);
// Xử lý ảnh
            String extension = FilenameUtils.getExtension(imgProduct.getOriginalFilename());
            String baseURL = String.valueOf(product.getProductName());
            String newURL = baseURL;
            int counter = 1;
            while (productService.findImageData(newURL + "." + extension)) {
                newURL = baseURL + counter;
                counter++;
//                System.out.println("Đổi URL thành: " + newURL);
            }
            storageService.store(imgProduct, newURL);
            product.setImageData(newURL + "." + extension);
            manageProductService.saveProduct(product, extension);
            ObjectMapper objectMapper = new ObjectMapper();
            if (productsJson.startsWith(",")) {
                productsJson = productsJson.substring(1);
            }
            List<ProductInfoDTO> productInfoList = objectMapper.readValue(productsJson, new TypeReference<List<ProductInfoDTO>>() {});
            manageProductService.saveProductInfo(product, productInfoList, fileArray);
//            redirectAttributes.addFlashAttribute("successMessage", "Product added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/manage-product";
    }


    @RequestMapping(value = "/edit_mainProduct", method = RequestMethod.POST)
    public String editMainProduct(@RequestParam("editProductId") int productId,
                                  // @RequestParam("brand") int brand,
                                  @ModelAttribute("productEdit") Product productEditForm,
                                  @RequestParam("editImgProduct") MultipartFile imgProduct
                                  // RedirectAttributes redirectAttributes
    ) {
        try {
            Product product = productService.findById(productId);
            storageService.delete(product.getImageData());
            System.out.println("imgName ="+imgProduct.getOriginalFilename());
            String extension = FilenameUtils.getExtension(imgProduct.getOriginalFilename());
            if (imgProduct != null && !imgProduct.isEmpty()) {
                String baseURL = String.valueOf(product.getProductName()+"z");
                String newURL = baseURL;
                int counter = 1;
                while (productService.findImageData(newURL + "." + extension)) {
                    newURL = baseURL + counter;
                    counter++;
//                System.out.println("Đổi URL thành: " + newURL);
                }

                storageService.store(imgProduct, newURL);
                productEditForm.setImageData(newURL+ "." + extension);
            }else{
                productEditForm.setImageData(product.getImageData());
            }
            productEditForm.setProductId(productId);
            manageProductService.replaceProduct(productEditForm);
            // product.setCategoryId(category);
            // product.setBrandId(brand);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/manage-product";
    }


    @RequestMapping(value = "/edit_productInfo", method = RequestMethod.POST)
    public String editProductInfo(
                                  @ModelAttribute("productInfoEdit") ProductInfo productInfoEditForm,
                                  @RequestParam("oldImgInfoId") int oldImgInfoId,
                                  @RequestParam("oldImgInfo") String oldImgInfo,
                                  @RequestParam("editImgProductInfo") MultipartFile imgProductInfo
                                  // RedirectAttributes redirectAttributes
    ) {
        try {
            Optional<ProductInfo> productInfo = productInfoService.findById(productInfoEditForm.getProductInfoId());
            String newURL = oldImgInfo;
            String extension = FilenameUtils.getExtension(imgProductInfo.getOriginalFilename());
            if (imgProductInfo != null && !imgProductInfo.isEmpty()) {
                storageService.delete(oldImgInfo);
                String baseURL = String.valueOf(productInfoEditForm.getProductInfoId());
                newURL = baseURL;
                int counter = 1;
                // Kiểm tra và thay đổi URL nếu đã tồn tại
                while (!productImagineRepository.findProductImagesByImageURL(newURL + "." + extension).isEmpty()) {
                    newURL = baseURL + counter;
                    counter++;
                    System.out.println("Đổi URL thành: " + newURL);
                }
                storageService.store(imgProductInfo, newURL);
            }else{
            }
            manageProductService.replaceProductInfo(productInfoEditForm, newURL+"." + extension , oldImgInfoId);
            // product.setCategoryId(category);
            // product.setBrandId(brand);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/manage-product";
    }


    public String getFileNameWithoutExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("File name is null or empty");
        }
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex == -1) {
            return fileName; // Trường hợp không có dấu chấm trong tên tệp
        }
        return fileName.substring(0, lastDotIndex);
    }
}



