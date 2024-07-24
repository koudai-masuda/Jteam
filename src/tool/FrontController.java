package tool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String path = req.getServletPath().substring(1);
			String name = path.replace(".a", "A").replace('/', '.');
			Action action = (Action)Class.forName(name).
					getDeclaredConstructor().newInstance();
			String url = action.execute(req, resp);

			req.getRequestDispatcher(url).forward(req, resp);
		} catch (Exception e) {
			// 開発用エラー表示
			PrintWriter out = resp.getWriter();
			e.printStackTrace(out);

			// 本番用エラー表示
//			 e.printStackTrace();
//			 resp.sendRedirect("error.jsp");
		}
	}
}
