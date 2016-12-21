package Sevlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class PageServlet
 */
public class PageServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String pageStr = req.getParameter("page");
		int page = 1;
		System.out.println(1111111111);
		if (pageStr != null) {
			page = Integer.parseInt(pageStr);
		}
		if (page < 1) {
			page = 1;

		}
		int pageSize = 10;
		String pageSizeStr = req.getParameter("pageSize");
		if (pageSizeStr != null) {
			pageSize = Integer.parseInt(pageSizeStr);

		}
		ImplDao.EmpDAOImpl dao = new ImplDao.EmpDAOImpl();
		int num = dao.count();
		int total = num % pageSize == 0 ? (num / pageSize) : (num / pageSize) + 1;

		List<Entity.Emp> list = dao.page(page, pageSize);
		req.setAttribute("list", list);
		req.setAttribute("npage", page);
		req.setAttribute("total", total);
		System.out.println(22222);
		req.getRequestDispatcher("index.jsp").forward(req, res);
	}

}
