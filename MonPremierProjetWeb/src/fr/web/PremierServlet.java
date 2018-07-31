package fr.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "Mon premier servlet exemple", urlPatterns = { "/tata", "/tutu" })
public class PremierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Passage dans init");
	}

	@Override
	public void destroy() {
		System.out.println("Passage dans destroy");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("maclef", new Date());
		request.setAttribute("monsupermessage", "Hello");

		RequestDispatcher dispatcher = request.getRequestDispatcher("MaJsp.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String vCh1 = request.getParameter("ch1");
		String vCh2 = request.getParameter("ch2");

		if (vCh1 != null) {
			// traitement
		}
		PrintWriter out = response.getWriter();
		out.write("Bonjour tout le monde (je suis en POST)" + vCh1 + " " + vCh2);
	}

}
