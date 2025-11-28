<%@page import="model.Carrinho"%>
<%@page import="model.Produto"%>
<%@page import="model.DAO.CarrinhoDAO"%>
<%@page import="model.DAO.ProdutoDAO"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    
    try {
        // Verificar autenticação
        HttpSession sessao = request.getSession(false);
        if (sessao == null || sessao.getAttribute("usuarioId") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.print("{\"success\": false, \"message\": \"Usuário não autenticado\"}");
            return;
        }
        
        int usuarioId = (Integer) sessao.getAttribute("usuarioId");
        
        // Receber parâmetros
        String produtoIdParam = request.getParameter("produtoId");
        String quantidadeParam = request.getParameter("quantidade");
        
        if (produtoIdParam == null || quantidadeParam == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"success\": false, \"message\": \"Parâmetros inválidos\"}");
            return;
        }
        
        int produtoId = Integer.parseInt(produtoIdParam);
        int quantidade = Integer.parseInt(quantidadeParam);
        
        // Buscar produto para obter o preço
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = new Produto();
        produto.setId(produtoId);
        produto = produtoDAO.consulta_id(produto);
        
        if (produto == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            out.print("{\"success\": false, \"message\": \"Produto não encontrado\"}");
            return;
        }
        
        // Calcular subtotal
        float subtotal = produto.getPreco() * quantidade;
        
        // Verificar se item já existe no carrinho
        CarrinhoDAO carrinhoDAO = new CarrinhoDAO();
        Carrinho itemExistente = carrinhoDAO.consulta_item(usuarioId, produtoId);
        
        boolean sucesso;
        if (itemExistente != null) {
            // Atualizar quantidade
            int novaQuantidade = itemExistente.getQuantidade() + quantidade;
            float novoSubtotal = produto.getPreco() * novaQuantidade;
            sucesso = carrinhoDAO.atualizar_quantidade(itemExistente.getId(), novaQuantidade, novoSubtotal);
        } else {
            // Adicionar novo item
            Carrinho novoItem = new Carrinho();
            novoItem.setUsuarioId(usuarioId);
            novoItem.setProdutoId(produtoId);
            novoItem.setQuantidade(quantidade);
            novoItem.setSubtotal(subtotal);
            sucesso = carrinhoDAO.adicionar(novoItem);
        }
        
        if (sucesso) {
            out.print("{\"success\": true, \"message\": \"Produto adicionado ao carrinho\"}");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"success\": false, \"message\": \"Erro ao adicionar produto\"}");
        }
        
    } catch (Exception ex) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        out.print("{\"success\": false, \"message\": \"" + ex.getMessage() + "\"}");
    }
%>
