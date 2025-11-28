<%@page import="java.util.List"%>
<%@page import="model.Endereco"%>
<%@page import="model.DAO.EnderecoDAO"%>
<%@page import="com.google.gson.Gson"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    
    try {
        HttpSession sessao = request.getSession(false);
        if (sessao == null || sessao.getAttribute("usuarioId") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.print("{\"error\": \"Não autenticado\"}");
            return;
        }
        
        int usuarioId = (Integer) sessao.getAttribute("usuarioId");
        
        EnderecoDAO dao = new EnderecoDAO();
        List<Endereco> enderecos = dao.consulta_por_usuario(usuarioId);
        
        Gson gson = new Gson();
        String json = gson.toJson(enderecos);
        out.print(json);
        
    } catch (Exception ex) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        out.print("{\"error\": \"" + ex.getMessage() + "\"}");
    }
%>
