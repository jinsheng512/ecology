package ecustom.servlets;

import weaver.general.GCONST;
import weaver.hrm.User;
import weaver.hrm.UserManager;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class InitServer extends HttpServlet {

	private static final long serialVersionUID = 3015889876980682345L;
	public void init(ServletConfig config) {
		GCONST.setServerName("ecology");
		GCONST.setRootPath(config.getServletContext().getRealPath("/"));
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		int userId = Integer.parseInt(req.getParameter("id"));
		User user = new UserManager().getUserByUserIdAndLoginType(userId, "1");
		req.getSession().setAttribute("weaver_user@bean", user);
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("	<meta charset=\"UTF-8\">");
		out.println("</head>");
		out.println("<body>");
		out.println("Login Id: " + user.getLoginid());
		out.println("<br/>Last Name: " + user.getLastname());
		out.println("</body>");
		out.println("</html>");
	}
}