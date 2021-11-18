package jm.task.core.jdbc;



import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        Connection connection = Util.getConnection();
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Igor", "Ivanov", (byte) 22);
        userService.saveUser("Alex", "Petrov", (byte) 28);
        userService.saveUser("Ivan", "Makarov", (byte) 33);
        userService.saveUser("Sergej", "Semenov", (byte) 41);
        userService.getAllUsers();
        System.out.println (userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();

        connection.close();




    }
}
