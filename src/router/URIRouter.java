package router;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.BoardController;
import controller.Controller;
import controller.DeleteController;
import controller.LoginController;
import controller.LogoutController;
import controller.ModifyController;
import controller.RegisterController;
import controller.ViewController;
import controller.WriteController;

@MultipartConfig
public class URIRouter extends HttpServlet {
	
	private Map<String, Controller> urlMap = new HashMap<>();
	
	@Override
	public void init() throws ServletException {
		urlMap.put("/", new LoginController());
		urlMap.put("/register", new RegisterController());
		urlMap.put("/board", new BoardController());
		urlMap.put("/logout", new LogoutController());
		urlMap.put("/board/write", new WriteController());
		urlMap.put("/board/view", new ViewController());
		urlMap.put("/board/delete", new DeleteController());
		urlMap.put("/board/modify", new ModifyController());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		
		String uri = req.getRequestURI(); // 사용자가 요청한  URI
		String context = req.getContextPath();
		
		uri = uri.substring(context.length());
		
		Controller c = urlMap.get(uri);
		
		String view = null;
		try {
			view = c.service(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			view = "notfound";
		}
		
		if(view.startsWith("redirect::")) {
			String target = view.substring("redirect::".length());
			resp.sendRedirect(target);
		}else {
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/" + view + ".jsp");
			rd.forward(req, resp);
		}
		
	}
	
	
}
