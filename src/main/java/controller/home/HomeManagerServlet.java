package controller.home;

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

@WebServlet(name = "HomeManagerServlet", urlPatterns = "/home_manager")
public class HomeManagerServlet extends HttpServlet {
    ProductService productService = new ProductService();
    CategoryService categoryService = new CategoryService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action==null){
            action = "";
        }
        switch (action){
            case "":
                showHoneManager(request,response);
        }
    }

    private void showHoneManager(HttpServletRequest request, HttpServletResponse response) {
        List<Product> products = productService.fillAll();
        List<Category> categories = categoryService.fillAll();
        List<Product> productList = productService.findBestSale();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Home_manager.jsp");
        request.setAttribute("products",products);
        request.setAttribute("categories",categories);
        request.setAttribute("productList",productList);
        try {
            requestDispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
