package org.ad5xj.Servlets;

import java.io.IOException;

import java.sql.SQLException;

import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.ad5xj.dao.AuthorImplDAO;
import org.ad5xj.model.Author;

/**
 * @brief AuthorsServlet acts as a page controller for the application.
 * @details 
 * AuthorsServlet acts as a page controller for the application, handling all
 * requests from the user.
 * 
 */

@WebServlet("/AuthorsServlet")
public class AuthorsServlet extends HttpServlet 
{
    private static final long serialVersionUID = 1L;

	private AuthorImplDAO authobj = new AuthorImplDAO();

    public void init() { authobj = new AuthorImplDAO(); }

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
                listAuthor(request, response);
                break;
            }
        } 
        catch (SQLException ex) 
        {
            throw new ServletException(ex);
        }
    }

    private void listAuthor(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException 
    {
        List < Author > listAuth = authobj.retrieveAll();
        request.setAttribute("listAuthor", listAuth);
        RequestDispatcher dispatcher = request.getRequestDispatcher("author-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        RequestDispatcher dispatcher = request.getRequestDispatcher("author-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException 
    {
        int id = Integer.parseInt(request.getParameter("id"));
        Author existingAuthor = authobj.retrieveByID(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("author-form.jsp");
        request.setAttribute("author", existingAuthor);
        dispatcher.forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException 
    {
    	int    i_id = authobj.getLastID() + 1;
        String i_fname = request.getParameter("firstName");
        String i_lname = request.getParameter("lastName");
        String i_note = request.getParameter("authNote");
      
        Author newAuthor = new Author(i_id, i_fname, i_lname, i_note);
        authobj.add(newAuthor);
        response.sendRedirect("list");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException 
    {
        int    i_id = Integer.parseInt(request.getParameter("authid"));
        String i_fname = request.getParameter("firstName");
        String i_lname = request.getParameter("lastName");
        String i_note = request.getParameter("authNote");

        Author author = new Author(i_id, i_fname, i_lname, i_note);
        authobj.update(author);
        response.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException 
    {
        int i_id = Integer.parseInt(request.getParameter("authid"));
        authobj.destroy(i_id);
        response.sendRedirect("list");
    }
}