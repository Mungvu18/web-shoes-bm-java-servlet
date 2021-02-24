package controller.login_logout;

import model.Account;
import service.classModle.LoginService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
            Account account = loginService.getAccount(username, password);
            if (account == null) {
                request.setAttribute("mess", "Wrong password or username");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            } else {
                if(account.getRole()== 3) {
                    response.sendRedirect("/home_logout");
                }else if(account.getRole()==2){
                    response.sendRedirect("/home_manager");
                } else if(account.getRole()==1){
                    response.sendRedirect("/home_admin");
                }
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
