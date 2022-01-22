package kz.f12.school.model.repository;

import kz.f12.school.model.dto.ProductDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private static final String url = "jdbc:postgresql://localhost:5432/marketplace";
    private static final String login = "postgres";
    private static final String password = "postgres";

    private Connection connection;

    private Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, login, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<ProductDTO> getAll() {
        List<ProductDTO> list = new ArrayList<>();
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from main.products");
            // пока есть запись в таблице
            while (resultSet.next()) {
                // берем данные из таблицы
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                double weight = resultSet.getDouble("weight");

                // создаем объект продукта и передаем туда значения
                ProductDTO productDTO = new ProductDTO();
                productDTO.setName(name);
                productDTO.setDescription(description);
                productDTO.setQuantity(quantity);
                productDTO.setPrice(price);
                productDTO.setWeight(weight);

                // заполняем список результата
                list.add(productDTO);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

}
