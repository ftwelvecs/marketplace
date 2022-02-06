package kz.f12.school.service;

import kz.f12.school.model.dto.ProductDTO;
import kz.f12.school.model.repository.ProductRepository;

import java.util.List;

public class ProductService {

    private ProductRepository productRepository = ProductRepository.getInstance();

    public void save(ProductDTO productDTO) {
        productRepository.save(productDTO);
    }

    public void update(ProductDTO productDTO) {
        productRepository.update(productDTO);
    }

    public void delete(int productId) {
        productRepository.delete(productId);
    }

    public List<ProductDTO> getAll() {
        return productRepository.getAll();
    }

    public ProductDTO getById(int id) {
        return productRepository.getById(id);
    }
}
