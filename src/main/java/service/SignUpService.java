package service;

import jdbc.ConnectionJDBC;
import model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUpService {
    public Account getAccount(String username) throws SQLException {
        Account account = null;
        Connection connection = ConnectionJDBC.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from account where username = ?");
        preparedStatement.setString(1,username);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String password = resultSet.getString(3);
            int role = resultSet.getInt(4);
            account = new Account(id,username,password,role);
        }
        return account;
    }
    public void insertAccount(String username, String password){
        Connection connection = ConnectionJDBC.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("insert into account(username, password, role) values (?,?,3)");
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
