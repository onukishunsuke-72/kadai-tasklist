package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

/**
 * Servlet implementation class EditSetvlet
 */
@WebServlet("/edit")
public class EditSetvlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditSetvlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    EntityManager em = DBUtil.createEntityManager();

    Task t = em.find(Task.class, Integer.parseInt(request.getParameter("id")));

    em.close();

    //タスク情報とセッションIDをリクエストスコープに保存
    request.setAttribute("task", t);
    request.setAttribute("_token",request.getSession().getId());

    //タスクIDをセッションスコープに登録
    request.getSession().setAttribute("task_id", t.getId());

    RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/tasks/edit.jsp");
    rd.forward(request, response);


    }

}
