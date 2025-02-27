<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quiz Result</title>
<style>
    body {
        font-family: Arial, sans-serif;
        text-align: center;
        background: linear-gradient(to right, white, red);
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .result-box {
        width: 50%;
        background: rgba(255, 255, 255, 0.8);
        padding: 20px;
        border-radius: 10px;
        box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.3);
    }
    .btn {
        padding: 10px 20px;
        background-color: red;
        color: white;
        border: none;
        cursor: pointer;
        text-decoration: none;
        display: inline-block;
        margin-top: 20px;
        border-radius: 5px;
    }
    .btn:hover {
        background-color: darkred;
    }
</style>
</head>
<body>
    <div class="result-box">
        <h2>Quiz Completed!</h2>
        <h3>Your Score: <%= session.getAttribute("score") %></h3>
        <a href="index.jsp" class="btn">Home</a>
    </div>
</body>
</html>
