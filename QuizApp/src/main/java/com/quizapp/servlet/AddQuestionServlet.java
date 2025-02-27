package com.quizapp.servlet;

import com.quizapp.dao.QuestionDAO;
import com.quizapp.model.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddQuestionServlet")
public class AddQuestionServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questionText = request.getParameter("question");
        String option1 = request.getParameter("option1");
        String option2 = request.getParameter("option2");
        String option3 = request.getParameter("option3");
        String option4 = request.getParameter("option4");
        String correctAnswer = request.getParameter("correctAnswer");  // Ensure this is retrieved properly

        if (correctAnswer == null || correctAnswer.isEmpty()) {
            correctAnswer = "Option1";  // Default value (optional)
        }

        Question question = new Question();
        question.setQuestion(questionText);
        question.setOption1(option1);
        question.setOption2(option2);
        question.setOption3(option3);
        question.setOption4(option4);
        question.setCorrectAnswer(correctAnswer);  // Ensure correct answer is set

        QuestionDAO questionDAO = new QuestionDAO();
        boolean success = questionDAO.addQuestion(question);

        if (success) {
            response.sendRedirect("questions.jsp?success=true");
        } else {
            response.sendRedirect("addQuestion.jsp?error=true");
        }
    }
}
