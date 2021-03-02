package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

// データベースから複数のタスク情報を取得して一覧表示するサーブレット
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IndexServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    EntityManager em = DBUtil.createEntityManager();
	    
	    // 開くページ数を取得
	    int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch(NumberFormatException e) {}

        // 最大件数と開始位置を指定してメッセージを取得
        List<Task> tasks = em.createNamedQuery("getAllTasks", Task.class)
                                   .setFirstResult(5 * (page - 1))
                                   .setMaxResults(5)
                                   .getResultList();

        // 全件数を取得
        long tasks_count = (long)em.createNamedQuery("getTasksCount", Long.class)
                                      .getSingleResult();

        em.close();

        request.setAttribute("tasks", tasks);
        request.setAttribute("tasks_count", tasks_count);   
        request.setAttribute("page", page);     
	    
	    
	    // フラッシュメッセージがセッションスコープにセットされていたら
	    if(request.getSession().getAttribute("flush") != null) {
	        // セッションスコープ内のフラッシュメッセージをリクエストスコープに保存し、セッションスコープからは削除する
	        request.setAttribute("flush", request.getSession().getAttribute("flush"));
	        request.getSession().removeAttribute("flush");
	    }
	    
	    // ビューとなるindex.jspを指定
	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/index.jsp");
	    // ビューとなるindex.jspを呼び出す
	    rd.forward(request, response);
	}
}
