package org.ad5xj.Servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/mainServlet")
public class MainServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	RequestDispatcher rd;
	
	String DML = "";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {  super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if (request.getParameter("ListTitles") != null)
		{
		    // do user maint
			System.out.println("INFO HomepageServlet.doGet 29- Titles  List and search Selected...");
		    showTitlesList(request, response);
		}
		else if (request.getParameter("PrintTitles") != null)
		{
			System.out.println("INFO MainServlet.doGet 36- print all Titles ...");
		    printTitlesList(request, response);
	    }
		else if (request.getParameter("PrintAuthors") != null)
		{
			System.out.println("DEBUG MainServlet.doGet 41- Print all Authors and titles..");
		    printListByAuthor(request, response);
	    }
		else if (request.getParameter("PrintMedia") != null)
		{
			System.out.println("DEBUG MainServlet.goGet() 47: print by media type...");
		    printListByMT(request, response);
		}
	}

	private void showTitlesList(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
	{
		System.out.println("INFO MainServlet:showTitlesList() 50- Loading Titles List...");
	     RequestDispatcher dispatcher = request.getRequestDispatcher("list-titles.jsp");
         dispatcher.forward(request, response);
    }

	private void printTitlesList(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
	{
		System.out.println("INFO MainServlet:printTitlesList() 57- Printing Titles List...");
	     RequestDispatcher dispatcher = request.getRequestDispatcher("print-titles.jsp");
         dispatcher.forward(request, response);
    }

	private void printListByAuthor(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
	{
		System.out.println("INFO MainServlet:printTitlesList() 64- Printing Titles By Author, Title and MediaType...");
	     RequestDispatcher dispatcher = request.getRequestDispatcher("print-titles.jsp");
         dispatcher.forward(request, response);
    }

	private void printListByMT(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
	{
		System.out.println("INFO MainServlet:printTitlesList() 71- Printing Titles By MediaType, TItle and Author...");
	     RequestDispatcher dispatcher = request.getRequestDispatcher("print-mt.jsp");
         dispatcher.forward(request, response);
    }
		
}
