package kz.f12.school.util;

import kz.f12.school.model.dto.ProductDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Mapper {

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

            // передаем значения
            productDTO.setId(id);
            productDTO.setName(name);
            productDTO.setDescription(description);
            productDTO.setQuantity(quantity);
            productDTO.setPrice(price);
            productDTO.setWeight(weight);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productDTO;
    }

}
