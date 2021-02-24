package service.classModle;

import jdbc.ConnectionJDBC;
import model.Account;
import service.iService.IAccountService;

import javax.servlet.RequestDispatcher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountService implements IAccountService {
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
                Account account = new Account(id,username,password,role,status);
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

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update account set username = ?,password = ?,status= ?, role = ? where id= ?");
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
                account = new Account(id,username,password,role,status);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return account;
    }

    @Override
    public List<Account> findByName(String username) {
        Account account = null;
        List<Account> accounts = null;
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
                account = new Account(id,username,password,role,status);
                accounts.add(account);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return accounts;
    }
    public void designAccount(int id) {
        AccountService accountService = new AccountService();
        Connection connection = ConnectionJDBC.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("update account set status= ? where id= ?");
            preparedStatement.setInt(2, id);
            if( accountService.findById(id).getStatus() == "on"){
                preparedStatement.setString(1, "false");
            }else if(accountService.findById(id).getStatus()=="false"){
                preparedStatement.setString(1, "on");
            }
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

