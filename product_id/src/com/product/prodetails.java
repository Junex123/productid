package com.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class prodetails
 */
@WebServlet("/prodetails")
public class prodetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public prodetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String SearchProductId = request.getParameter("productid");
		 try {
			 //1.Register connection
			 
			 Class.forName("com.sql.cj.jdbc.Driver");
			 
			 //2.get connection
			 
			 Connection connection = DriverManager.getConnection(
					 "jdbc:mysql://127.0.0.1:3306/employee",
					 "root",
					 "9987626498root"
					 );
			 
			 //3.create statement
			 
			 PreparedStatement preparedstatement = connection.prepareCall("select * from product_details where id = (?)");
			 preparedstatement.setString(1, SearchProductId);
			 
			 response.setContentType("text/html");
			 request.getRequestDispatcher("product.html").include(request, response);

			 ResultSet resultset= preparedstatement.executeQuery();
			 if(resultset.next()) {
				 out.print("<br/>"+"product id"+ SearchProductId + "found" + "<br/>");
				 out.print("<br/>" +"id" + resultset.getString("id"));
				 out.print("<br/>" +"name" + resultset.getString("name"));
				 out.print("<br/>" +"aboutit" + resultset.getString("aboutit"));
				 out.print("<br/>" +"price" + resultset.getString("price"));
				 out.print("<br/>" +"country" + resultset.getString("country"));
			 }else {
				 out.print("<br/>"+"product id"+ SearchProductId + "not found" + "<br/>");
			 }
			 
			 
			 out.close();
			 connection.close();
		 }
		catch(Exception e) {
			System.out.println("Database Error");
			System.out.println(e);
		}
	}

}
















