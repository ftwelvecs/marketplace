package kz.f12.school.model.repository;

import kz.f12.school.model.dto.ProductDTO;
import kz.f12.school.util.Mapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository extends AbstractRepository {

    public void save(ProductDTO productDTO) {
        // 1. получаем подключение
        Connection connection = getConnection();
        try {
            // 2. подготовливаем запрос
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into main.products(name, description, quantity, price, weight) values(?,?,?,?,?)");
            // 3. передача параметров
            preparedStatement.setString(1, productDTO.getName());
            preparedStatement.setString(2, productDTO.getDescription());
            preparedStatement.setInt(3, productDTO.getQuantity());
            preparedStatement.setDouble(4, productDTO.getPrice());
            preparedStatement.setDouble(5, productDTO.getWeight());
            // 4. выполнения запроса
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(ProductDTO productDTO) {
        Connection connection = getConnection();
        try {
            String sql = "update main.products set name = ?, description = ?, quantity = ?, price = ?, weight = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productDTO.getName());
            preparedStatement.setString(2, productDTO.getDescription());
            preparedStatement.setInt(3, productDTO.getQuantity());
            preparedStatement.setDouble(4, productDTO.getPrice());
            preparedStatement.setDouble(5, productDTO.getWeight());
            preparedStatement.setInt(6, productDTO.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(int productId) {
        String sql = "delete from main.products where id = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<ProductDTO> getAll() {
        List<ProductDTO> list = new ArrayList<>();
        Connection connection = getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from main.products");
            // пока есть запись в таблице
            while (resultSet.next()) {
                // заполняем список результата
                list.add(Mapper.toProductDTO(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public ProductDTO getById(int productId) {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection.prepareStatement("select * from main.products where id = ?")) {
            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return Mapper.toProductDTO(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ProductDTO();
    }

}
