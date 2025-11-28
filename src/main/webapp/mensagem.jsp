<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>TireGrip - Mensagem</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-family: 'Inter', system-ui, sans-serif;
            background-color: black;
            color: white;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 1rem;
        }
        .container {
            max-width: 32rem;
            width: 100%;
        }
        .message-box {
            background-color: #1A1A1A;
            border: 2px solid #FF6B00;
            padding: 2rem;
            text-align: center;
        }
        .message-box h2 {
            color: #FF6B00;
            text-transform: uppercase;
            letter-spacing: 0.1em;
            margin-bottom: 1rem;
            font-weight: 800;
        }
        .message-box p {
            color: white;
            margin-bottom: 1.5rem;
            line-height: 1.6;
        }
        .btn {
            background-color: #FF6B00;
            color: black;
            padding: 1rem 2rem;
            text-transform: uppercase;
            letter-spacing: 0.1em;
            font-weight: 800;
            border: none;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            transition: background-color 0.3s;
        }
        .btn:hover {
            background-color: #FFD000;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="message-box">
            <h2>Mensagem do Sistema</h2>
            <p>
                <%= request.getAttribute("Message") != null ? request.getAttribute("Message") : "Operação realizada!" %>
            </p>
            <a href="javascript:history.back()" class="btn">Voltar</a>
        </div>
    </div>
</body>
</html>
