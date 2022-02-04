package kz.f12.school.model.repository;

import kz.f12.school.model.dto.CategoryDTO;
import kz.f12.school.util.Mapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository extends AbstractRepository {

    private static final CategoryRepository INSTANCE = new CategoryRepository();

    private CategoryRepository() {
    }

    public static CategoryRepository getInstance() {
        return INSTANCE;
    }

    public void save(CategoryDTO categoryDTO) {
        // 1. получаем подключение
        Connection connection = getConnection();
        try {
            // 2. подготовливаем запрос
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into main.category(name, description) values(?,?)");
            // 3. передача параметров
            preparedStatement.setString(1, categoryDTO.getName());
            preparedStatement.setString(2, categoryDTO.getDescription());
            // 4. выполнения запроса
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(CategoryDTO categoryDTO) {
        Connection connection = getConnection();
        try {
            String sql = "update main.category set name = ?, description = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, categoryDTO.getName());
            preparedStatement.setString(2, categoryDTO.getDescription());
            preparedStatement.setInt(3, categoryDTO.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(int categoryId) {
        String sql = "delete from main.category where id = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, categoryId);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<CategoryDTO> getAll() {
        List<CategoryDTO> list = new ArrayList<>();
        Connection connection = getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from main.category");
            // пока есть запись в таблице
            while (resultSet.next()) {
                // заполняем список результата
                list.add(Mapper.toCategoryDTO(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public CategoryDTO getById(int categoryId) {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection.prepareStatement("select * from main.category where id = ?")) {
            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return Mapper.toCategoryDTO(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new CategoryDTO();
    }
}
