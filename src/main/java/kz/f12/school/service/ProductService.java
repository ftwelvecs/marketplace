package kz.f12.school.service;

import kz.f12.school.model.dto.ProductDTO;
import kz.f12.school.model.repository.ProductRepository;

import java.util.List;

public class ProductService {

    private ProductRepository productRepository = new ProductRepository();

    public void save(ProductDTO productDTO) {
        productRepository.save(productDTO);
    }

    public List<ProductDTO> getAll() {
        return productRepository.getAll();
    }

}
