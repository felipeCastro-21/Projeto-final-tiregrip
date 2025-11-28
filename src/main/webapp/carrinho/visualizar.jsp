<%@page import="java.util.List"%>
<%@page import="model.Carrinho"%>
<%@page import="model.DAO.CarrinhoDAO"%>
<%@page import="com.google.gson.Gson"%>
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
        List<Carrinho> itens = dao.consulta_por_usuario(usuarioId);
        float subtotalTotal = dao.calcular_subtotal(usuarioId);
        
        Gson gson = new Gson();
        String json = "{\"itens\": " + gson.toJson(itens) + ", \"subtotal\": " + subtotalTotal + "}";
        out.print(json);
        
    } catch (Exception ex) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        out.print("{\"error\": \"" + ex.getMessage() + "\"}");
    }
%>
