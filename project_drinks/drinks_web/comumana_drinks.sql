-- phpMyAdmin SQL Dump
-- version 4.0.10.14
-- http://www.phpmyadmin.net
--
-- Servidor: localhost:3306
-- Tempo de Geração: 03/11/2017 às 11:29
-- Versão do servidor: 5.5.56-MariaDB-cll-lve
-- Versão do PHP: 5.6.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Banco de dados: `comumana_drinks`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `codUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `encrypted_password` varchar(80) NOT NULL,
  `telefone` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`codUsuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Fazendo dump de dados para tabela `usuarios`
--

INSERT INTO `usuarios` (`codUsuario`, `nome`, `email`, `encrypted_password`, `telefone`) VALUES
(1, 'Emerson Francisco', 'ems@gmail.com', 'jLIjfQZ5yojbZGTqxg2pY0VROWQ=', '81986854814'),
(2, 'Tercio Lima', 'terciosky@gmail.com', 'QL0AFWMIX8NRZTKeof9cXsvbvu8=', '81996644179'),
(3, 'Juliana', 'souza3408@gmail.com', 'a15q4UVZBErnR7WyvhHqQ2Sq/p0=', '81986226795'),
(4, 'Silvio', 'engeheirocedrim@gmail.com', 'fEqNCco3Yq9h5ZUglD3CZJT4lBs=', '81998237293'),
(5, 'italo', 'italo@italo.com', 'fEqNCco3Yq9h5ZUglD3CZJT4lBs=', '81995974462');

-- --------------------------------------------------------

--
-- Estrutura para tabela `estabelecimento`
--

CREATE TABLE IF NOT EXISTS `estabelecimento` (
  `codEstabelecimento` int(11) NOT NULL AUTO_INCREMENT,
  `cnpj` varchar(14) NOT NULL,
  `eMail` varchar(100) NOT NULL,
  `login` varchar(50) DEFAULT NULL,
  `bairro` varchar(50) DEFAULT NULL,
  `cep` varchar(9) DEFAULT NULL,
  `cidade` varchar(50) DEFAULT NULL,
  `latitude` varchar(12) DEFAULT NULL,
  `longitude` varchar(12) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `rua` varchar(150) DEFAULT NULL,
  `uf` varchar(2) DEFAULT NULL,
  `nomeFantasia` varchar(100) NOT NULL,
  `razaoSocial` varchar(100) NOT NULL,
  `senha` varchar(16) NOT NULL,
  `telefone` varchar(11) NOT NULL,
  `administrador` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`codEstabelecimento`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;

--
-- Fazendo dump de dados para tabela `estabelecimento`
--

INSERT INTO `estabelecimento` (`codEstabelecimento`, `cnpj`, `eMail`, `login`, `bairro`, `cep`, `cidade`, `latitude`, `longitude`, `numero`, `rua`, `uf`, `nomeFantasia`, `razaoSocial`, `senha`, `telefone`, `administrador`)VALUES
(1, '99999999999999', 'admin@admin.com', 'admin', '', '', '', '', '', '', '', '', 'SuperAdmin', 'SuperAdmin', '123456', '99999999999', 1),
(2, '10657358000145', 'gerencia@saladereboco.com.br', 'saladereboco', 'Cordeiro', '50720740', 'Recife', '-8.0536974', '-34.9222478', 264, 'Rua Gregório Júnio', 'PE', 'Sala de Reboco', 'Sala De Reboco Bar & Comedoria Ltda - Me', '123456', '81999750204', 0),
(3, '11004583000145', 'afabricabar@hotmail.com', 'fabrica', 'Carmo', '53120160', 'Olinda', '-8.0144249', '-34.8475361', 9, 'Travessa Municipal', 'PE', 'A Fábrica Bar', 'A Fabrica Bar e Restaurante Ltda - Me', '123456', '81987209705', 0),
(4, '10636838000120', 'grutabar.camaragibe@gmail.com', 'grutabar', 'Vila da Fábrica', '54759135', 'Camaragibe', '-8.0139369', '-34.9788453', 387, 'Rua Carlos Alberto de Menezes', 'PE', 'GRUTA BAR', 'M J DE SOUZA BAR - ME', '123456', '81999998146', 0),
(5, '12972637000129', 'betosbaroficial@gmail.com', 'betosbar', 'Candeias', '54440620', 'Jaboatao Dos Guararapes', '-8.0139369', '-34.9788453', 4738, 'Av. Bernardo Vieira De Melo', 'PE', 'BetoS Bar', 'BetoS Restaurante e Pizzaria Ltda - Epp', '123456', '8134692995', 0),
(6, '09202813000139', 'bardobarriga@gmail.com', 'bardobarriga', 'Pilar', '53900000', 'Ilha de Itamaracá', '-8.0139369', '-34.9788453', 30, 'Rua Assunção', 'PE', 'Bar Do Barriga O Rei Da Tainha', 'Bar Do Barriga O Rei Da Tainha', 'c20ad4d76fe97759', '81991915773', 0);

-- --------------------------------------------------------

--
-- Estrutura para tabela `produto`
--

CREATE TABLE IF NOT EXISTS `produto` (
  `codProduto` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(400) NOT NULL,
  `gelada` tinyint(1) DEFAULT NULL,
  `nome` varchar(150) NOT NULL,
  `preco` double NOT NULL,
  `codEstabelecimento` int(11) NOT NULL,
  PRIMARY KEY (`codProduto`),
  KEY `FK_Produto_Estabelecimento` (`codEstabelecimento`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Fazendo dump de dados para tabela `produto`
--

INSERT INTO `produto` (`codProduto`, `descricao`, `gelada`, `nome`, `preco`, `codEstabelecimento`) VALUES
(1, 'Gelo', 0, 'Gelo em Cubos', 3.99, 2),
(2, 'Cerveja ANTARCTICA Pilsen Lata 350ml', 0, 'Cerveja ANTARCTICA 350mL', 2.79, 2),
(3, 'Refrigerante Fanta Lata gelada ', 0, 'Fanta - Lata', 3.59, 2),
(4, 'Refrigerante Coca-cola Lata gelada ', 0, 'Coca-Cola Lata 350mL', 3.59, 2),
(5, 'Cerveja KAISER Lager Pilsen Lata 350ml', 0, 'Cerveja KAISER 350mL', 1.79, 2),
(6, 'Cerveja HEINEKEN Lager Beer Premium Lata 350ml', 0, 'Cerveja HEINEKEN 350mL', 3.59, 2),
(7, 'Cerveja DEVASSA Tropical Lager Long Neck 355mla', 0, 'Cerveja DEVASSA 355mL', 3.20, 2),
(8, 'Gelo', 0, 'Gelo em Escamas', 2.99, 3),
(9, 'Cerveja ANTARCTICA Pilsen Lata 350mL', 0, 'Cerveja ANTARCTICA 350mL', 2.69, 3),
(10, 'Vinho Tinto Seco 750 mL Salton Classic Cabernet Sauvignon 2016', 1, 'Vinho Tinto Seco 750 mL', 25.90, 3),
(11, 'Vinho Branco Seco 750mL Miolo Seleção Chardonnay Viognier 2016', 1, 'Vinho Branco Seco 750mL', 35.59, 3),
(12, 'Refrigerante Coca-cola 2L gelada', 0, 'Coca-Cola - 2L', 5.19, 3),
(13, 'Cerveja DEVASSA 350mL', 0, 'Cerveja DEVASSA Tropical Lager Lata 350mL', 1.99, 3),
(14, 'Refrigerante Sprite 2L gelada', 0, 'Sprite - 2L', 3.45, 3),
(15, 'Gelo', 0, 'Gelo em Cubos', 3.50, 5),
(16, 'Gelo', 0, 'Gelo em Escamas', 3.99, 5),
(17, 'Pitu', 1, 'Cachaça Pitu', 10.59, 5),
(18, 'Cerveja BOHEMIA Pilsen Long Neck 355mL', 0, 'Cerveja BOHEMIA 350mL', 3.49, 5),
(19, 'Refrigerante Coca-cola 2L gelada', 0, 'Coca-Cola - 2L', 6.49, 3),
(20, 'Vinho Bodega Vieja Tinto Suave 750mL', 1, 'Vinho Tinto Suave 750mL', 35.50, 5),
(22, 'Refrigerante Sprite 2L gelada', 0, 'Sprite - 2L', 4.00, 5);

-- --------------------------------------------------------

--
-- Estrutura para tabela `pedido`
--

CREATE TABLE IF NOT EXISTS `pedido` (
  `codPedido` int(11) NOT NULL AUTO_INCREMENT,
  `dataPedido` text NOT NULL,
  `bairro` varchar(50) DEFAULT NULL,
  `cep` varchar(9) DEFAULT NULL,
  `cidade` varchar(50) DEFAULT NULL,
  `latitude` varchar(10) DEFAULT NULL,
  `longitude` varchar(10) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `rua` varchar(150) DEFAULT NULL,
  `uf` varchar(2) DEFAULT NULL,
  `pagamento` varchar(255) DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `valorTotal` double NOT NULL,
  `codUsuario` int(11) NOT NULL,
  `codEstabelecimento` int(11) NOT NULL,
  PRIMARY KEY (`codPedido`),
  KEY `FK_codEstabelecimento` (`codEstabelecimento`),
  KEY `FK_codUsuario` (`codUsuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Fazendo dump de dados para tabela `pedido`
--

INSERT INTO `pedido` (`codPedido`, `dataPedido`, `bairro`, `cep`, `cidade`, `latitude`, `longitude`, `numero`, `rua`, `uf`, `pagamento`, `status`, `valorTotal`, `codUsuario`, `codEstabelecimento`) VALUES
(1, '31/10/2017 19:16:35', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ESPECIE', 'AGUARDANDO', 25.57, 5, 5),
(2, '31/10/2017 19:28:53', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ESPECIE', 'AGUARDANDO', 40.74, 4, 2),
(3, '01/11/2017 20:18:12', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CARTAO', 'AGUARDANDO', 81.15, 2, 3),
(4, '05/11/2017 19:05:25', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CARTAO', 'AGUARDANDO', 66.93, 1, 5);

-- --------------------------------------------------------

--
-- Estrutura para tabela `pedido_produto`
--

CREATE TABLE IF NOT EXISTS `pedido_produto` (
  `codPedido` int(11) NOT NULL,
  `codProduto` int(11) NOT NULL,
  `preco` double DEFAULT NULL,
  `quantidade` int(11) DEFAULT NULL,
  `precoTotal` double DEFAULT NULL,
  KEY `FK55E4DBFF3CBCE51E` (`codPedido`),
  KEY `FK55E4DBFFE4370969` (`codProduto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `pedido_produto`
--

INSERT INTO `pedido_produto` (`codPedido`, `codProduto`, `preco`, `quantidade`, `precoTotal`) VALUES
(1, 16, 3.99, 1, 3.99),
(1, 7, 3.20, 5, 16.00),
(1, 2, 2.79, 2, 5.58),
(2, 7, 3.20, 6, 19.20),
(2, 4, 3.59, 6, 21.54),
(3, 10, 25.90, 3, 77.70),
(3, 14, 3.45, 1, 3.45),
(4, 16, 3.99, 1, 3.99),
(4, 19, 6.49, 6, 38.94),
(4, 22, 4.00, 6, 24.00);

-- --------------------------------------------------------

--
-- Restrições para tabelas `pedido_produto`
--
ALTER TABLE `pedido_produto`
  ADD CONSTRAINT `FK_Pedido_produto_codPedido` FOREIGN KEY (`codPedido`) REFERENCES `pedido` (`codPedido`),
  ADD CONSTRAINT `FK_Pedido_produto_codProduto` FOREIGN KEY (`codProduto`) REFERENCES `produto` (`codProduto`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
