<%@page import="java.util.List"%>
<%@page import="model.Produto"%>
<%@page import="model.DAO.ProdutoDAO"%>
<%@page import="com.google.gson.Gson"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    
    try {
        ProdutoDAO dao = new ProdutoDAO();
        List<Produto> produtos = dao.consulta_geral();
        
        // Converter para JSON usando Gson
        Gson gson = new Gson();
        String json = gson.toJson(produtos);
        
        out.print(json);
        
    } catch (Exception ex) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        out.print("{\"error\": \"" + ex.getMessage() + "\"}");
    }
%>
