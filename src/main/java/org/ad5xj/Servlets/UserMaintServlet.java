package org.ad5xj.Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.ad5xj.model.User;
import org.ad5xj.dao.UserImplDAO;

@WebServlet("/userMaintServlet")
public class UserMaintServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserMaintServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	{
		String action = request.getServletPath();
		try 
		{
		    switch (action) 
		    {
		    case "new":
		        showNewForm(request, response);
		        break;
		    case "insert":
		        insertUser(request, response);
		        break;
		    case "delete":
		        deleteUser(request, response);
		        break;
		    case "edit":
		        showEditForm(request, response);
		        break;
		    case "update":
		        updateUser(request, response);
		        break;
		    default:
		        listUser(request, response);
		        break;
		    }
		}
		catch ( Exception e )
		{
			
		}
	}
	protected void showNewForm(HttpServletRequest request, HttpServletResponse response)
    {
    	int id = Integer.valueOf(request.getParameter("id"));
    	System.out.println("UserMaintServlet.showNewForm 88: for id="+Integer.toString(id));
        RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
        try
        {
            dispatcher.forward(request, response);
        }
        catch (ServletException | IOException e )
        {
        	
        }    }

    protected void insertUser(HttpServletRequest request, HttpServletResponse response)
    {
    	
    }

    protected void deleteUser(HttpServletRequest request, HttpServletResponse response)
    {
    	
    }

    protected void showEditForm(HttpServletRequest request, HttpServletResponse response)
    {
    	int id = Integer.valueOf(request.getParameter("id"));
    	System.out.println("UserMaintServlet.showEditForm 88: for id="+Integer.toString(id));
    	UserImplDAO userobj = new UserImplDAO();
        User user = userobj.retrieveByID(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-edit.jsp");
        request.setAttribute("user", user);
        try
        {
            dispatcher.forward(request, response);
        }
        catch (ServletException | IOException e )
        {
        	
        }
    }

    protected void updateUser(HttpServletRequest request, HttpServletResponse response)
    {
    	
    }

    protected void listUser(HttpServletRequest request, HttpServletResponse response)
    {
    	
    }
}
