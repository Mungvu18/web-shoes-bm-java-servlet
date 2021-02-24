package controller;

import model.Category;
import model.Product;
import service.classModle.CategoryService;
import service.classModle.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ManagerProductServlet",urlPatterns = "/managerProduct")
public class ManagerProductServlet extends HttpServlet {
    ProductService productService = new ProductService();
    CategoryService categoryService = new CategoryService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "edit":
                editProductJsp(request,response);
                break;
            case "create":
                addProduct(request,response);
                break;
        }
    }


    private void editProductJsp(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String image = request.getParameter("image");
        String description = request.getParameter("description");
        Double price = Double.parseDouble(request.getParameter("price"));
        int id_category = Integer.parseInt(request.getParameter("id_category"));
        int id_account = Integer.parseInt(request.getParameter("id_account"));
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = new Product(id,name,image,description,price,id_category,id_account);
        productService.update(product,id);
        try {
            response.sendRedirect("/managerProduct");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void addProduct(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String image = request.getParameter("image");
        String description = request.getParameter("description");
        Double price = Double.valueOf(request.getParameter("price"));
        int id_category = Integer.parseInt(request.getParameter("id_category"));
        int id_account = Integer.parseInt(request.getParameter("id_account"));
        Product product = new Product(name,image,description,price,id_category,id_account);
        productService.save(product);
        try {
            response.sendRedirect("/managerProduct");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "":
                showManagerProductjsp(request,response);
                break;
            case "edit":
                showEditProductJsp(request,response);
                break;
            case "delete":
                  deleteProduct(request,response);
                break;
            case "create":
                ShowAddProduct(request,response);
                break;
        }
    }

    private void ShowAddProduct(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("CreateProduct.jsp");
        List<Category> categories = categoryService.fillAll();
        request.setAttribute("listC",categories);
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showEditProductJsp(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditProduct.jsp");
        List<Category> categories = categoryService.fillAll();
        request.setAttribute("listC",categories);
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        request.setAttribute("o",product);
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showManagerProductjsp(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("ManagerProduct.jsp");
        List<Product> products = productService.fillAll();
        List<Category> categories = categoryService.fillAll();
        request.setAttribute("products",products);
        request.setAttribute("categories",categories);
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        productService.delete(id);
        try {
            response.sendRedirect("/managerProduct");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
