<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.quizapp.dao.QuestionDAO, com.quizapp.model.Question, java.util.List" %>
<%
    QuestionDAO questionDAO = new QuestionDAO();
    List<Question> questions = questionDAO.getAllQuestions();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Panel - Quiz App</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            background: linear-gradient(to right, white, red);
            height: 100vh;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }
        .container {
            width: 60%;
            background: rgba(255, 255, 255, 0.8);
            padding: 20px;
            border-radius: 10px;
            box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.3);
        }
        form {
            margin-bottom: 20px;
        }
        input, button {
            width: 100%;
            padding: 8px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            background-color: red;
            color: white;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background-color: darkred;
        }
        h2, h3 {
            color: darkred;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Admin Panel</h2>

        <!-- FORM TO ADD QUESTIONS -->
        <form action="AddQuestionServlet" method="post">
            <label>Question:</label>
            <input type="text" name="question" required>

            <label>Option 1:</label>
            <input type="text" name="option1" required>

            <label>Option 2:</label>
            <input type="text" name="option2" required>

            <label>Option 3:</label>
            <input type="text" name="option3" required>

            <label>Option 4:</label>
            <input type="text" name="option4" required>

            <label>Correct Answer:</label>
            <input type="text" name="correctAnswer" required>

            <button type="submit">Add Question</button>
        </form>

        <h3>All Questions</h3>
        <%
            if (questions != null && !questions.isEmpty()) {
                for (Question q : questions) {
        %>
                    <p><%= q.getQuestion() %></p>
        <%
                }
            } else {
        %>
            <p>No questions added yet.</p>
        <%
            }
        %>
    </div>
</body>
</html>
