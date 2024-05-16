package org.project.protectora.servicios.bbdd;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBBDD {
    private static String url, pwd, user;
    private static Connection connection;

    static {
        url = "jdbc:MySQL://localhost/protectora";
        pwd = "";
        user = "root";
    }
    public ConexionBBDD() throws SQLException {
        connection = DriverManager.getConnection(url, user, pwd);
    }
}
