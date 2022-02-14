package kz.f12.school;

import kz.f12.school.model.dto.ProductDTO;
import kz.f12.school.model.repository.ProductRepository;
import org.junit.Test;

public class TestMain {

    @Test
    public void testSavePosition() {
        ProductRepository productRepository = ProductRepository.getInstance();

        ProductDTO productDTO = productRepository.getById(72);

        System.out.println("Name: " + productDTO.getName());
    }

}
