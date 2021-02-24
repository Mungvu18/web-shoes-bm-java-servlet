package controller;

import model.Account;
import model.Product;
import service.classModle.AccountService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdminServlet",urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet {
    AccountService accountService = new AccountService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action== null){
            action = "";
        }
        switch (action){
            case "delete":
                deleteAccount(request,response);
            case "edit":
                deleteAccount(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action== null){
            action = "";
        }
        switch (action){
            case "":
                showAdminAccount(request,response);
                break;
            case "delete":
                ShowDeleteAccount(request,response);
                break;
            case "edit":
                ShowDeleteAccount(request,response);
                break;
        }
    }

    private void ShowDeleteAccount(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("EditAccount.jsp");
        int id = Integer.parseInt(request.getParameter("id"));
        Account account = accountService.findById(id);
        request.setAttribute("o",account);
        try {
            requestDispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteAccount(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String status = request.getParameter("status");
        int role = Integer.parseInt(request.getParameter("role"));
        Account account= new Account(id,username,password,role,status);
        accountService.update(account,id);
        try {
            response.sendRedirect("/admin");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAdminAccount(HttpServletRequest request, HttpServletResponse response) {
        List<Account> accounts = new ArrayList<>();
        accounts = accountService.fillAll();
        request.setAttribute("accounts",accounts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
