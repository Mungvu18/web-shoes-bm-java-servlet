package controller.login_logout;

import model.Account;
import service.classModle.SignUpService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

@WebServlet(name = "SignupServlet",urlPatterns = "/signup")
public class SignupServlet extends HttpServlet {
    SignUpService signUpService = new SignUpService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "":
                signup(request,response);
        }

    }

    private void signup(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String username = request.getParameter("user");
        String password = request.getParameter("pass");
        String rePassword = request.getParameter("repass");
        try {
            Account account = signUpService.getAccount(username);
            if(account == null){
                if(password.equals(rePassword)) {
                    signUpService.insertAccount(username, password);
                    response.sendRedirect("/home");
                } else {
                    response.sendRedirect("Login.jsp");
                }
            }else {
                response.sendRedirect("Login.jsp");
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
