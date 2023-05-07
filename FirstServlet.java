
package wt;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/wt/FirstServlet")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public FirstServlet() {
        // TODO Auto-generated constructor stub
    	super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("init started");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		out.println("<html><body>");
		Connection connection=null;
		Statement statement=null;
		ResultSet resultset=null;
		try {
			String id = request.getParameter("userid");
			String driver = "com.mysql.jdbc.Driver";
			String connectionUrl = "jdbc:mysql://localhost:3306/";
			String database = "mydb";
			String userid = "user";
			String password = "password";
			Class.forName(driver);

			connection = DriverManager.getConnection(connectionUrl + database, userid, password);
			statement = connection.createStatement();
			String sql = "select * from stud_info";
			resultset = statement.executeQuery(sql);
			out.print("<table border=1> <tr><th>Id</th><th>Name</th><th>Author</th><th>Price</th><th>Quantity</th></tr>"); 
			while (resultset.next()) {
				String book_id = resultset.getString("book_id");
				
				String book_title = resultset.getString("book_title");
				String book_author = resultset.getString("book_author");
				String book_price = resultset.getString("book_price");
				String book_quantity = resultset.getString("book_quantity");
				
				out.println("<tr><td>" + book_id + "</td><td>" + book_title + "</td><td>" + book_author + "</td><td>" + book_price + "</td><td>" + book_quantity + "</td></tr>");
			}
			out.println("</table>");
			out.println("</html></body>");
			connection.close();

		} catch (Exception e) {    
			out.println("error");
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
