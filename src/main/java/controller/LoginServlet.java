package controller;

import model.Account;
import service.LoginService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    LoginService loginService = new LoginService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "":
                login(request, response);
                break;
//            case "login":
//                login(request, response);
//                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "":
                showLogin(request, response);
                break;
        }
    }

        private void login (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String username = request.getParameter("user");
            String password = request.getParameter("pass");
            List<Account> accounts = new ArrayList<>();
            Account account = loginService.getAccount(username, password);
            if (account == null) {
                request.setAttribute("mess", "Wrong password or username");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            } else {
                accounts.add(account);
                request.setAttribute("list",accounts);
                response.sendRedirect("/home_logout");
            }
        }

    private void showLogin(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Login.jsp");
        try {
            requestDispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
