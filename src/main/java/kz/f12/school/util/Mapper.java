package kz.f12.school.util;

import kz.f12.school.model.dto.CategoryDTO;
import kz.f12.school.model.dto.ProductDTO;
import kz.f12.school.model.repository.CategoryRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Mapper {

    private static CategoryRepository categoryRepository = CategoryRepository.getInstance();

    public static ProductDTO toProductDTO(ResultSet resultSet) {
        // создаем объект продукта
        ProductDTO productDTO = new ProductDTO();
        try {
            // берем данные из таблицы
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            int quantity = resultSet.getInt("quantity");
            double price = resultSet.getDouble("price");
            double weight = resultSet.getDouble("weight");
            CategoryDTO categoryDTO = categoryRepository.getById(resultSet.getInt("category_id"));

            // передаем значения
            productDTO.setId(id);
            productDTO.setName(name);
            productDTO.setDescription(description);
            productDTO.setQuantity(quantity);
            productDTO.setPrice(price);
            productDTO.setWeight(weight);
            productDTO.setCategoryDTO(categoryDTO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productDTO;
    }

    public static CategoryDTO toCategoryDTO(ResultSet resultSet) {
        // создаем объект продукта
        // shift + f6 <- быстро поменять все именования переменной
        CategoryDTO categoryDTO = new CategoryDTO();
        try {
            // берем данные из таблицы
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");

            // передаем значения
            categoryDTO.setId(id);
            categoryDTO.setName(name);
            categoryDTO.setDescription(description);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryDTO;
    }


}
