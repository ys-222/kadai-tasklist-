package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
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
	        
	        em.getTransaction().begin();
	        em.persist(t);
	        em.getTransaction().commit();
	        em.close();
	        
	        response.sendRedirect(request.getContextPath() + "/index");
	    }
	}

}