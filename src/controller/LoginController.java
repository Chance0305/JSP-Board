package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDAO;
import vo.UserVO;

public class LoginController implements Controller {
	@Override
	public String service(HttpServletRequest req, HttpServletResponse resp) {

		if (req.getMethod().equalsIgnoreCase("POST")) {
			String id = req.getParameter("id");
			String password = req.getParameter("password");

			UserVO user = MemberDAO.instance.login(id, password);

			if (user != null) { // when login success
				System.out.println("로그인 성공");
				HttpSession session = req.getSession();
				session.setAttribute("user", user);
				req.getSession().setAttribute("msg", "로그인 성공");
				return "redirect::/board";
			} else { // when login failed
				System.out.println("로그인 실패");
				HttpSession session = req.getSession();
				req.getSession().setAttribute("msg", "로그인 실패");
				return "redirect::/";
			}
		}
		return "index";
	}
}
