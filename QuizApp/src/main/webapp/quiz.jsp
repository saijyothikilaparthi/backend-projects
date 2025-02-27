<%@ page import="com.quizapp.dao.QuestionDAO, com.quizapp.model.Question, java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Quiz Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            background: linear-gradient(135deg, #ffffff, #ff0000);
            color: black;
            margin: 0;
            padding: 0;
        }
        .container {
            margin: 50px auto;
            width: 60%;
            background: rgba(255, 255, 255, 0.8);
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
        }
        .question {
            text-align: left;
            margin-bottom: 15px;
            padding: 10px;
            background: rgba(255, 255, 255, 0.6);
            border-radius: 5px;
        }
        .options {
            margin-top: 10px;
        }
        input[type="radio"] {
            margin-right: 5px;
        }
        input[type="submit"] {
            padding: 12px 24px;
            background-color: #ff0000;
            color: white;
            font-size: 18px;
            font-weight: bold;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 20px;
        }
        input[type="submit"]:hover {
            background-color: #cc0000;
        }
        h2 {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Quiz</h2>

        <form action="SubmitQuizServlet" method="post">
            <%
                QuestionDAO dao = new QuestionDAO();
                List<Question> questions = dao.getAllQuestions();

                if (questions == null || questions.isEmpty()) {
            %>
                    <h3 style="color: red;">No questions available. Please add questions first.</h3>
            <%
                } else {
                    int i = 1;
                    for (Question q : questions) {
            %>
                        <div class="question">
                            <p><strong><%= i++ %>. <%= q.getQuestion() %></strong></p>
                            <div class="options">
                                <input type="radio" name="q<%= q.getId() %>" value="<%= q.getOption1() %>"> <%= q.getOption1() %> <br>
                                <input type="radio" name="q<%= q.getId() %>" value="<%= q.getOption2() %>"> <%= q.getOption2() %> <br>
                                <input type="radio" name="q<%= q.getId() %>" value="<%= q.getOption3() %>"> <%= q.getOption3() %> <br>
                                <input type="radio" name="q<%= q.getId() %>" value="<%= q.getOption4() %>"> <%= q.getOption4() %> <br>
                            </div>
                        </div>
            <%
                    }
                }
            %>

            <br>
            <input type="submit" value="Submit Quiz">
        </form>
    </div>
</body>
</html>
