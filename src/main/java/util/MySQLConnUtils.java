package util;

import dao.UserDAO;
import dao.impl.UserDAOJdbcImpl;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnUtils {
    private static Connection connection;

    public MySQLConnUtils() {
        connection = getMysqlConnection();
    }

    public  Connection getConnection() {
        return connection;
    }

    private static Connection getMysqlConnection() {
        try {

            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("web6_db?").          //db name
                    append("user=root&").          //login
                    append("password=1234").       //password
                    append("&useTimezone=true&serverTimezone=UTC&useSSL=false");       //password

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }


}
