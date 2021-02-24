package controller.login_logout;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LogoutServlet",urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action==null){
            action ="";
        }
        switch (action){
            case "":
            logout(request,response);
            break;
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect("Home_logout.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action==null){
            action ="";
        }
        switch (action){
            case "":
                logout(request,response);
                break;
        }
    }
}
