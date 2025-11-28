<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // Invalidar a sessão
    HttpSession sessao = request.getSession(false);
    if (sessao != null) {
        sessao.invalidate();
    }
    
    // Redirecionar para a página de login
    response.sendRedirect("../index.html");
%>
