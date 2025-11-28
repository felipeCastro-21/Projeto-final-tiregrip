<%@page import="model.Endereco"%>
<%@page import="model.DAO.EnderecoDAO"%>
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
        
        int usuarioId = (Integer) sessao.getAttribute("usuarioId");
        
        // Receber parâmetros
        String cep = request.getParameter("cep");
        String rua = request.getParameter("rua");
        String numero = request.getParameter("numero");
        String complemento = request.getParameter("complemento");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        
        // Validar campos obrigatórios
        if (cep == null || rua == null || numero == null || bairro == null || cidade == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"success\": false, \"message\": \"Campos obrigatórios não preenchidos\"}");
            return;
        }
        
        // Criar objeto Endereco
        Endereco endereco = new Endereco();
        endereco.setUsuarioId(usuarioId);
        endereco.setCep(cep);
        endereco.setRua(rua);
        endereco.setNumero(numero);
        endereco.setComplemento(complemento);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        
        // Cadastrar
        EnderecoDAO dao = new EnderecoDAO();
        boolean sucesso = dao.cadastrar(endereco);
        
        if (sucesso) {
            out.print("{\"success\": true, \"message\": \"Endereço cadastrado com sucesso\"}");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"success\": false, \"message\": \"Erro ao cadastrar endereço\"}");
        }
        
    } catch (Exception ex) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        out.print("{\"success\": false, \"message\": \"" + ex.getMessage() + "\"}");
    }
%>
