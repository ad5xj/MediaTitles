package org.ad5xj.Servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.sql.SQLException;

import java.util.List;

import org.ad5xj.model.User;
import org.ad5xj.dao.UserImplDAO;

/**
 * @brief ControllerServlet.java
 * @details 
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * 
 * @author AD5XJ Ken
 */

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet 
{
    private static final long serialVersionUID = 1L;

    private UserImplDAO userobj;

    public void init() { userobj = new UserImplDAO(); }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String action = request.getServletPath();

        try {
            switch (action) 
            {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertUser(request, response);
                break;
            case "/delete":
                deleteUser(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateUser(request, response);
                break;
            default:
                listUser(request, response);
                break;
            }
        } 
        catch (SQLException ex) 
        {
            throw new ServletException(ex);
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException 
    {
        List < User > listUsers = userobj.retrieveAll();
        request.setAttribute("listUser", listUsers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException 
    {
        int id = Integer.parseInt(request.getParameter("userid"));
        User user = userobj.retrieveByID(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("user", user);
        dispatcher.forward(request, response);

    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException 
    {
        int    userID      = userobj.getLastID();
        int    userPrivLvl = Integer.valueOf(request.getParameter("userPrivLvl"));
        String userLogin   = request.getParameter("userLogin");
        String userName    = request.getParameter("userName");
        String userPasswd  = request.getParameter("userPassword");
        User user = new User(userID, userPrivLvl, userLogin, userName, userPasswd );
        userobj.add(user);
        response.sendRedirect("list");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException 
    {
        int    userid = Integer.parseInt(request.getParameter("userid"));
        int userPrivLvl    = Integer.valueOf(request.getParameter("userPrivLvl"));
        String userLogin   = request.getParameter("userLogin");
        String userName    = request.getParameter("userName");
        String userPasswd  = request.getParameter("userPasswd");

        User usr = new User(userid, userPrivLvl, userLogin, userName, userPasswd);
        userobj.update(usr);
        response.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userobj.destroy(id);
        response.sendRedirect("list");
    }
}