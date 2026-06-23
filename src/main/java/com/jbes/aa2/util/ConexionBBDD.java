package com.jbes.aa2.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBBDD {

    private static final String URL = "jdbc:mariadb://localhost:3306/entrega";

    private static final String USER = "root";
    private static final String PASSWORD = "joeyblack97";

    public static Connection getConexion() throws SQLException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error: No se ha encontrado el driver de MariaDB en el proyecto.", e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}