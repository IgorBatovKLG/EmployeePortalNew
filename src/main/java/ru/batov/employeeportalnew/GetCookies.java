package ru.batov.employeeportalnew;



import ru.batov.employeeportalnew.config.DbConnections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
public class GetCookies {

    public HashMap<String, String> getCookies(){
        HashMap<String, String> cookies = new HashMap<>();
        Connection connection = DbConnections.MainBaseMse();
        try (PreparedStatement statement = connection.prepareStatement("select name, VALUE from cookies")) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                cookies.put(resultSet.getString(1), resultSet.getString(2));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            return cookies;
        }
        return cookies;


    }
}
