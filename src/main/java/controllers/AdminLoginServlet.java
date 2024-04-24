
package controllers;

import dao.AdminDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import models.Admin;
import connection.ConnectionDB;
import java.io.IOException;
import java.io.PrintWriter;



public class AdminLoginServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            AdminDao ado = new AdminDao(ConnectionDB.getCon());
            Admin admin = ado.logAdmin(email, password);
            if(admin!=null){
                HttpSession session = request.getSession();
                session.setAttribute("loggedAdmin", admin);
                response.sendRedirect("adminpanel.jsp");
            }else{
                out.println("unknown credential");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}