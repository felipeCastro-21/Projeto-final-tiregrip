<%@page import="model.Usuario"%>
<%@page import="model.DAO.UsuarioDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login - TireGrip</title>
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
        }
        .container {
            max-width: 28rem;
            width: 100%;
            padding: 2rem;
        }
        .message {
            background-color: #1A1A1A;
            border: 2px solid #333333;
            padding: 2rem;
            text-align: center;
        }
        .success {
            border-color: #FFD000;
            color: #FFD000;
        }
        .error {
            border-color: #EF4444;
            color: #EF4444;
        }
        .message h2 {
            margin-bottom: 1rem;
            text-transform: uppercase;
            letter-spacing: 0.1em;
        }
        .message p {
            margin-bottom: 1.5rem;
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
        }
        .btn:hover {
            background-color: #FFD000;
        }
    </style>
</head>
<body>
    <div class="container">
        <%
            try {
                // Receber parâmetros do formulário
                String email = request.getParameter("email");
                String senha = request.getParameter("password");
                
                // Validar campos obrigatórios
                if (email == null || email.trim().isEmpty() || senha == null || senha.trim().isEmpty()) {
                    out.println("<div class='message error'>");
                    out.println("<h2>Erro</h2>");
                    out.println("<p>Por favor, preencha todos os campos!</p>");
                    out.println("<a href='../index.html' class='btn'>Voltar</a>");
                    out.println("</div>");
                } else {
                    // Tentar autenticar
                    UsuarioDAO dao = new UsuarioDAO();
                    Usuario usuario = dao.autenticar(email, senha);
                    
                    if (usuario != null) {
                        // Login bem-sucedido - criar sessão
                        HttpSession sessao = request.getSession();
                        sessao.setAttribute("usuarioId", usuario.getId());
                        sessao.setAttribute("usuarioNome", usuario.getNome());
                        sessao.setAttribute("usuarioEmail", usuario.getEmail());
                        
                        // Redirecionar para home
                        response.sendRedirect("../home.html");
                    } else {
                        // Credenciais inválidas
                        out.println("<div class='message error'>");
                        out.println("<h2>Erro de Autenticação</h2>");
                        out.println("<p>Email ou senha incorretos!</p>");
                        out.println("<a href='../index.html' class='btn'>Tentar Novamente</a>");
                        out.println("</div>");
                    }
                }
            } catch (Exception ex) {
                out.println("<div class='message error'>");
                out.println("<h2>Erro</h2>");
                out.println("<p>Erro ao processar login: " + ex.getMessage() + "</p>");
                out.println("<a href='../index.html' class='btn'>Voltar</a>");
                out.println("</div>");
            }
        %>
    </div>
</body>
</html>
