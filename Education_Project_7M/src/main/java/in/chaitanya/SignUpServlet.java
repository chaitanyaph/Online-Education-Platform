package in.chaitanya;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String email = request.getParameter("email");
		String contact = request.getParameter("mob");
		String password = request.getParameter("passwod");
		
		Connection conn = null;
        PreparedStatement pstmt = null;
        PrintWriter writer = null;
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ educationregisteration", "root", "Satara@123");

            String sql = "INSERT INTO UserRegisteration (first, last, email, phone, password) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setString(4, contact);
            pstmt.setString(5, password);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
               
                response.sendRedirect("signin.html");
            } else {
                // Insert failed
                writer.println("Registration failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

	}

}
