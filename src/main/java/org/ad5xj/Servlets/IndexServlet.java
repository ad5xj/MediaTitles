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

import org.ad5xj.Model.TitleStr;
import org.ad5xj.DAO.TitleImplDAO;

@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public IndexServlet() {  super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		processRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("DEBUG IndexServlet.processRequest 41 - Evaluating Request...");
		if ( request.getParameter("titlesList") != null )
		{
			System.out.println("DEBUG IndexServlet.processRequest 42 - User List Selected...");
			try
			{
			    showTitlesList(request, response);
			}
		    catch ( IOException | ServletException e)
		    {
		 	    System.out.println("ERROR IndexServlet:showTitlesList() 47 - Requesting Titles List..."+e.getMessage());
		    }
		}
	    else if (request.getParameter("usr-login") != null)
		{
			System.out.println("DEBUG IndexServlet.doGet 25- Login Selected...");
		    showLogin(request, response);
		}
		else if (request.getParameter("usr-register") != null)
		{
			System.out.println("DEBUG IndexServlet.doGet 38- Registration Selected...");
		    showRegister(request, response);
	    }
		else if (request.getParameter("btnExit") != null)
		{
			System.out.println("DEBUG IndexServlet.goGet() 46: close the application...");
			System.exit(0);
		}
    }


	protected void showTitlesList(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
	{
		RequestDispatcher rd = null;
		System.out.println("INFO IndexServlet:showTtilesList() 75- Loading Titles List...");
		TitleStr title = new TitleStr();
		TitleImplDAO titleobj = new TitleImplDAO();
		List<TitleStr> listTitles = null;

		listTitles = titleobj.listAll();
		System.out.println("DEBUG IndexServlet:showTitlesList() 82 - Found "+Integer.toString(listTitles.size()) + " titles to display...");
        try
        {
    		response.setContentType("text/html; charset=UTF-8");
    		System.out.println("DEBUG IndexServlet:showTitlesList() 82 forwarding to list-titles.jsp ..");
            request.setCharacterEncoding("UTF-8");
            request.setAttribute ("title",title);
            request.setAttribute("listTitles", listTitles);
            rd = request.getRequestDispatcher("list-titles.jsp");
            rd.forward(request, response);
            // find the id in the tags of list-titles
            // forward the HTML for the table
        }
        catch ( IOException e )
        {
    		System.out.println("ERROR IndexServlet:showTitlesList() 86 error forwarding to list-titles.jsp .."+e.getMessage());
    		e.printStackTrace();
    		request.setAttribute("exception", e);
            rd = request.getRequestDispatcher("ERROR.jsp");
            rd.forward(request,response);
        }
    }

	protected void showLogin(HttpServletRequest request, HttpServletResponse response)
	{
		response.setContentType("text/html; charset=utf-8");
		try ( PrintWriter pw = response.getWriter() )
		{
            pw.println("<!DOCTYPE html>'n");
            pw.println("<html>\n");
            pw.println("  <head>\n");
            pw.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
            pw.println("    <style>\n");
            pw.println("      body {font-family: Arial, Helvetica, sans-serif;}\n");
            pw.println("      form {border: 3px solid #f1f1f1;}\n");
            pw.println("      input[type=text], input[type=password]\n"); 
            pw.println("      {\n");
            pw.println("        width: 100%;\n");
            pw.println("        padding: 12px 20px;\n");
            pw.println("        margin: 8px 0;\n");
            pw.println("        display: inline-block;\n");
            pw.println("        border: 1px solid #ccc;\n");
            pw.println("        box-sizing: border-box;\n");
            pw.println("      }\n");
            pw.println("\n");
            pw.println("      button\n");
            pw.println("      {\n");
            pw.println("        background-color: #04AA6D;\n");
            pw.println("        color: white;\n");
            pw.println("        padding: 14px 20px;\n");
            pw.println("        margin: 8px 0;\n");
            pw.println("        border: none;\n");
            pw.println("        cursor: pointer;\n");
            pw.println("        width: 100%;\n");
            pw.println("     }\n");
            pw.println("     button:hover { opacity: 0.8; }\n");
            pw.println("\n");
            pw.println("     img.avatar { width: 40%; border-radius: 50%; }\n");
            pw.println("\n");
            pw.println("     span.psw   { float: right; padding-top: 16px; }\n");
            pw.println("\n");
            pw.println("     #main-container { width: 350%; text-align: center; margin-left: 20%; max-width: 940px; min-width: 320px; }\n");
            pw.println("\n");
            pw.println("     .cancelbtn { width: auto;  padding: 10px 18px; background-color: #f44336; }\n");
            pw.println("\n");
            pw.println("     .imgcontainer {  text-align: center;  margin: 24px 0 12px 0; }\n");
            pw.println("\n\n");
            pw.println("     .container { padding: 16px;	}\n");
            pw.println("\n\n");
            pw.println("      /* Change styles for span and cancel button on extra small screens */");
            pw.println("      @media screen and (max-width: 300px)\n");
            pw.println("      {\n");
            pw.println("        span.psw { display: block; float: none; }\n");
            pw.println("        .cancelbtn { width: 100%; }\n");
            pw.println("      }\n");
            pw.println("   </style>\n");
            pw.println("  </head>\n");
            pw.println(" <body>\n");
            pw.println("   <div id=\"main-container\" >\n");
            pw.println("     <h2>Login Form</h2>\n");
            pw.println("     <form action=\"UserLoginServlet\" method=\"GET\" style=\"border-width: 1px; border-color: #aaa; border-radius: 7px;\">\n");
            pw.println("       <div class=\"imgcontainer\"><img src=\"Images/people.png\" alt=\"Avatar\" class=\"avatar\"></div>\n");
            pw.println("       <div class=\"container\">\n");
            pw.println("         <label for=\"login\"><b>Login ID</b></label>\n");
            pw.println("         <input type=\"text\" placeholder=\"Enter Logon ID\" name=\"login\" required>\n");
            pw.println("         <label for=\"psw\"><b>Password</b></label>\n");
            pw.println("         <input type=\"password\" placeholder=\"Enter Password\" name=\"passwd\" required>\n");
            pw.println("         <button type=\"submit\">Login</button>\n");
            pw.println("       </div>\n");
            pw.println("       <div class=\"container\" style=\"background-color:#f1f1f1\">\n");
            pw.println("         Register <a href=\"register.jsp\">Now?</a>&nbsp;&nbsp;&nbsp;Forgot <a href=\"#\">password?</a>\n");
            pw.println("       </div>\n");
            pw.println("     </form>\n");
            pw.println("   </div>\n");
            pw.println(" </body>\n");
            pw.println("</html>\n");
        }  // try
		catch ( IOException e )
		{
			System.out.println("ERROR IndexServlet.showLogin() 71 - "+ e.getMessage());
			try ( PrintWriter pw = response.getWriter() )
			{
	            pw.println("<!DOCTYPE html>'n");
	            pw.println("<html>\n");
	            pw.println("  <head>\n");
	            pw.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
                pw.println("  </head>\n");
                pw.println(" <body>\n");
                pw.println("   <div id=\"main-container\" >\n");
	            pw.println("     <div class=\"tab\">Error displaying Login form</div>");
                pw.println("   </div>\n");
                pw.println(" </body>\n");
                pw.println("</html>\n");
			}
			catch ( IOException ex )
			{
				System.out.println("ERROR IndexServlet.showLogin() 152 - Login not found "+ ex.getMessage());
			}
		}
	}

	protected void showRegister(HttpServletRequest request, HttpServletResponse response)
	{
        System.out.println("INFO IndexServlet:showRegister() 63- Loading Registration Form...");
	     RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
	     try
	     {
           dispatcher.forward(request, response);
	     }
	     catch ( IOException | ServletException e)
	     {
	 		System.out.println("ERROR IndexServlet:showLogin() 67 - Requesting Registration Form...");
	     }
	}
}
