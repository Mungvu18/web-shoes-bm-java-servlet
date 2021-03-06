package controller.search;

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

@WebServlet(name = "SearchProductServlet", urlPatterns = "/search")
public class SearchProductServlet extends HttpServlet {
    ProductService productService = new ProductService();
    CategoryService categoryService = new CategoryService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action==null){
            action = "";
        }
        switch (action){
            case "":
                showListSearchProduct(request,response);
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void showListSearchProduct(HttpServletRequest request, HttpServletResponse response) {
        String txt = request.getParameter("txt");
        List<Product> products = productService.findByName(txt);
        List<Category> categories = categoryService.fillAll();
        List<Product> productList = productService.findBestSale();
        request.setAttribute("products",products);
        request.setAttribute("categories",categories);
        request.setAttribute("productList",productList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Home_login.jsp");
        try {
            requestDispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
