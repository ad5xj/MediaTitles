package org.ad5xj.Servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

import org.ad5xj.model.User;
import org.ad5xj.dao.UserImplDAO;

@WebServlet("/userListServlet")
public class UserListServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	PrintWriter pw;
	String DML = "";
	User user = new User();
	UserImplDAO userobj = new UserImplDAO();
	List<User> list;
	RequestDispatcher rd;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		processRequest(req, res);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("UserListServlet.processRequest 47: what to do? ? ? ");
	}
}
