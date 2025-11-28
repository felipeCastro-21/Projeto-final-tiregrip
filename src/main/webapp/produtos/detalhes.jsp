<%@page import="model.Produto"%>
<%@page import="model.DAO.ProdutoDAO"%>
<%@page import="com.google.gson.Gson"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    
    try {
        String idParam = request.getParameter("id");
        
        if (idParam == null || idParam.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"error\": \"ID do produto não fornecido\"}");
        } else {
            int produtoId = Integer.parseInt(idParam);
            
            ProdutoDAO dao = new ProdutoDAO();
            Produto produto = new Produto();
            produto.setId(produtoId);
            produto = dao.consulta_id(produto);
            
            if (produto != null) {
                // Converter para JSON usando Gson
                Gson gson = new Gson();
                String json = gson.toJson(produto);
                out.print(json);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.print("{\"error\": \"Produto não encontrado\"}");
            }
        }
        
    } catch (NumberFormatException ex) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        out.print("{\"error\": \"ID inválido\"}");
    } catch (Exception ex) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        out.print("{\"error\": \"" + ex.getMessage() + "\"}");
    }
%>
