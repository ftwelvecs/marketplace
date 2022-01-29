package kz.f12.school.model.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractRepository {
    private static final String url = "jdbc:postgresql://localhost:5432/marketplace";
    private static final String login = "postgres";
    private static final String password = "postgres";

    private Connection connection;

    protected Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, login, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
