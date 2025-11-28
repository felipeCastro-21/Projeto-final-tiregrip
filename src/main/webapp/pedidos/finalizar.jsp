<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Carrinho"%>
<%@page import="model.Pedido"%>
<%@page import="model.ItemPedido"%>
<%@page import="model.DAO.CarrinhoDAO"%>
<%@page import="model.DAO.PedidoDAO"%>
<%@page import="model.DAO.ItemPedidoDAO"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    
    try {
        // Verificar autenticação
        HttpSession sessao = request.getSession(false);
        if (sessao == null || sessao.getAttribute("usuarioId") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.print("{\"success\": false, \"message\": \"Não autenticado\"}");
            return;
        }
        
        int usuarioId = (Integer) sessao.getAttribute("usuarioId");
        
        // Receber parâmetros
        String enderecoIdParam = request.getParameter("enderecoId");
        String formaPagamento = request.getParameter("formaPagamento");
        
        if (enderecoIdParam == null || formaPagamento == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"success\": false, \"message\": \"Parâmetros inválidos\"}");
            return;
        }
        
        int enderecoId = Integer.parseInt(enderecoIdParam);
        
        // Buscar itens do carrinho
        CarrinhoDAO carrinhoDAO = new CarrinhoDAO();
        List<Carrinho> itensCarrinho = carrinhoDAO.consulta_por_usuario(usuarioId);
        
        if (itensCarrinho == null || itensCarrinho.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"success\": false, \"message\": \"Carrinho vazio\"}");
            return;
        }
        
        // Calcular valores
        float subtotal = carrinhoDAO.calcular_subtotal(usuarioId);
        float frete = 50.00f;
        float desconto = 0.0f;
        
        // Aplicar desconto de 5% se for PIX
        if ("PIX".equalsIgnoreCase(formaPagamento)) {
            desconto = (subtotal + frete) * 0.05f;
        }
        
        float total = subtotal + frete - desconto;
        
        // Criar pedido
        Pedido pedido = new Pedido();
        pedido.setUsuarioId(usuarioId);
        pedido.setEnderecoId(enderecoId);
        pedido.setSubtotal(subtotal);
        pedido.setFrete(frete);
        pedido.setDesconto(desconto);
        pedido.setTotal(total);
        pedido.setFormaPagamento(formaPagamento.toUpperCase());
        pedido.setStatus("PENDENTE");
        
        PedidoDAO pedidoDAO = new PedidoDAO();
        int pedidoId = pedidoDAO.cadastrar(pedido);
        
        if (pedidoId == 0) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"success\": false, \"message\": \"Erro ao criar pedido\"}");
            return;
        }
        
        // Criar itens do pedido
        List<ItemPedido> itensPedido = new ArrayList<>();
        for (Carrinho itemCarrinho : itensCarrinho) {
            ItemPedido item = new ItemPedido();
            item.setPedidoId(pedidoId);
            item.setProdutoId(itemCarrinho.getProdutoId());
            item.setQuantidade(itemCarrinho.getQuantidade());
            item.setPrecoUnitario(itemCarrinho.getProdutoPreco());
            item.setSubtotal(itemCarrinho.getSubtotal());
            itensPedido.add(item);
        }
        
        ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
        boolean itensAdicionados = itemPedidoDAO.cadastrar_lote(itensPedido);
        
        if (!itensAdicionados) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"success\": false, \"message\": \"Erro ao adicionar itens do pedido\"}");
            return;
        }
        
        // Limpar carrinho
        carrinhoDAO.limpar_carrinho(usuarioId);
        
        // Retornar sucesso
        out.print("{\"success\": true, \"message\": \"Pedido finalizado com sucesso\", \"pedidoId\": " + pedidoId + ", \"total\": " + total + "}");
        
    } catch (Exception ex) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        out.print("{\"success\": false, \"message\": \"" + ex.getMessage() + "\"}");
    }
%>
