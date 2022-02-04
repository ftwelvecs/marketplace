package kz.f12.school.service;

import kz.f12.school.model.dto.CategoryDTO;
import kz.f12.school.model.repository.CategoryRepository;

import java.util.List;

public class CategoryService {

    private CategoryRepository categoryRepository = CategoryRepository.getInstance();

    public void save(CategoryDTO categoryDTO) {
        categoryRepository.save(categoryDTO);
    }

    public void update(CategoryDTO categoryDTO) {
        categoryRepository.update(categoryDTO);
    }

    public void delete(int categoryId) {
        categoryRepository.delete(categoryId);
    }

    public List<CategoryDTO> getAll() {
        return categoryRepository.getAll();
    }

}
