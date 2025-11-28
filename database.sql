





DROP DATABASE IF EXISTS TireGrip;
CREATE DATABASE TireGrip CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE TireGrip;




CREATE TABLE usuarios (
    pk_id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;




CREATE TABLE produtos (
    pk_id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    descricao TEXT,
    largura VARCHAR(20),
    perfil VARCHAR(20),
    aro VARCHAR(20),
    indice_carga VARCHAR(20),
    indice_velocidade VARCHAR(20),
    estoque INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_nome (nome),
    INDEX idx_preco (preco)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;




CREATE TABLE carrinho (
    pk_id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    produto_id INT NOT NULL,
    quantidade INT NOT NULL DEFAULT 1,
    subtotal DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(pk_id) ON DELETE CASCADE,
    FOREIGN KEY (produto_id) REFERENCES produtos(pk_id) ON DELETE CASCADE,
    INDEX idx_usuario (usuario_id),
    UNIQUE KEY unique_user_product (usuario_id, produto_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;




CREATE TABLE enderecos (
    pk_id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    cep VARCHAR(10) NOT NULL,
    rua VARCHAR(200) NOT NULL,
    numero VARCHAR(20) NOT NULL,
    complemento VARCHAR(100),
    bairro VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(pk_id) ON DELETE CASCADE,
    INDEX idx_usuario (usuario_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;




CREATE TABLE pedidos (
    pk_id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    endereco_id INT NOT NULL,
    data_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    subtotal DECIMAL(10,2) NOT NULL,
    frete DECIMAL(10,2) NOT NULL DEFAULT 50.00,
    desconto DECIMAL(10,2) DEFAULT 0.00,
    total DECIMAL(10,2) NOT NULL,
    forma_pagamento VARCHAR(20) NOT NULL,
    status VARCHAR(20) DEFAULT 'PENDENTE',
    FOREIGN KEY (usuario_id) REFERENCES usuarios(pk_id) ON DELETE RESTRICT,
    FOREIGN KEY (endereco_id) REFERENCES enderecos(pk_id) ON DELETE RESTRICT,
    INDEX idx_usuario (usuario_id),
    INDEX idx_status (status),
    INDEX idx_data (data_pedido)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;




CREATE TABLE itens_pedido (
    pk_id INT AUTO_INCREMENT PRIMARY KEY,
    pedido_id INT NOT NULL,
    produto_id INT NOT NULL,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (pedido_id) REFERENCES pedidos(pk_id) ON DELETE CASCADE,
    FOREIGN KEY (produto_id) REFERENCES produtos(pk_id) ON DELETE RESTRICT,
    INDEX idx_pedido (pedido_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;






INSERT INTO usuarios (nome, email, senha) VALUES 
('João Silva', 'joao@teste.com', '123456'),
('Maria Santos', 'maria@teste.com', '123456'),
('Admin TireGrip', 'admin@tiregrip.com', 'admin123');


INSERT INTO produtos (nome, modelo, preco, descricao, largura, perfil, aro, indice_carga, indice_velocidade, estoque) VALUES
('PNEU PERFORMANCE MAX', 'Modelo Sport 2024 - Alta Performance', 599.99, 
 'Pneu de alta performance com tecnologia de drenagem de água avançada, composto de borracha de alta aderência e baixo nível de ruído durante a condução.', 
 '205', '55', '16"', '91', 'V (240 km/h)', 50),

('PNEU OFF-ROAD PRO', 'Modelo Adventure Pro', 699.99, 
 'Pneu para terrenos difíceis com maior durabilidade e resistência ao desgaste. Ideal para aventuras off-road.', 
 '215', '65', '17"', '95', 'H (210 km/h)', 30),

('PNEU ECONÔMICO PLUS', 'Modelo Eco 2024', 399.99, 
 'Pneu com economia de combustível otimizada e desempenho em todas as estações. Excelente custo-benefício.', 
 '185', '60', '15"', '88', 'T (190 km/h)', 80),

('PNEU PREMIUM COMFORT', 'Modelo Luxury Ride', 799.99, 
 'Pneu premium com máximo conforto e silêncio. Tecnologia de absorção de impactos para viagens longas.', 
 '225', '50', '18"', '98', 'W (270 km/h)', 25),

('PNEU ALL-TERRAIN', 'Modelo Multi-Surface', 649.99, 
 'Pneu versátil para uso urbano e off-road leve. Aderência superior em diferentes superfícies.', 
 '205', '70', '16"', '92', 'S (180 km/h)', 40),

('PNEU SPORT RACING', 'Modelo Track Pro', 899.99, 
 'Pneu de alta performance para uso esportivo. Aderência máxima em curvas e frenagens.', 
 '245', '40', '19"', '100', 'Y (300 km/h)', 15);


INSERT INTO enderecos (usuario_id, cep, rua, numero, complemento, bairro, cidade) VALUES
(1, '01310-100', 'Avenida Paulista', '1578', 'Apto 101', 'Bela Vista', 'São Paulo'),
(1, '04543-907', 'Avenida Brigadeiro Faria Lima', '2232', NULL, 'Jardim Paulistano', 'São Paulo'),
(2, '22640-102', 'Avenida Vieira Souto', '500', 'Cobertura', 'Ipanema', 'Rio de Janeiro');


INSERT INTO carrinho (usuario_id, produto_id, quantidade, subtotal) VALUES
(1, 1, 2, 1199.98),
(1, 3, 1, 399.99);


INSERT INTO pedidos (usuario_id, endereco_id, subtotal, frete, desconto, total, forma_pagamento, status) VALUES
(2, 3, 1299.98, 50.00, 67.50, 1282.48, 'PIX', 'CONFIRMADO');


INSERT INTO itens_pedido (pedido_id, produto_id, quantidade, preco_unitario, subtotal) VALUES
(1, 2, 1, 699.99, 699.99),
(1, 5, 1, 649.99, 649.99);






CREATE VIEW vw_carrinho_detalhado AS
SELECT 
    c.pk_id,
    c.usuario_id,
    u.nome as usuario_nome,
    c.produto_id,
    p.nome as produto_nome,
    p.modelo as produto_modelo,
    p.preco as produto_preco,
    c.quantidade,
    c.subtotal
FROM carrinho c
INNER JOIN usuarios u ON c.usuario_id = u.pk_id
INNER JOIN produtos p ON c.produto_id = p.pk_id;


CREATE VIEW vw_pedidos_detalhados AS
SELECT 
    ped.pk_id,
    ped.usuario_id,
    u.nome as usuario_nome,
    u.email as usuario_email,
    ped.data_pedido,
    ped.subtotal,
    ped.frete,
    ped.desconto,
    ped.total,
    ped.forma_pagamento,
    ped.status,
    e.rua,
    e.numero,
    e.bairro,
    e.cidade,
    e.cep
FROM pedidos ped
INNER JOIN usuarios u ON ped.usuario_id = u.pk_id
INNER JOIN enderecos e ON ped.endereco_id = e.pk_id;


























