package controller;

import model.Category;
import model.Product;
import service.CategoryService;
import service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeLoginServlet", urlPatterns = "/home_login")
public class HomeLoginServlet extends HttpServlet {
    public  static ProductService productService = new ProductService();
    public static CategoryService categoryService = new CategoryService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action==null){
            action = "";
        }
        switch (action){
            case "":
                showAllProduct(request,response);
                break;
        }
    }

    private void showAllProduct(HttpServletRequest request, HttpServletResponse response) {
        // lấy ra danh sách
        List<Product> products = productService.fillAll();
        List<Category> categories = categoryService.fillAll();
        List<Product> productList = productService.findBestSale();
        // điều hướng
        RequestDispatcher dispatcher = request.getRequestDispatcher("Home_login.jsp");
        // đẩy danh sách sang trang jsp
        request.setAttribute("products",products);
        request.setAttribute("categories",categories);
        request.setAttribute("productList",productList);
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
