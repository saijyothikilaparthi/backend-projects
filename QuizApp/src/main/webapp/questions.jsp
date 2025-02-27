<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.quizapp.model.Question" %>
<%@ page import="com.quizapp.dao.QuestionDAO" %>

<!DOCTYPE html>
<html>
<head>
    <title>View Questions</title>
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
            width: 80%;
            background: rgba(255, 255, 255, 0.9);
            padding: 20px;
            border-radius: 10px;
            box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.3);
        }
        h2 {
            color: darkred;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid black;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: darkred;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f8d7da;
        }
    </style>
</head>
<body>

    <div class="container">
        <h2>Question List</h2>

        <table>
            <tr>
                <th>ID</th>
                <th>Question</th>
                <th>Option 1</th>
                <th>Option 2</th>
                <th>Option 3</th>
                <th>Option 4</th>
                <th>Correct Answer</th>
            </tr>

            <%
                QuestionDAO questionDAO = new QuestionDAO();
                List<Question> questions = questionDAO.getAllQuestions();

                for (Question q : questions) {
            %>
                    <tr>
                        <td><%= q.getId() %></td>
                        <td><%= q.getQuestion() %></td>
                        <td><%= q.getOption1() %></td>
                        <td><%= q.getOption2() %></td>
                        <td><%= q.getOption3() %></td>
                        <td><%= q.getOption4() %></td>
                        <td><%= q.getCorrectAnswer() %></td>
                    </tr>
            <%
                }
            %>

        </table>
    </div>

</body>
</html>
