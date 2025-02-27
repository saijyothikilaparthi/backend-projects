package com.quizapp.servlet;

import com.quizapp.dao.QuestionDAO;
import com.quizapp.model.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/fetchQuestions")
public class FetchQuestionsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Create instance of QuestionDAO
        QuestionDAO questionDAO = new QuestionDAO();

        // Fetch all questions
        List<Question> questions = questionDAO.getAllQuestions();

        // Debugging - Print questions count in server logs
        System.out.println("Fetched Questions: " + questions.size());

        // Set questions in request attribute
        request.setAttribute("questions", questions);

        // Forward request to questions.jsp
        request.getRequestDispatcher("questions.jsp").forward(request, response);
    }
}
