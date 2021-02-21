package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
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
	    
	    // TaskクラスのJPQL文につけた"getAllTasks"をcreateNamedQueryメソッドの引数に指定
	    // 問い合わせ結果をgetResultList()メソッドを使用しリスト形式で取得
	    List<Task> tasks = em.createNamedQuery("getAllTasks", Task.class).getResultList();
	    
	    // データ登録の件数
		response.getWriter().append(Integer.valueOf(tasks.size()).toString());
		
		em.close();
	}
}
