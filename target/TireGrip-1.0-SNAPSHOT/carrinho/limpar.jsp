<%@page import="model.DAO.CarrinhoDAO"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    
    try {
        HttpSession sessao = request.getSession(false);
        if (sessao == null || sessao.getAttribute("usuarioId") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.print("{\"error\": \"Usuário não autenticado\"}");
            return;
        }
        
        int usuarioId = (Integer) sessao.getAttribute("usuarioId");
        
        CarrinhoDAO dao = new CarrinhoDAO();
        boolean sucesso = dao.limpar_carrinho(usuarioId);
        
        if (sucesso) {
            out.print("{\"success\": true, \"message\": \"Carrinho limpo com sucesso\"}");
        } else {
            out.print("{\"success\": false, \"message\": \"Erro ao limpar carrinho\"}");
        }
        
    } catch (Exception ex) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        out.print("{\"error\": \"" + ex.getMessage() + "\"}");
    }
%>
