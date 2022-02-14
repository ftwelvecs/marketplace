package kz.f12.school.util;

import kz.f12.school.model.dto.CategoryDTO;
import kz.f12.school.model.dto.ProductDTO;
import kz.f12.school.model.repository.CategoryRepository;
import org.json.JSONObject;

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

    public static ProductDTO toProductDTO(JSONObject json) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(!json.isNull("id") ? json.getInt("id") : null);
        productDTO.setName(!json.isNull("name") ? json.getString("name") : null);
        productDTO.setDescription(!json.isNull("description") ? json.getString("description") : null);
        productDTO.setQuantity(!json.isNull("quantity") ? json.getInt("quantity") : null);
        productDTO.setPrice(!json.isNull("price") ? json.getDouble("price") : null);
        productDTO.setWeight(!json.isNull("weight") ? json.getDouble("weight") : null);
        productDTO.setCategoryDTO(!json.isNull("category") ? toCategoryDTO(json.getJSONObject("category")) : null);
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

    public static CategoryDTO toCategoryDTO(JSONObject json) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(!json.isNull("id") ? json.getInt("id") : null);
        categoryDTO.setName(!json.isNull("name") ? json.getString("name") : null);
        categoryDTO.setDescription(!json.isNull("description") ? json.getString("description") : null);
        return categoryDTO;
    }


}
