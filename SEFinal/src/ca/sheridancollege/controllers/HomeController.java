package ca.sheridancollege.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.persistence.Query;

import ca.sheridancollege.beans.Account;
import ca.sheridancollege.beans.User;
import ca.sheridancollege.dao.DAO;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/HomeController")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 102831973239L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
    }
	private DAO dao = new DAO();
	private User u = new User();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email");
		String loginPassword = request.getParameter("password");
		
		User user = new User(email, loginPassword);
		
		List<User> verifyLogin = dao.queryUser(email, loginPassword);
		boolean spriteCranberry = u.login(verifyLogin);
		if(spriteCranberry = true) {
			response.sendRedirect("Index.html");
		}
		else {
			request.getRequestDispatcher("form.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			


		
		doGet(request, response);
	}

}
