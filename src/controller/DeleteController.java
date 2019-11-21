package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberDAO;

public class DeleteController implements Controller {
	@Override
	public String service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// get id parameter by link
		int id = Integer.parseInt(req.getParameter("id"));

		System.out.println(id);
		int res = MemberDAO.instance.delete(id);
		System.out.println(res);
		if (res == 1) {
			req.getSession().setAttribute("msg", "글 삭제 완료");
			return "redirect::/board";
		} else {
			req.getSession().setAttribute("msg", "글 삭제중 오류");
			return "board/view?id=" + id;
		}

	}
}
