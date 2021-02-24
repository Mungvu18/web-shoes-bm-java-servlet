package controller;

import model.Account;
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
