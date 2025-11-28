<%@page import="model.DAO.CarrinhoDAO"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    
    try {
        HttpSession sessao = request.getSession(false);
        if (sessao == null || sessao.getAttribute("usuarioId") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.print("{\"success\": false, \"message\": \"Não autenticado\"}");
            return;
        }
        
        String idParam = request.getParameter("id");
        
        if (idParam == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"success\": false, \"message\": \"ID não fornecido\"}");
            return;
        }
        
        int id = Integer.parseInt(idParam);
        
        CarrinhoDAO dao = new CarrinhoDAO();
        boolean sucesso = dao.remover(id);
        
        out.print("{\"success\": " + sucesso + ", \"message\": \"Item removido do carrinho\"}");
        
    } catch (Exception ex) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        out.print("{\"success\": false, \"message\": \"" + ex.getMessage() + "\"}");
    }
%>
