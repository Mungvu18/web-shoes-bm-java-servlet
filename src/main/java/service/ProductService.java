package service;

import jdbc.ConnectionJDBC;
import model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProductService implements IProductService {
    public List<Product> findBestSale() {
        List<Product> productList = new ArrayList<>();
        Connection connection = ConnectionJDBC.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from product order by id desc limit 1");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String image = resultSet.getString(3);
                String description = resultSet.getString(4);
                Double price = resultSet.getDouble(5);
                int id_category = resultSet.getInt(6);
                int id_account = resultSet.getInt(7);
                Product product = new Product(id, name, image, description, price, id_category, id_account);
                productList.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productList;
    }

    @Override
    public List<Product> fillAll() {
        List<Product> products = new ArrayList<>();
        Connection connection = ConnectionJDBC.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from product");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String image = resultSet.getString(3);
                String description = resultSet.getString(4);
                Double price = resultSet.getDouble(5);
                int id_category = resultSet.getInt(6);
                int id_account = resultSet.getInt(7);
                Product product = new Product(id, name, image, description, price, id_category, id_account);
                products.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }

    @Override
    public void save(Product product) {
        Connection connection = ConnectionJDBC.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("insert into product(name, image, description, price, id_category, id_account)\n" +
                    "VALUES(?,?,?,?,?,?)");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getImage());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setDouble(4,product.getPrice());
            preparedStatement.setInt(5,product.getId_category());
            preparedStatement.setInt(6,product.getId_account());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Product update(Product product, int id) {
        Connection connection = ConnectionJDBC.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("update product set name = ?,image = ?,description = ?, price = ?, id_category = ?,id_account =? where id= ?");
            preparedStatement.setInt(7, id);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getImage());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setDouble(4,product.getPrice());
            preparedStatement.setInt(6,product.getId_account());
            preparedStatement.setInt(5,product.getId_category());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }

    @Override
    public void delete(int id) {
        Connection connection = ConnectionJDBC.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("delete from product where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Product findById(int id) {
        Product product = null;
        Connection connection = ConnectionJDBC.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from product where  id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString(2);
                String image = resultSet.getString(3);
                String description = resultSet.getString(4);
                Double price = resultSet.getDouble(5);
                int id_category = resultSet.getInt(6);
                int id_account = resultSet.getInt(7);
                product = new Product(id,name,image,description,price,id_category,id_account);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> findByName(String name) {
        Product product = null;
        List<Product> products = null;
        Connection connection = ConnectionJDBC.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from product where  name like ?");
            preparedStatement.setString(1,"%"+name+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String image = resultSet.getString(3);
                String description = resultSet.getString(4);
                Double price = resultSet.getDouble(5);
                int id_category = resultSet.getInt(6);
                int id_account = resultSet.getInt(7);
                product = new Product(id,name,image,description,price,id_category,id_account);
                products.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }
}
