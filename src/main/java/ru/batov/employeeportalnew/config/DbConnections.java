package ru.batov.employeeportalnew.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnections {

    public static Connection MainBaseMse(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:C:/TEMP/db/MainBaseMse.db");
        } catch (SQLException e) {
            System.out.println("Проблемы с подключением к базе данных");
            e.printStackTrace();
        }
        return connection;
    }
}
