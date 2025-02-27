package com.quizapp.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.query.Query;
import com.quizapp.model.Question;
import com.quizapp.util.HibernateUtil;

@WebServlet("/SubmitQuizServlet")
public class SubmitQuizServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Question> query = session.createQuery("FROM Question", Question.class);
        List<Question> questions = query.list();
        session.close();

        int score = 0;

        for (Question q : questions) {
            String selectedAnswer = request.getParameter("q" + q.getId());
            if (selectedAnswer != null && selectedAnswer.equals(q.getCorrectAnswer())) {
                score++;
            }
        }

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("score", score);
        response.sendRedirect("result.jsp");
    }
}
