package controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import models.validators.TaskValidator;
import utils.DBUtil;

@WebServlet("/create")
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreateServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String _token = request.getParameter("_token");
	    
	    // CSRF対策のチェック _tokenに値セットがなければ || セッションIDと値が違ったらデータ登録しない
	    if(_token != null && _token.equals(request.getSession().getId())) {
	        EntityManager em = DBUtil.createEntityManager();
	        
	        Task t = new Task();
	        
	        String title = request.getParameter("title");
	        t.setTitle(title);
	        
	        String content = request.getParameter("content");
	        t.setContent(content);
	        
	        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
	        t.setCreated_at(currentTime);
	        t.setUpdated_at(currentTime);
	        
	     // バリデーションを実行してエラーがあったら編集画面のフォームに戻る
            List<String> errors = TaskValidator.validate(t);
            if(errors.size() > 0) {
                em.close();

                // フォームに初期値を設定、さらにエラーメッセージを送る
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("task", t);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/edit.jsp");
                rd.forward(request, response);
            } else {
                // データベースを更新
                em.getTransaction().begin();
                em.getTransaction().commit();
                request.getSession().setAttribute("flush", "更新が完了しました。");
                em.close();

                // セッションスコープ上の不要になったデータを削除
                request.getSession().removeAttribute("task_id");

                // indexページへリダイレクト
                response.sendRedirect(request.getContextPath() + "/index");
            }
	    }
	}
}
	    