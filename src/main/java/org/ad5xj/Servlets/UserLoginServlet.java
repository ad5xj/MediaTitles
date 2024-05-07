package org.ad5xj.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.ad5xj.Model.User;
import org.ad5xj.DAO.UserImplDAO;

@WebServlet("/userLoginServlet")
public class UserLoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	PrintWriter pw;
	String DML = "";
	ResultSet rs;
	RequestDispatcher rd;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		processRequest(req,res);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		UserImplDAO userobj = new UserImplDAO();
		User user = new User();
		
		String uName = request.getParameter("login");
		String pWord = request.getParameter("passwd");
		System.out.println("DEBUG UserLoginServlet.processRequest 41: validating usr:"+uName+"\tpwd:"+pWord+"\n");
		boolean validated = userobj.validate(uName, pWord); 
		if ( validated )
		{
			System.out.println("DEBUG UserLoginServlet.processRequest 45: validated usr:"+uName+"\tpwd:"+pWord+"\n");
		    user = userobj.retrieveByLoginPW(uName, pWord);
			if ( user.getID() > 0 ) 
			{
				System.out.println("DEBUG UserLoginServlet.processRequest 44:usr found...loading success form\n");
				response.setContentType("text/html; charset=utf-8");
				try ( PrintWriter pw = response.getWriter() )
				{
                    pw.println("<!DOCTYPE html>\n");
                    pw.println("<html>\n");
                    pw.println(" <head>\n");
                    pw.println("   <title>Login Success!</title>\n");
                    pw.println(" </head>\n");
                    pw.println(" <body>\n");
                    pw.println("  <div align=\"center\">\n");
                    pw.println("   <br><br><br>\n");
                    pw.println("   <h2>Success! You have logged in. Click continue to proceed.</h2>\n");
                    pw.println("   <br><br><br>\n");
                    pw.println("   <form action=\"homepage.jsp\" method=\"GET\"><button type=\"submit\">CONTINUE</button></form>\n");
                    pw.println("   </div>\n");
                    pw.println(" </body>\n");
                    pw.println("</html>");
				}
			}
		}
		else
		{ // login did not validate
			System.out.println("DEBUG UserLoginServlet.processRequest 41: usr:"+uName+"\tpwd:"+pWord+" NOT VALID \n");
			response.setContentType("text/html; charset=utf-8");
			try ( PrintWriter pw = response.getWriter() )
			{
                pw.println("<!DOCTYPE html>\n");
                pw.println("<html>\n");
                pw.println(" <head>\n");
                pw.println("   <title>Login FAILURE!</title>\n");
                pw.println(" </head>\n");
                pw.println(" <body>\n");
                pw.println("  <div align=\"center\">\n");
                pw.println("   <br><br><br>\n");
                pw.println("   <h2>Sorry. The information you provided is not valid. Click continue to proceed\n");
                pw.println("   and register or exit. Click continue now\n</h2>\n");
                pw.println("   <br><br><br>\n");
                pw.println("   <form action=\"index.jsp\" method=\"GET\"><button style='width: 120px; height: 30px;' type=\"submit\">CONTINUE</button></form>\n");
                pw.println("   </div>\n");
                pw.println(" </body>\n");
                pw.println("</html>");
			}
			
			rd.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		//
	}
}