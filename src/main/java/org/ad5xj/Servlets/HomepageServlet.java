package org.ad5xj.Servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.ad5xj.model.User;
import org.ad5xj.model.Author;
import org.ad5xj.model.MediaType;
import org.ad5xj.model.Title;
import org.ad5xj.model.TitleStr;

import org.ad5xj.dao.UserImplDAO;
import org.ad5xj.dao.AuthorImplDAO;
import org.ad5xj.dao.MediaImplDAO;
import org.ad5xj.dao.TitleImplDAO;


@WebServlet("/HomepageServlet")
public class HomepageServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	RequestDispatcher rd;

	AuthorImplDAO authobj = null;
	TitleImplDAO  titleobj = null;
	List<Author>  authlist = new ArrayList<>();
	List<Title>   listTitles = new ArrayList<>();

    public HomepageServlet() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		processRequest(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if ( request.getParameterValues("titlesList") != null )
		{
			System.out.println("DEBUG HomepageServlet.processRequest 38 - User List Selected...");
			this.showTitlesList(request, response);
		}
	    else if (request.getParameterValues("usrlist") != null)
		{
			System.out.println("DEBUG HomepageServlet.processRequest 38 - User List Selected...");
			this.showUserList(request, response);
		}
		else if (request.getParameter("usrmaint") != null)
		{
			System.out.println("DEBUG HomepageServlet.doGet 43 - User Maint Selected...");
		    this.showUserList(request, response);
		}
		else if (request.getParameter("btn-authmaint") != null)
		{
			System.out.println("DEBUG HomepageServlet.doGet 48- Authors Maint Selected...");
		    showAuthList(request, response);
	    }
		else if (request.getParameter("btn-mediamaint") != null)
		{
			System.out.println("DEBUG HomepageServlet.doGet 53- MediaType Maint Selected...");
		    showMediaList(request, response);
	    }
		else if (request.getParameter("btnExit") != null)
		{
			System.out.println("DEBUG HomepageServlet.goGet() 59: close the application...");
			System.exit(0);
		}
    }

	private void showTitlesList(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
	{
		RequestDispatcher rd;
		TitleImplDAO titleobj = new TitleImplDAO();

		List < TitleStr > listTitles = titleobj.listAll();
		System.out.println("INFO HomepageServlet:showTtilesList() 86- Loading Titles List...");
        request.setAttribute("mytitle", listTitles);
        request.setAttribute("listTitles", listTitles);
		response.setContentType("text/html; charset=UTF-8");
        try
        {
	        rd = request.getRequestDispatcher("list-titles.jsp");
            rd.forward(request, response);
        }
        catch ( ServletException | IOException e )
        {
    		System.out.println("ERROR HomepageServlet.showTitlesList() 96: error forwarding list to list-titles.jsp");
            request.setAttribute("exception", e);
		    rd = request.getRequestDispatcher("ERROR.jsp");
    		rd.forward(request, response);
        }
    }
	
	private void showUserList(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
	{
		RequestDispatcher rd;
		UserImplDAO userobj = new UserImplDAO();
		User user = new User();

		List < User > listUser = userobj.retrieveAll();
		System.out.println("DEBUG HomepageServlet.showUserList() 81: forwarding list of "+Integer.toString(listUser.size())+" to list-users.jsp");
		response.setContentType("text/html; charset=UTF-8");
        request.setAttribute("user", user);
        request.setAttribute("listUser", listUser);
        try
        {
		    rd = request.getRequestDispatcher("list-users.jsp");
		    rd.forward(request, response);
        }
        catch ( ServletException | IOException e )
        {
    		System.out.println("ERROR HomepageServlet.showUserList() 121: error forwarding list to list-users.jsp - "+e.getMessage());
        }
	}

	private void showAuthList(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
	{
		AuthorImplDAO authobj = new AuthorImplDAO();
		List < Author > listAuthors = authobj.retrieveAll();
		System.out.println("INFO HomepageServlet:showAuthList() 66- Loading Authors List...");
        request.setAttribute("listAuth", listAuthors);
	     RequestDispatcher dispatcher = request.getRequestDispatcher("list-authors.jsp");
         dispatcher.forward(request, response);
    }
	private void showMediaList(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
	{
		MediaImplDAO mediaobj = new MediaImplDAO();
		List < MediaType > listMedia = mediaobj.retrieveAll();
		System.out.println("INFO HomepageServlet:showMediaList() 72- Loading MediaType List...");
        request.setAttribute("listMedia", listMedia);
	     RequestDispatcher dispatcher = request.getRequestDispatcher("list-media.jsp");
         dispatcher.forward(request, response);
    }

}
