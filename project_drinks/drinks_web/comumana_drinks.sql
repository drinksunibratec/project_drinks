-- phpMyAdmin SQL Dump
-- version 4.0.10.14
-- http://www.phpmyadmin.net
--
-- Servidor: localhost:3306
-- Tempo de Geração: 27/10/2017 às 21:42
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
  `latitude` varchar(10) DEFAULT NULL,
  `longitude` varchar(10) DEFAULT NULL,
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Fazendo dump de dados para tabela `estabelecimento`
--

INSERT INTO `estabelecimento` (`codEstabelecimento`, `cnpj`, `eMail`, `login`, `bairro`, `cep`, `cidade`, `latitude`, `longitude`, `numero`, `rua`, `uf`, `nomeFantasia`, `razaoSocial`, `senha`, `telefone`, `administrador`) VALUES
(1, '12345678912345', 'silviocedrim@outlook.com', NULL, 'A', '55555000', 'Recife', '-8.0634838', '-34.87286', 12, 'Rua B', 'PE', 'Teste', 'Teste', '123456', '11111111111', 0),
(3, '24582745000174', 'admin@admin.com', 'admin', '', '', 'Recife', '', '', 0, '', 'PE', 'SuperAdmin', 'SuperAdmin', '123456', '99999999994', 1),
(6, '11112222222222', 'postoa@gmail.com', 'postoa', 'B', '22222222', 'Recife', '-8.0621664', '-34.87249', 23, 'Rua A', 'PE', 'Posto A', 'Posto A', '123456', '23333333333', 0),
(7, '11111111111111', 'qteste@gmail.com', NULL, 'A', '11111111', 'Recife', '0.1', '0.1', 12, 'Rua B', 'PE', 'Teste', 'Teste', '123456', '11111111111', 0),
(8, '11111111111111', 'teste1@gmail.com', NULL, 'A', '11111111', 'Recife', '0.1', '0.1', 12, 'Rua B', 'PE', 'Teste 1', 'Teste 1', '123456', '11111111111', 0),
(10, '23344444444444', 'teste3@gmail.com', NULL, 'A', '22222222', 'D', '0.1', '0.1', 12, NULL, 'AC', 'Teste 3', 'Teste 3', 'c20ad4d76fe97759', '11111111111', 0),
(11, '55555555555555', 'teste4@gmail.com', NULL, 'D', '88888888', 'Recife', '0.1', '0.1', 14, NULL, 'PE', 'Teste 4', 'Teste 4', '202cb962ac59075b', '33333333333', 0),
(12, '55555555555555', 'a@g.com', NULL, 'S', '99999999', 'S', '0.3', '0.4', 1, NULL, 'PA', 'Teste 5', 'Teste 5', '202cb962ac59075b', '33333333333', 0),
(13, '44444444444444', 'a@gmail.com', NULL, 'A', '32222222', 'Recife', '0.1', '0.1', 12, NULL, 'AL', 'Teste 3', 'Teste final', '1223456', '22222222222', 0),
(14, '11111111111111', 'teste3@gmail.com', NULL, 'A', '11111111', 'Recife', '-8.0420327', '-34.89108', 12, NULL, 'AM', 'Teste 3', 'Teste', '123', '22222222222', 0),
(15, '11111111111111', 'teste3@gmail.com', NULL, 'A', '22222222', 'Recife', '0.1', '0.1', 12, NULL, 'AL', 'Teste 3', 'Teste 3', '123', '___________', 0),
(17, '77777777777777', 'teste3@gmail.com', NULL, 'A', '33333333', 'Recife', '0.1', '0.1', 12, NULL, 'AP', 'Teste 3', 'Teste 3', '123456', '23333333333', 0),
(18, '99999999999999', 'admin@admin.com', NULL, 'A', '12111111', 'Recife', '0.1', '0.1', 12, NULL, 'AL', 'Teste 3', 'Teste 3', '1223456', '22222222222', 0);

-- --------------------------------------------------------

--
-- Estrutura para tabela `pedido`
--

CREATE TABLE IF NOT EXISTS `pedido` (
  `codPedido` int(11) NOT NULL AUTO_INCREMENT,
  `dataPedido` datetime NOT NULL,
  `bairro` varchar(50) NOT NULL,
  `cep` varchar(9) NOT NULL,
  `cidade` varchar(50) NOT NULL,
  `latitude` varchar(10) NOT NULL,
  `longitude` varchar(10) NOT NULL,
  `numero` int(11) NOT NULL,
  `rua` varchar(150) NOT NULL,
  `uf` varchar(2) NOT NULL,
  `pagamento` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `valorTotal` double NOT NULL,
  `codUsuario` int(11) NOT NULL,
  `codEstabelecimento` int(11) NOT NULL,
  PRIMARY KEY (`codPedido`),
  KEY `FK_codEstabelecimento` (`codEstabelecimento`),
  KEY `FK_codUsuario` (`codUsuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Estrutura para tabela `pedido_produto`
--

CREATE TABLE IF NOT EXISTS `pedido_produto` (
  `codPedido` int(11) NOT NULL,
  `codProduto` int(11) NOT NULL,
  KEY `FK55E4DBFF3CBCE51E` (`codPedido`),
  KEY `FK55E4DBFFE4370969` (`codProduto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Fazendo dump de dados para tabela `produto`
--

INSERT INTO `produto` (`codProduto`, `descricao`, `gelada`, `nome`, `preco`, `codEstabelecimento`) VALUES
(1, 'Gelada', 0, 'Cerveja', 5.2, 6),
(3, '', 0, 'Cerveja', 11.11, 1),
(6, 'Cerveja', 0, 'Cerveja', 11, 1),
(7, 'Cerveja', 0, 'Cerveja22', 11, 1),
(8, 'Cerveja', 0, 'Cerveja23', 12, 1);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Fazendo dump de dados para tabela `usuarios`
--

INSERT INTO `usuarios` (`codUsuario`, `nome`, `email`, `encrypted_password`, `telefone`) VALUES
(10, 'Emerson Francisco Da Silva', 'ems@gmail.com', 'jLIjfQZ5yojbZGTqxg2pY0VROWQ=', '(81)98685-4814'),
(12, 'Tercio', 'terciosky@gmail.com', 'QL0AFWMIX8NRZTKeof9cXsvbvu8=', '81996644179'),
(13, 'Juliana', 'souza3408@gmail.com', 'a15q4UVZBErnR7WyvhHqQ2Sq/p0=', '(81)98622-6795'),
(14, 'Silvio', 'engeheirocedrim@gmail.com', 'fEqNCco3Yq9h5ZUglD3CZJT4lBs=', '(81)99823-7293');

--
-- Restrições para dumps de tabelas
--

--
-- Restrições para tabelas `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `FK_codEstabelecimento` FOREIGN KEY (`codEstabelecimento`) REFERENCES `estabelecimento` (`codEstabelecimento`),
  ADD CONSTRAINT `FK_codUsuario` FOREIGN KEY (`codUsuario`) REFERENCES `usuarios` (`codUsuario`);

--
-- Restrições para tabelas `pedido_produto`
--
ALTER TABLE `pedido_produto`
  ADD CONSTRAINT `FK_Pedido_produto_codPedido` FOREIGN KEY (`codPedido`) REFERENCES `pedido` (`codPedido`),
  ADD CONSTRAINT `FK_Pedido_produto_codProduto` FOREIGN KEY (`codProduto`) REFERENCES `produto` (`codProduto`);

--
-- Restrições para tabelas `produto`
--
ALTER TABLE `produto`
  ADD CONSTRAINT `FK_Produto_Estabelecimento` FOREIGN KEY (`codEstabelecimento`) REFERENCES `estabelecimento` (`codEstabelecimento`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
