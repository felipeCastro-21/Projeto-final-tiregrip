<%@page import="java.util.List"%>
<%@page import="model.Pedido"%>
<%@page import="model.ItemPedido"%>
<%@page import="model.DAO.PedidoDAO"%>
<%@page import="model.DAO.ItemPedidoDAO"%>
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
        
        String pedidoIdParam = request.getParameter("pedidoId");
        
        if (pedidoIdParam == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"error\": \"ID do pedido não fornecido\"}");
            return;
        }
        
        int pedidoId = Integer.parseInt(pedidoIdParam);
        
        // Buscar pedido
        PedidoDAO pedidoDAO = new PedidoDAO();
        Pedido pedido = new Pedido();
        pedido.setId(pedidoId);
        pedido = pedidoDAO.consulta_id(pedido);
        
        if (pedido == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            out.print("{\"error\": \"Pedido não encontrado\"}");
            return;
        }
        
        // Buscar itens do pedido
        ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
        List<ItemPedido> itens = itemPedidoDAO.consulta_por_pedido(pedidoId);
        
        // Montar resposta JSON
        Gson gson = new Gson();
        String json = "{\"pedido\": " + gson.toJson(pedido) + ", \"itens\": " + gson.toJson(itens) + "}";
        out.print(json);
        
    } catch (Exception ex) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        out.print("{\"error\": \"" + ex.getMessage() + "\"}");
    }
%>
