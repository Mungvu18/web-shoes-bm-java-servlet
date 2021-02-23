package service;

import jdbc.ConnectionJDBC;
import model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountServlet implements IAccountServlet{
    @Override
    public List<Account> fillAll() {
        List<Account> accounts = new ArrayList<>();
        Connection connection = ConnectionJDBC.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from account");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String username = resultSet.getString(2);
                String password = resultSet.getString(3);
                String status = resultSet.getString(4);
                int role = resultSet.getInt(5);
                Account account = new Account(id,username,password,status,role);
                accounts.add(account);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return accounts;
    }

    @Override
    public void save(Account account) {
        Connection connection = ConnectionJDBC.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("insert into account(username,password,status,role)\n" +
                    "VALUES(?,?,?,?,?)");
            preparedStatement.setString(1,account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setString(3, account.getStatus());
            preparedStatement.setDouble(4,account.getRole());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Account update(Account account, int id) {
        Connection connection = ConnectionJDBC.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("update account set username = ?,password = ?,status= ?, role = ? where id= ?");
            preparedStatement.setInt(5, id);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setString(3, account.getStatus());
            preparedStatement.setDouble(4, account.getRole());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return account;
    }

    @Override
    public void delete(int id) {
        Connection connection = ConnectionJDBC.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("delete from account where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Account findById(int id) {
        Account account = null;
        Connection connection = ConnectionJDBC.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from account where  id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String username = resultSet.getString(2);
                String password = resultSet.getString(3);
                String status = resultSet.getString(4);
                int role = resultSet.getInt(5);
                account = new Account(id,username,password,status,role);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return account;
    }

    @Override
    public Account findByName(String username) {
        Account account = null;
        Connection connection = ConnectionJDBC.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from account where  name like ?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String password = resultSet.getString(3);
                String status = resultSet.getString(4);
                int role = resultSet.getInt(5);
                account = new Account(id,username,password,status,role);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return account;
    }
}

