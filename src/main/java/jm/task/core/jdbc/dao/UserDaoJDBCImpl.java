package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (id INT NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR (45), lastName VARCHAR(45), age TINYINT  NOT NULL, PRIMARY KEY (id))";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
            System.out.println("Таблица создана");
        } catch (SQLException e) {

            //e.printStackTrace();
            System.out.println("Таблица не создана");
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
            System.out.println("Таблица очищена");

        } catch (SQLException e) {
            System.out.println("Таблица не очищена");
        }
    }

        public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
            try (Connection connection = Util.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString( 2, lastName);
                preparedStatement.setByte(3, age);
                preparedStatement.executeUpdate();
                System.out.println("User с именем " + name + " добавлен в базу данных");

            } catch (SQLException e) {
                System.out.println("Юзеры не добавлены");
            }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Юзер с id " + id + " remove");

        } catch (SQLException e) {
            System.out.println("Юзер не remove");
        }

    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    String lastName = resultSet.getString("lastName");
                    Byte age = resultSet.getByte("age");
                    userList.add(new User(id, name, lastName, age));
                }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
            System.out.println("Таблица удалена");

        } catch (SQLException e) {
            System.out.println("Таблица не удалена");
        }

    }
}
