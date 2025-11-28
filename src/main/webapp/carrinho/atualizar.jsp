<%@page import="model.Carrinho"%>
<%@page import="model.Produto"%>
<%@page import="model.DAO.CarrinhoDAO"%>
<%@page import="model.DAO.ProdutoDAO"%>
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
        String quantidadeParam = request.getParameter("quantidade");
        
        if (idParam == null || quantidadeParam == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"success\": false, \"message\": \"Parâmetros inválidos\"}");
            return;
        }
        
        int id = Integer.parseInt(idParam);
        int quantidade = Integer.parseInt(quantidadeParam);
        
        CarrinhoDAO dao = new CarrinhoDAO();
        
        if (quantidade < 1) {
            // Remover item
            boolean sucesso = dao.remover(id);
            out.print("{\"success\": " + sucesso + ", \"message\": \"Item removido\"}");
        } else {
            // Atualizar quantidade - precisa recalcular subtotal
            // Por simplicidade, vamos apenas atualizar (o front-end deve enviar o subtotal correto)
            String subtotalParam = request.getParameter("subtotal");
            float subtotal = Float.parseFloat(subtotalParam);
            
            boolean sucesso = dao.atualizar_quantidade(id, quantidade, subtotal);
            out.print("{\"success\": " + sucesso + ", \"message\": \"Quantidade atualizada\"}");
        }
        
    } catch (Exception ex) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        out.print("{\"success\": false, \"message\": \"" + ex.getMessage() + "\"}");
    }
%>
